package Controller;

import Dto.Club.BookInfoRes;
import Dto.Club.ClubBookRes;
import Entity.Club.Club;
import Security.CustomUserDetails;
import Service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/clubs")
public class ClubController {
    @Autowired
    ClubService clubService;

    // TODO : 페이지네이션 구현
    /* 전체 클럽의 소개 목록 */
    @RequestMapping(value = {"","/"}, method= RequestMethod.GET)
    public String clubList(Model model){
        model.addAttribute("clubs", clubService.listClub());
        return "club.list";
    }

    // TODO
    /* 클럽 생성 절차 */
    @RequestMapping(value = {"","/"}, method= RequestMethod.POST)
    public String createClub(@AuthenticationPrincipal CustomUserDetails thisUser,
                             @RequestParam Map<String,Object> params){
        return null;
    }

    /* 신규 클럽 생성 양식 */
    @RequestMapping(value = "/form/new", method = RequestMethod.GET)
    public String getNewClubForm(@AuthenticationPrincipal CustomUserDetails thisUser) {
        if(thisUser == null) {
            return "redirect:/form/login";
        }
        return "club.form.new";
    }

    // TODO: 클럽의 회원과 읽은 책 목록, 리뷰 등을 대문정보에 포함
    /* 특정 클럽 대문 */
    @RequestMapping(value = "/{clubname}", method= RequestMethod.GET)
    public String clubFacade(Model model, @PathVariable String clubname){
        List<ClubBookRes> books = clubService.getBookStack(clubname);
        model.addAttribute("books", books);
        return "club.facade._";
    }

    // TODO
    /* 클럽 정보 수정 절차 */
    @RequestMapping(value = "/{clubname}", method= RequestMethod.PUT)
    public String updateClubInfo(@PathVariable String clubname){
        return null;
    }

    // TODO
    /* 클럽 게시판 */
    @RequestMapping(value = "/{clubname}/{boardID}", method= RequestMethod.GET)
    public String clubBoard(@PathVariable String clubname, @PathVariable int boardID){
        return null;
    }

    // TODO
    /* 클럽 게시판에 글쓰기 */
    @RequestMapping(value = "/{clubname}/{boardID}", method= RequestMethod.POST)
    public String postClubBoard(@PathVariable String clubname, @PathVariable int boardID,
                                @RequestParam String title) {
        return null;
    }

    /* 회원가입 절차 */
    @RequestMapping(value = "/{clubName}/member", method=RequestMethod.POST)
    public String signUp(@PathVariable String clubName, @AuthenticationPrincipal CustomUserDetails user){
        return null;
    }

}
