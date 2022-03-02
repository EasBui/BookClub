package Dto.Review;

import Entity.Review.Review;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewWriteReq {
    private String title;
    private String content;
    private String ISBN;
    private String authorName;
    private String clubName;
    private LocalDateTime writtenDate;
    private int rate;

    public Review toReview() {
        return new Review(
                -1,
                this.title,
                this.content,
                this.ISBN,
                this.authorName,
                this.clubName,
                Timestamp.valueOf(this.writtenDate),
                this.rate
        );
    }
}
