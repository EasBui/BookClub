package Dto.Post;

import java.util.List;

public class PageDto {
    List<PostDto> postList;
    List<Integer> pageIndex;
    int currentPage;

    public PageDto(List<PostDto> postList, List<Integer> pageIndex, int currentPage) {
        this.postList = postList;
        this.pageIndex = pageIndex;
        this.currentPage = currentPage;
    }

    public List<PostDto> getPostList() {
        return postList;
    }

    public List<Integer> getPageIndex() {
        return pageIndex;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public String toString() {
        String res = "";
        res = res + "[Posts]\n";
        for(PostDto p : this.postList) {
            res = res + "* " + p.getTitle() +"\n";
        }
        res = res + "\n[Pagination]\n< ";
        for(int i : this.pageIndex) {
            res = res + (i == this.currentPage ? "*" + i : i) + " ";
        }
        res = res + ">";
        return res;
    }
}
