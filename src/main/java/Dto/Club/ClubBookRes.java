package Dto.Club;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClubBookRes {
    private BookInfoRes book;
    private int club_avg_rate;
    private boolean isRead;
}
