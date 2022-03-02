package Service.JDBC;

import Dao.Post.PostDao;
import Dto.Post.PageDto;
import Dto.Post.PostDto;
import Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service // 의미있는 Component 어노테이션
@Transactional
public class JDBCPostService implements PostService {
    @Autowired
    private PostDao postDao;


    @Override
    public PostDto searchPost(int id) {
        try {
            return postDao.postSelect(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("게시글 조회에 실패하였습니다.");
        }
    }

    @Override
    public int savePost(PostDto post, boolean isNew) {
        if(!isNew) {
            return postDao.postUpdate(post);
        }
        else {
            return postDao.postInsert(post);
        }
    }

    @Override
    public boolean deletePost(int id) {
        try {
            return postDao.postDelete(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("게시글 삭제에 실패하였습니다.");
        }
    }

    @Override
    public int searchLatestPostId() {
        try {
            return postDao.latestIdSelect();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("가장 최근 ID 조회에 실패하였습니다.");
        }
    }

    /**
     *
     * @param page 조회하려는 페이지
     * @param postPerPage 한 페이지에 포함되는 게시글 수
     * @param displayedPageAmount 페이지 인덱스에 나타나는 최대 페이지 수
     * @return
     */
    @Override
    public PageDto searchPage(int page, int postPerPage, int displayedPageAmount) {
        try {
            int postCount = postDao.postCount();
            int endPage = (postCount == 0) ? 0 : (postCount-1)/postPerPage; // 0 페이지로 시작할 때, 마지막 페이지 넘버

            if( page < 0 || page > endPage) { // 마지막 페이지 넘버보다 큰 넘버의 페이지는 조회 불가
                throw new RuntimeException("페이지 조회에 실패하였습니다.");
            }

            /** 게시글 목록 가져오기 */
            List<PostDto> postList = postDao.postsSelect(page*postPerPage, postPerPage);

            /** 페이지 인덱스 설정 */
            List<Integer> pageIndex = new ArrayList<>();
            int l_index = page-1;
            int r_index = page+1;
            if(0 <= page && page <= endPage) {
                pageIndex.add(page);
            }
            while(pageIndex.size() < displayedPageAmount &&
                                    (0 < l_index || r_index < endPage)) {
                if(0 <= l_index) {
                    pageIndex.add(l_index);
                    l_index--;
                }
                if(r_index <= endPage) {
                    pageIndex.add(r_index);
                    r_index++;
                }
            }

            pageIndex.sort(Comparator.naturalOrder());

            return new PageDto(postList, pageIndex, page);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("페이지 조회에 실패하였습니다.");
        }

    }
}
