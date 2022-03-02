package Service;

import Dto.Review.ReviewEditReq;
import Dto.Review.ReviewRes;
import Dto.Review.ReviewSummaryRes;
import Dto.Review.ReviewWriteReq;

import java.util.List;

public interface ReviewService {
    List<ReviewSummaryRes> searchReviewSummariesWithClub(String clubName);
    List<ReviewSummaryRes> searchReviewSummariesWithAuthor(String authorName);
    ReviewRes searchReviewWithID(int ID);
    boolean addReviewInClub(ReviewWriteReq reviewWriteReq);
    boolean editReview(ReviewEditReq reviewEditReq);
    boolean removeReview(int ID);
    String searchBelongedClubOfReview(int ID);

}
