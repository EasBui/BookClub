package Entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int ID;
    private String senderName;
    private boolean senderSubscribe;
    private String receiverName;
    private boolean receiverSubscribe;
    private Timestamp time;
    private String content;
}
