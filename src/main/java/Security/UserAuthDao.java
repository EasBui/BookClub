package Security;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserAuthDao {
    @Autowired
    SqlSession sqlSession;

    private final static String NAMESPACE = "Mapper.User.";

    public CustomUserDetails getUserByAccount(String account) {
        return sqlSession.selectOne(NAMESPACE + "selectUserDetails", account);
    }
}
