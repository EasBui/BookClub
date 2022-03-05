package Controller;

import Dto.Club.ClubBasicRes;
import Dto.Club.ClubBookRes;
import Dto.Club.ClubDetailRes;
import Dto.Club.ClubNewReq;
import Dto.Review.ReviewSummaryRes;
import Dto.User.MessageReq;
import Dto.User.UserProfileRes;
import Security.CustomUserDetails;
import Service.ClubService;
import Service.ReviewService;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value="/clubs")
public class ClubController {
    @Autowired
    ClubService clubService;

    @Autowired
    ReviewService reviewService;


    // TODO : 페이지네이션 구현
    /* 전체 클럽의 소개 목록 */
    @RequestMapping(value = {"/list"}, method= RequestMethod.GET)
    public String clubList(Model model,
                           @RequestParam(required = false, defaultValue = "") String query,
                           @RequestParam Boolean isTags){
        List<ClubBasicRes> clubs;
        if(query == null || query.length() == 0) {
            clubs = clubService.listAllClub();
        }
        else {
            if(isTags) {
                clubs = clubService.listClubWithTags(query);
            }
            else {
                clubs = clubService.listClubWithTitle(query);
            }
        }
        model.addAttribute("clubs", clubs);
        return "club.list";
    }

    // TODO
    /* 클럽 생성 절차 */
    @RequestMapping(value = {"","/"}, method= RequestMethod.POST)
    public String createClub(
            @AuthenticationPrincipal CustomUserDetails thisUser,
            @RequestParam String clubName,
            @RequestParam String description,
            @RequestParam String tags,
            @RequestParam boolean isOpened){
        clubService.addClub(new ClubNewReq(clubName, thisUser.getID(), description, isOpened, tags));
        return "redirect:/clubs/"+clubName;
    }

    /* 신규 클럽 생성 양식 */
    @RequestMapping(value = "/form/new", method = RequestMethod.GET)
    public String getNewClubForm(@AuthenticationPrincipal CustomUserDetails thisUser) {
        return "club.form.new";
    }

    // TODO: 클럽의 회원과 읽은 책 목록, 리뷰 등을 대문정보에 포함 + 관리자 페이지
    /* 특정 클럽 대문 */
    @RequestMapping(value = "/{clubName}", method= RequestMethod.GET)
    public String clubFacade(Model model, @PathVariable String clubName,
                             @AuthenticationPrincipal CustomUserDetails user){
        /* 사용자의 클럽에서 권한 */
        model.addAttribute("isMember", (user.roleCheckOn(clubName) >= 1)); // roleCheckOn 결과가 1 이상이면 멤버임
        model.addAttribute("isHost", (user.roleCheckOn(clubName) == 2)); // 2면 호스트임
        /* 기본 정보 */
        ClubBasicRes info = clubService.searchClub(clubName);
        model.addAttribute("info", info);
        /* 가입신청자 목록 */
        List<UserProfileRes> signUps = clubService.searchClubSignUps(clubName);
        model.addAttribute("signUps", signUps);
        /* 회원 목록 */
        List<UserProfileRes> members = clubService.searchClubMembers(clubName);
        model.addAttribute("members", members);
        /* 클럽에서 읽은 도서와 그에 따른 리뷰를 짝지어 주기 위해 리뷰를 도서별(ISBN 이용)로 분류 */
        List<ClubBookRes> books = clubService.getBookStack(clubName);
        model.addAttribute("books", books);
        HashMap<String, List<ReviewSummaryRes>> reviewsClassifiedByISBN = new HashMap<>();
        for(ReviewSummaryRes review : reviewService.searchReviewSummariesWithClub(clubName)) {
            String ISBN = review.getBook().getISBN();
            if(!reviewsClassifiedByISBN.containsKey(ISBN)) {
                reviewsClassifiedByISBN.put(ISBN, new ArrayList<ReviewSummaryRes>());
            }
            reviewsClassifiedByISBN.get(ISBN).add(review);
        }
        model.addAttribute("reviewsClassifiedByISBN", reviewsClassifiedByISBN);
        return "club.facade._";
    }

    // TODO
    /* 클럽 정보 수정 절차 */
    @RequestMapping(value = "/{clubName}", method= RequestMethod.PUT)
    public String updateClubInfo(@PathVariable String clubName){
        return null;
    }

    // TODO
    /* 클럽 게시판 */
    @RequestMapping(value = "/{clubName}/{boardID}", method= RequestMethod.GET)
    public String clubBoard(@PathVariable String clubName, @PathVariable int boardID){
        return null;
    }

    // TODO
    /* 클럽 게시판에 글쓰기 */
    @RequestMapping(value = "/{clubName}/{boardID}", method= RequestMethod.POST)
    public String postClubBoard(@PathVariable String clubName, @PathVariable int boardID,
                                @RequestParam String title) {
        return null;
    }

    /* 클럽 가입 신청 절차 */
    @ResponseBody
    @RequestMapping(value = "/{clubName}/sign-up", method=RequestMethod.POST)
    public HashMap<String, Object> signUp(@PathVariable String clubName, @AuthenticationPrincipal CustomUserDetails user){
        // TODO : 유저가 이미 가입한 상황이라면 여기 접근 막아야 함.
        HashMap<String, Object> res = new HashMap<>();
        if(!clubService.addSignUp(user.getName(), clubName)) {
            res.put("error", true);
            res.put("message", "가입 신청에 실패하였습니다");
        }
        else {
            res.put("error", false);
            res.put("message", "가입에 성공하였습니다.");
        }
        return res;
    }

    /* 클럽 가입 결정 절차 */
    @ResponseBody
    @RequestMapping(value = "/{clubName}/member", method=RequestMethod.POST)
    public HashMap<String, Object> signUpDecision(@PathVariable String clubName, @RequestBody HashMap<String, Object> data) {
        // TODO : 클럽의 호스트만이 접근할 수 있는 경로
        HashMap<String, Object> res = new HashMap<>();
        String preMemberName = (String)data.get("preMemberName");
        boolean isApproved = (boolean)data.get("isApproved");
        if(!clubService.decideSignUp(preMemberName, clubName, isApproved)) {
            res.put("error", true);
            res.put("message", "가입" + (isApproved ? "승인" : "거절") + "에 실패하였습니다.");
        }
        else {
            res.put("error", false);
            res.put("message", preMemberName + "님의 가입을" + (isApproved ? "승인" : "거절") + "하였습니다.");
        }

        return res;
    }

    // TODO
    /* 읽은/을 책 목록에 도서 추가 */
    @ResponseBody
    @RequestMapping(value = {"/{clubName}/book"}, method=RequestMethod.POST)
    public HashMap<String, Object> newBookOnStack(
            @PathVariable String clubName, @RequestBody HashMap<String, Object> data) {
        return null;
    }


}
