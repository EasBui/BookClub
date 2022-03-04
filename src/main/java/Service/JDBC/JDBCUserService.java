package Service.JDBC;

import Dao.User.UserDao;
import Dto.User.*;
import Entity.User.Message;
import Entity.User.User;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class JDBCUserService implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    /* 사용자 프로필 검색 */
    @Override
    public UserProfileRes searchUser(String userName) {
        return new UserProfileRes(userDao.selectUser(userName));
    }

    /* 사용자 등록 */
    @Override
    public boolean registerUser(User user) {
        user.setPassword("{bcrypt}"+passwordEncoder.encode(user.getPassword()));
        return userDao.insertUser(user);
    }

    /* 사용자 정보 갱신 */
    @Override
    public boolean updateUser(UserUpdateReq uur) {
        User user = uur.toUser(passwordEncoder);
        return userDao.updateUser(user);
    }

    /* 사용자 탈퇴 */
    @Override
    public boolean resignUser(String userName) {
        return userDao.deleteUser(userName);
    }

    /* 사용자 차단 */
    @Override
    public boolean blockUser(String userName) {
        return false;
    }

    /* 사용자의 대화 목록 검색 */
    @Override
    public List<Conversation> searchConversationsOf(String userName) {
        List<Message> messages = userDao.selectMessagesSubscribedBy(userName);
        Map<String, List<MessageRes>> msgClassifiedByCounterpart = new HashMap<>();
        messages.stream().forEach(message -> {
            String counterpart;
            if(userName.equals(message.getSenderName())) { // 사용자가 송신자인 경우
                counterpart = message.getReceiverName();
            }
            else { // 사용자가 수신자인 경우
                counterpart = message.getSenderName();
            }

            if(!msgClassifiedByCounterpart.containsKey(counterpart)) {
                msgClassifiedByCounterpart.put(counterpart, new ArrayList<MessageRes>());
            }
            msgClassifiedByCounterpart.get(counterpart).add(new MessageRes(message));
        });

        List<Conversation> conversations = new ArrayList<>();
        for(String key_counterpart : msgClassifiedByCounterpart.keySet()) {
            msgClassifiedByCounterpart.get(key_counterpart).sort(Comparator.reverseOrder()); // 가장 최근 메세지가 앞에 위치.
            conversations.add(
                new Conversation(
                    key_counterpart,
                    msgClassifiedByCounterpart.get(key_counterpart),
                    msgClassifiedByCounterpart.get(key_counterpart).get(0).getTime()
                )
            );
        }

        conversations.sort(Comparator.reverseOrder()); // 가장 최근 대화한 상대가 맨 앞으로 위치
        return conversations;
    }

    /* 메세지 저장 */
    @Override
    public boolean sendMessage(MessageReq messageReq) {
        userDao.insertMessage(messageReq.toMessage());
        return true;
    }

    /* 사용자(of)의 기존 메시지 중 특정 상대방(with)과의 메시지를 보이지 않게 끔 */
    @Override
    public boolean unsubscribeConversation(String of, String with) {
        return userDao.unsubscribeConversation(of, with);
    }

}
