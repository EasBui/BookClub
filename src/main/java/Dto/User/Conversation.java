package Dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Conversation implements Comparable<Conversation>{
    private String counterpartName;
    private List<MessageRes> messages;
    private LocalDateTime lastActivatedTime;

    @Override
    public int compareTo(Conversation other) {
        if(this.lastActivatedTime.isBefore(other.getLastActivatedTime())) {
            return 1;
        }
        else if(this.lastActivatedTime.isAfter(other.getLastActivatedTime())) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
