package Controller;

import Dto.Review.ReviewEditReq;
import Dto.Review.ReviewRes;
import Dto.Review.ReviewWriteReq;
import Security.CustomUserDetails;
import Service.BookService;
import Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    BookService bookService;


    /* Helpers */
    private boolean authorCheck(CustomUserDetails user, int ID) {
        ReviewRes target = reviewService.searchReviewWithID(ID);
        return (target.getAuthorName().equals(user.getName()));
    }

    private final int OUTSIDER = 0;
    private final int MEMBER = 1;
    private final int HOST = 2;

    /* Controller */

    /* 리뷰 조회 */
    @RequestMapping(value = {"/{ID}", ""}, method = RequestMethod.GET)
    public String readReview(Model model, @PathVariable int ID) {

        model.addAttribute("review", reviewService.searchReviewWithID(ID));
        return "review.review";
    }

    /* 리뷰 등록 양식 */
    @RequestMapping(value = {"/write"}, method = RequestMethod.GET)
    public String writeForm(
            Model model,
            @RequestParam String clubName,
            @RequestParam String ISBN,
            @AuthenticationPrincipal CustomUserDetails user) {
        model.addAttribute("clubName", clubName);
        model.addAttribute("book", bookService.searchBookInfoWithISBN(ISBN));
        return "review.write";
    }

    /* 리뷰 등록 절차 */
    @ResponseBody
    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public HashMap<String, Object> postReview(
            @RequestBody String ISBN,
            @RequestBody String clubName,
            @RequestBody String title,
            @RequestBody String content,
            @RequestBody int rate,
            @AuthenticationPrincipal CustomUserDetails user) {
        HashMap<String, Object> res = new HashMap<>();
        /* 개인 리뷰이거나, 리뷰를 작성하고자 하는 클럽의 회원일 경우에만 작성 가능 */
        if(clubName == null || (user.roleCheckOn(clubName) >= MEMBER)) {
            reviewService.addReviewInClub(new ReviewWriteReq(
                    title,
                    content,
                    ISBN,
                    user.getName(),
                    clubName,
                    LocalDateTime.now(),
                    rate
            ));
            res.put("error", false);
            res.put("redirect", "/");
        }
        else {
            res.put("error", true);
            res.put("message", "게시글 작성 권한이 없습니다.");
            res.put("redirect", "/");
        }
        return null;
    }

    /* 리뷰 수정 절차 */
    @ResponseBody
    @RequestMapping(value = {"/{ID}"}, method = RequestMethod.PUT)
    public HashMap<String, Object> editReview(
            @PathVariable int ID,
            @RequestBody String title,
            @RequestBody String content,
            @RequestBody int rate,
            @AuthenticationPrincipal CustomUserDetails user) {
        HashMap<String, Object> res = new HashMap<>();
        /* 작성자만이 수정가능 */
        if(authorCheck(user, ID)) {
            reviewService.editReview(new ReviewEditReq(
                    ID,
                    title,
                    content,
                    rate
            ));
            res.put("error", false);
            res.put("redirect", "/");
        }
        else {
            res.put("error", true);
            res.put("messsage", "해당 리뷰에 대한 수정 권한이 없습니다. ");
        }
        return res;
    }

    /* 리뷰 삭제 절차 */
    @ResponseBody
    @RequestMapping(value = {"/{ID}"}, method = RequestMethod.DELETE)
    public  HashMap<String, Object> removeReview(
            @PathVariable Integer ID,
            @AuthenticationPrincipal CustomUserDetails user) {
        HashMap<String, Object> res = new HashMap<>();
        /* 작성자 혹은 해당 클럽장만이 삭제 가능 */
        String belongedClubName = reviewService.searchBelongedClubOfReview(ID); /* -> 좋은 설계인가...? */
        if(authorCheck(user, ID) || (user.roleCheckOn(belongedClubName) >= HOST)) {
            reviewService.removeReview(ID);
            res.put("error", false);
            res.put("redirect", "/");
        }
        else {
            res.put("error", true);
            res.put("messsage", "해당 리뷰에 대한 삭제 권한이 없습니다. ");
        }
        return res;
    }

}
