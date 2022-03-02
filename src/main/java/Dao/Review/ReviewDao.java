package Dao.Review;

import Entity.Review.Review;

import java.util.List;

public interface ReviewDao {
    List<Review> selectReviewsWithClub(String clubName);
    List<Review> selectReviewsWithAuthor(String authorName);
    Review selectReviewWithID(int ID);
    boolean insertReview(Review review);
    boolean updateReview(Review review);
    boolean deleteReview(int ID);
}
