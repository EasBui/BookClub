package Dto.Review;

import Dto.Club.BookInfoRes;
import Entity.Review.Review;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReviewRes {
    private int ID;
    private String title;
    private String content;
    private String authorName;
    private LocalDateTime writtenDate;
    private int rate;
    private BookInfoRes book;

    public ReviewRes(Review review, BookInfoRes book) {
        this.ID = review.getID();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.authorName = review.getAuthorName();
        this.writtenDate = review.getWrittenDate().toLocalDateTime();
        this.rate = review.getRate();
        this.book = book;
    }
}
