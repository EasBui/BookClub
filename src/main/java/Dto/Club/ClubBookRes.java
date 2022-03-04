package Dto.Club;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClubBookRes {
    private BookInfoRes bookInfo;
    private int clubAvgRate;
    private boolean isRead;
}
