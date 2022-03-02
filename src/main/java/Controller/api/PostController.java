package Controller.api;

import Dto.Post.PageDto;
import Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/posts/")
@RestController("apiNoticeController") // RESTful한 반환. 괄호는 이미 있는 noticeController랑 빈 id 안겹치게 명시.
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping("readme")
    public String readme(){
        return "API 데이터 교환 테스트 페이지";
    }

    @RequestMapping("list")
    public PageDto list() { // 객체를 별다른 처리없이 반환해도, Spring이 JSON으로 변환해 response body로 설정한다.
        PageDto page = postService.searchPage(1, 7,7);
        return page;
    }

    /**
     * URL: www.domain.com/api/posts/query-string?val1=hi&val2=2
     */
    @RequestMapping("query-string")
    public String queryString(String val1, @RequestParam(name="val2", defaultValue="200") Integer val2_other ) {
        //int 가 아닌 Integer인 이유는 파라미터가 안 들어올 경우 null 값을 담아야 하기 때문.
        return val1 + val2_other;
    }
}
