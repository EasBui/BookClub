package Dto.User;

import Entity.User.Message;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageRes implements Comparable<MessageRes>{
    private int ID;
    private String senderName;
    private String receiverName;
    private LocalDateTime time;
    private String content;

    public MessageRes(Message message) {
        this.ID = message.getID();
        this.senderName = message.getSenderName();
        this.receiverName = message.getReceiverName();
        this.time = message.getTime().toLocalDateTime();
        this.content= message.getContent();
    }

    @Override
    public int compareTo(MessageRes other) {
        if(this.time.isBefore(other.getTime())) {
            return 1;
        }
        else if(this.time.isAfter(other.getTime())) {
            return -1;
        }
        else {
           return 0;
        }
    }
}
