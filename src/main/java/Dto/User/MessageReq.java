package Dto.User;

import Entity.User.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MessageReq {
    private String senderName;
    private String receiverName;
    private String content;
    private LocalDateTime time;

    public Message toMessage() {
        return new Message(
                -1,
                this.senderName,
                true,
                this.receiverName,
                true,
                Timestamp.valueOf(this.time),
                this.content
        );
    }
}
