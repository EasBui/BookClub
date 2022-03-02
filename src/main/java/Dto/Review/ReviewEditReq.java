package Dto.Review;

import Entity.Review.Review;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewEditReq {
    private int ID;
    private String title;
    private String content;
    private int rate;

    public void edit(Review target) {
        target.setTitle(this.title);
        target.setContent(this.content);
        target.setRate(this.rate);
    }

}
