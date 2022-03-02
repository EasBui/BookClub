package Dto.Club;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ClubBookStackResult {
    private String ISBN;
    private boolean isRead;
    private int averageRate;
}
