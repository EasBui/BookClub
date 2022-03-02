package Controller;

import Dto.Review.ReviewSummaryRes;
import Dto.User.Conversation;
import Dto.User.MessageReq;
import Dto.User.UserProfileReq;
import Dto.User.UserUpdateReq;
import Security.CustomUserDetails;
import Service.ReviewService;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ReviewService reviewService;

    /* 자신의 프로필 보기 */
    @RequestMapping(value={"","/"}, method=RequestMethod.GET)
    public String userInfo(@AuthenticationPrincipal CustomUserDetails user) {
        if(user == null) {
            return "redirect:/form/login";
        }
        /* 자신의 프로필로 고고 */
        return "redirect:/users/" + user.getName();
    }

    /* 자신의 프로필 수정 양식 */
    @RequestMapping(value="/form/update", method=RequestMethod.GET)
    public String userInfoUpdateForm(
            Model model,
            @AuthenticationPrincipal CustomUserDetails user) {
        UserProfileReq upr = userService.searchUser(user.getName());
        model.addAttribute("user", upr);
        return "user.form.update";
    }

    /* 자신의 프로필 수정 절차 */
    @RequestMapping(value={"","/"}, method=RequestMethod.PUT)
    public String updateUserInfo(
            @RequestParam String password,
            @RequestParam String profile,
            @RequestParam String name,
            @AuthenticationPrincipal CustomUserDetails user) {
        UserUpdateReq userUpdateReq = new UserUpdateReq(user.getID(), password, name, profile);
        userService.updateUser(userUpdateReq);
        return "redirect:/users/";
    }

    /* 유저 프로필 검색 */
    @RequestMapping(value = {"/{username}"}, method=RequestMethod.GET)
    public String searchUserProfile(Model model, @PathVariable String username, @AuthenticationPrincipal CustomUserDetails user) {
        if(user == null) {
            return "redirect:/form/login";
        }
        try {
            UserProfileReq userProfileReq = userService.searchUser(username);
            model.addAttribute("user", userProfileReq);
            model.addAttribute("isMe", username.equals(user.getName()));

            List<ReviewSummaryRes> reviewSummaryRes = reviewService.searchReviewSummariesWithAuthor(user.getName());
            model.addAttribute("reviews", reviewSummaryRes);
            return "user.profile";
        } catch(RuntimeException e) {
            e.printStackTrace();
            return "redirect:/";
        }
    }

    /* 자신의 메세지함 */
    @RequestMapping(value = {"/{userName}/messages"}, method = RequestMethod.GET)
    public String messageBox(
            Model model,
            @PathVariable String userName,
            @AuthenticationPrincipal CustomUserDetails user) {
        if(!userName.equals(user.getName())) {
            return "redirect:/";
        }

        List<Conversation> conversations = userService.searchConversationsOf(userName);
        model.addAttribute("conversations", conversations);

        return "user.message";
    }

    /* 메세지 보내는 절차. 송신자가 수신자의 메세지함에 등록하는 방식 */
    @ResponseBody
    @RequestMapping(value = {"/{userName}/messages"}, method = RequestMethod.POST)
    public HashMap<String, Object> sendMessageToUser(
            @PathVariable String userName,
            @RequestBody HashMap<String, Object> data,
            @AuthenticationPrincipal CustomUserDetails user) {
        HashMap<String, Object> res = new HashMap<>();
        MessageReq messageReq = new MessageReq(
                user.getName(),
                userName,
                String.valueOf(data.get("content")),
                LocalDateTime.now());
        if(user == null) {
            res.put("error", true);
            res.put("message", "login first!");
        }
        if( userService.sendMessage(messageReq)) {
            res.put("error", false);
            res.put("redirect", "/users/" + user.getName() + "/messages");
        }
        else {
            res.put("error", true);
            res.put("message", "fail to sendMessage");
        }
        return res;
    }

    /* 특정 메시지들을 비활성화. 특정 상대와 주고 받았던 메세지 창을 닫을때 필요 */
    @ResponseBody
    @RequestMapping(value = {"/{userName}/messages"}, method = RequestMethod.PUT)
    public HashMap<String, String> unsubscribeConversation(
            @PathVariable String userName,
            @RequestParam String counterpartName,
            @AuthenticationPrincipal CustomUserDetails user) {
        HashMap<String, String> res = new HashMap<>();
        if(user == null || !userName.equals(user.getName())) {
            res.put("error", "true");
            res.put("message", "permission denied!");
            res.put("redirect", "/");
            return res;
        }
        if(userService.unsubscribeConversation(userName, counterpartName)) {
            res.put("error", "false");
            res.put("redirect", "/users/" + userName + "/messages");
        }
        else {
            res.put("error", "true");
            res.put("message", "message unsubscribe fail!");
        }
        return res;
    }


}
