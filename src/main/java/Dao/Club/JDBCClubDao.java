package Dao.Club;

import Dto.Club.ClubBookStackResult;
import Entity.Club.Book;
import Entity.Club.Club;
import Entity.Review.Review;
import Entity.Club.Schedule;
import Entity.User.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class JDBCClubDao implements ClubDao{
    @Autowired
    SqlSession sqlSession;

    private static final String NAMESPACE = "Mapper.Club.";

    @Override
    public List<Club> clubSelectAll() {
        return sqlSession.selectList(NAMESPACE + "selectAllClubs");
    }

    @Override
    public List<Club> clubSelectWithPattern(String pattern) {
        return sqlSession.selectList(NAMESPACE + "selectClubWithPattern", pattern);
    }

    @Override
    public Club clubSelect(String name) {
        return sqlSession.selectOne(NAMESPACE + "selectClub", name);
    }

    @Override
    public boolean clubInsert(Club club) {
        return false;
    }

    @Override
    public boolean clubDelete(String name) {
        return false;
    }

    @Override
    public boolean clubUpdate(Club club) {
        return false;
    }

    @Override
    public List<User> clubMemberSelect(String clubName) {

        return null;
    }

    @Override
    public boolean clubMemberInsert(String clubName, String userName) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("clubName", clubName);
        params.put("userName", userName);
        return false;
    }

    @Override
    public boolean clubMemberDelete(String clubName, String userName) {
        return false;
    }

    @Override
    public List<Review> reviewSelect(String clubName, Book book) {
        return null;
    }

    @Override
    public List<Review> reviewSelect(String clubName, String authorName) {
        return null;
    }

    @Override
    public List<Review> reviewSelect(String clubName, String authorName, Book book) {
        return null;
    }

    @Override
    public boolean reviewInsert(String clubName, Review review) {
        return false;
    }

    @Override
    public boolean reviewUpdate(String clubName, Review review) {
        return false;
    }

    @Override
    public boolean reviewDelete(int reviewID) {
        return false;
    }

    @Override
    public Schedule clubScheduleSelect(String clubName, int scheduleID) {
        return null;
    }

    @Override
    public boolean clubScheduleInsert(String clubName, Schedule schedule) {
        return false;
    }

    @Override
    public boolean clubScheduleUpdate(String clubName, Schedule schedule) {
        return false;
    }

    @Override
    public boolean clubScheduleDelete(String clubName, int scheduleID) {
        return false;
    }

    @Override
    public List<ClubBookStackResult> clubBookStackSelect(String clubName) {
        return sqlSession.selectList(NAMESPACE + "selectClubBookStack", clubName);
    }

    @Override
    public boolean clubBookStackInsert(int isbn) {
        return false;
    }

    @Override
    public boolean clubBookStackDelete(int isbn) {
        return false;
    }

    @Override
    public boolean insertSignUp(String userName, String clubName) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("userName", userName);
        params.put("clubName", clubName);
        sqlSession.insert(NAMESPACE + "insertClubSignUp", params);
        return true;
    }

    @Override
    public boolean deleteSignUp(String userName, String clubName) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("userName", userName);
        params.put("clubName", clubName);
        sqlSession.insert(NAMESPACE + "deleteClubSignUp", params);
        return true;
    }
}
