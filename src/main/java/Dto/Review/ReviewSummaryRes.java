package Dto.Review;

import Dto.Club.BookInfoRes;
import Entity.Review.Review;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ReviewSummaryRes {
    private int ID;
    private String title;
    private String authorName;
    private BookInfoRes book;
    private LocalDateTime writtenDate;
    private int rate;

    public ReviewSummaryRes(Review review, BookInfoRes book) {
        this.ID = review.getID();
        this.title = review.getTitle();
        this.authorName = review.getAuthorName();
        this.writtenDate = review.getWrittenDate().toLocalDateTime();
        this.rate = review.getRate();
        this.book = book;
    }
}
