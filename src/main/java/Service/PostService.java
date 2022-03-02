package Service;

import Dto.Post.PageDto;
import Dto.Post.PostDto;


public interface PostService {
    PostDto searchPost(int id);
    int savePost(PostDto post, boolean isNew);
    boolean deletePost(int id);

    int searchLatestPostId();

    /** 페이지 제공 */
    PageDto searchPage(int page, int postPerPage, int displayedPageAmount);
}
