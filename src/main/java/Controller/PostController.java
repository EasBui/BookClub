package Controller;

import Dto.Post.PageDto;
import Dto.Post.PostDto;
import Security.CustomUserDetails;
import Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    /* 게시글 조회 */
    @GetMapping(value = {"", "/", "/{_targetPostId}"}) /* 특정 게시물 조회와 사이드 메뉴  */
    public String _post(Model model, @PathVariable Optional<Integer> _targetPostId, @RequestParam Optional<Integer> page) {
        /** 게시물 로직 */
        try {
            int postId = _targetPostId.orElse(postService.searchLatestPostId());
            PostDto post = postService.searchPost(postId);
            model.addAttribute("id", postId);
            model.addAttribute("title", post.getTitle());
            model.addAttribute("author", post.getAuthor());
            model.addAttribute("dateTime",
                    post.getLastModifiedDate() != null ?
                            "작성 :: " + post.getWrittenDate() + "수정 :: " + post.getLastModifiedDate()
                            : "작성 :: " + post.getWrittenDate());
            model.addAttribute("content", post.getContent());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "redirect:/";
        }

        /** 페이지 로직 */
        int targetPageNum = page.orElse(0);
        PageDto resultPage = postService.searchPage(targetPageNum, 7, 7);
        model.addAttribute("page", resultPage);
        return "post.post._";
    }

    @ResponseBody
    @DeleteMapping(value = {"", "/"}) /* 특정 게시물 삭제 */
    // delete 메소드는 바디가 없으므로 payload 혹은 URL 파라미터로 보내야 함. 그렇지 않으면 지원하지 않는 메소드예외로 튕겨버린다.
    // Post메소드를 제외한 메소드가 request body 부분을 파싱하려면 tomcat server.xml 부분에 수정.
    //  여기 참고 -> https://stackoverflow.com/questions/25375046/passing-data-in-the-body-of-a-delete-request
    public HashMap<String, String> deletePost(
            @RequestParam(required = true, value = "_targetPostId") Optional<String> _targetPostId) {
        HashMap<String, String> res = new HashMap<>();
        try {
            int targetPostId = _targetPostId.map(i -> Integer.valueOf(i)).get(); // if empty, NoSuchElementException
            if(postService.deletePost(targetPostId)) {
                res.put("error", "true");
            }
            else {
                res.put("error", "false");
            }
        } catch(NoSuchElementException e) {
            e.printStackTrace();
            res.put("error", "true");
        } catch (RuntimeException e) {
            e.printStackTrace();
            res.put("error", "true");
        }
        res.put("redirection", "/posts/");
        return res;
    }

    @GetMapping("/form/update/{_targetPostId}") /* 특정 게시글 수정 양식 조회 */
    public String updatePost(Model model, @PathVariable Optional<Integer> _targetPostId) {
        try {
            int targetPostId = _targetPostId.get(); // if empty, NoSuchElementException
            PostDto targetPost = postService.searchPost(targetPostId);
            model.addAttribute("id", targetPost.getId());
            model.addAttribute("title", targetPost.getTitle());
            model.addAttribute("content", targetPost.getContent());
            return "post.form.update";
        } catch(NoSuchElementException e) {
            e.printStackTrace();
        } catch(RuntimeException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }


    @RequestMapping(value = {"", "/", "/{_targetPostId}"}, method=RequestMethod.PUT) /* 특정 게시물 수정 */
    public String updatePost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @PathVariable Optional<Integer> _targetPostId) {
        try {
            int targetPostId = _targetPostId.get(); // if empty, NoSuchElementException
            PostDto targetPost = postService.searchPost(targetPostId);
            targetPost.setTitle(title);
            targetPost.setContent(content);
            targetPost.setLastModifiedDate(LocalDateTime.now());
            postService.savePost(targetPost, false);
            return "redirect:/posts/" + targetPostId;
        } catch(NoSuchElementException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/form/new") /* 신규 게시물 등록 양식 조회 */
    public String getNewForm() {
        return "post.form.new";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST) /* 신규 게시글 등록 */
    public String postNew(
            @RequestParam String title,
            @RequestParam String content,
            @AuthenticationPrincipal CustomUserDetails user) {
        try {
            int savedPostId = postService.savePost(new PostDto(
                    -1,
                    title,
                    user.getName(),
                    LocalDateTime.now(),
                    null,
                    content
            ), true);
            return "redirect:/posts/" + savedPostId;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "redirect:/";
        }

    }


}
