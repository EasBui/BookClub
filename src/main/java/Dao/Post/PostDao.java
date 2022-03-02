package Dao.Post;

import Dto.Post.PostDto;

import java.util.List;

public interface PostDao {
    int postInsert(PostDto post);
    boolean postDelete(int id) ;
    PostDto postSelect(int id) ;
    int postUpdate(PostDto post);
    int latestIdSelect() ;
    int postCount() ;
    List<PostDto> postsSelect(int startPost, int amount);
}
