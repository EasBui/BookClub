package Entity.Review;

import Entity.Club.Book;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private int ID;
    private String title;
    private String content;
    private String ISBN;
    private String authorName;
    private String clubName;
    private Timestamp writtenDate;
    private int rate;
}
