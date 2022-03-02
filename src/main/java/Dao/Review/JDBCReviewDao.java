package Dao.Review;

import Entity.Review.Review;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JDBCReviewDao implements ReviewDao{
    @Autowired
    SqlSession sqlSession;

    private static final String NAMESPACE = "Mapper.Review.";

    @Override
    public List<Review> selectReviewsWithClub(String clubName) {
        return sqlSession.selectList(NAMESPACE + "selectReviewsWithClub", clubName);
    }

    @Override
    public List<Review> selectReviewsWithAuthor(String authorName) {
        return sqlSession.selectList(NAMESPACE + "selectReviewsWithAuthor", authorName);
    }

    @Override
    public Review selectReviewWithID(int ID) {
        return sqlSession.selectOne(NAMESPACE + "selectReviewWithID", ID);
    }

    @Override
    public boolean insertReview(Review review) {
        sqlSession.insert(NAMESPACE + "insertReview", review);
        return true;
    }

    @Override
    public boolean updateReview(Review review) {
        sqlSession.update(NAMESPACE + "updateReview", review);
        return true;
    }

    @Override
    public boolean deleteReview(int ID) {
        sqlSession.delete(NAMESPACE + "deleteReview", ID);
        return true;
    }
}
