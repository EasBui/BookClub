package Dao.User;

import Entity.User.Message;
import Entity.User.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JDBCUserDao implements UserDao {
    @Autowired
    SqlSession sqlSession;

    private static final String NAMESPACE = "Mapper.User.";

    @Override
    public User selectUser(String userName) {
        return sqlSession.selectOne(NAMESPACE + "selectUser", userName);
    }

    @Override
    public boolean deleteUser(String userName) {
        sqlSession.selectOne(NAMESPACE + "deleteUser", userName);
        return true;
    }

    @Override
    public boolean insertUser(User user) {
        sqlSession.selectOne(NAMESPACE + "insertUser", user);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        sqlSession.update(NAMESPACE + "updateUser", user);
        return true;
    }

    @Override
    public List<Message> selectMessagesSubscribedBy(String subscriberName) {
        return sqlSession.selectList(NAMESPACE + "selectMessages", subscriberName);
    }

    @Override
    public boolean insertMessage(Message message) {
        sqlSession.insert(NAMESPACE + "insertMessage", message);
        return false;
    }

    @Override
    public boolean unsubscribeConversation(String of, String with) {
        Map<String, String> params = new HashMap<>();
        params.put("of", of);
        params.put("with", with);
        sqlSession.update(NAMESPACE + "unsubscribeMessage", params);
        return false;
    }




}
