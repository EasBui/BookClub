package Service;

import Dto.User.Conversation;
import Dto.User.MessageReq;
import Dto.User.UserProfileReq;
import Dto.User.UserUpdateReq;
import Entity.User.User;

import java.util.List;

public interface UserService {
    UserProfileReq searchUser(String username);
    boolean registerUser(User user);
    boolean updateUser(UserUpdateReq user);
    boolean resignUser(String username);
    boolean blockUser(String account);




    List<Conversation> searchConversationsOf(String UserName);
    boolean sendMessage(MessageReq messageReq);
    boolean unsubscribeConversation(String of, String with);
}
