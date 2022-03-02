package Dao.User;


import Entity.User.Message;
import Entity.User.User;

import java.util.List;

public interface UserDao {
    User selectUser(String username);
    boolean deleteUser(String username);
    boolean insertUser(User user);
    boolean updateUser(User user);

    //List<Message> selectMessagesTo(String recieverName);
    //List<Message> selectMessagesFrom(String senderName);
    //List<Message> selectMessagesBetween(String senderName, String recieverName);
    List<Message> selectMessagesSubscribedBy(String subscriberName);
    boolean insertMessage(Message message);
    boolean unsubscribeConversation(String of, String with);

}
