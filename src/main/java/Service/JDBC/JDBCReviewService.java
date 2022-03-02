package Service.JDBC;

import Dao.Book.BookDao;
import Dao.Club.ClubDao;
import Dao.Review.ReviewDao;
import Dto.Club.BookInfoRes;
import Dto.Club.ClubBookStackResult;
import Dto.Review.ReviewEditReq;
import Dto.Review.ReviewRes;
import Dto.Review.ReviewSummaryRes;
import Dto.Review.ReviewWriteReq;
import Entity.Review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Service.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JDBCReviewService implements ReviewService {
    @Autowired
    ReviewDao reviewDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    ClubDao clubDao;

    @Override
    public List<ReviewSummaryRes> searchReviewSummariesWithClub(String clubName) {
        return reviewDao.selectReviewsWithClub(clubName).stream().map( review -> {
            return new ReviewSummaryRes(review, new BookInfoRes(bookDao.seachBookWithISBN(review.getISBN())));
        }).collect(Collectors.toList());
    }

    @Override
    public List<ReviewSummaryRes> searchReviewSummariesWithAuthor(String authorName) {
        return reviewDao.selectReviewsWithAuthor(authorName).stream().map( review -> {
            return new ReviewSummaryRes(review, new BookInfoRes(bookDao.seachBookWithISBN(review.getISBN())));
        }).collect(Collectors.toList());
    }

    @Override
    public ReviewRes searchReviewWithID(int ID) {
        Review review = reviewDao.selectReviewWithID(ID);
        return new ReviewRes(review, new BookInfoRes(bookDao.seachBookWithISBN(review.getISBN())));
    }

    @Override
    public boolean addReviewInClub(ReviewWriteReq reviewWriteReq) {
        /*논리적으로, 클럽에서 읽지 않은 책을 등록할 수 없다.*/
        List<ClubBookStackResult> cbsrl = clubDao.clubBookStackSelect(reviewWriteReq.getClubName());
        if(!cbsrl.stream().filter( cbsr -> cbsr.getISBN().equals(reviewWriteReq.getISBN())).findAny().isPresent()) {
            return false;
        }
        /* TODO : 이미 리뷰를 작성한 도서인지 확인해야 함 */
        return reviewDao.insertReview(reviewWriteReq.toReview());
    }

    @Override
    public boolean editReview(ReviewEditReq reviewEditReq) {
        Review target = reviewDao.selectReviewWithID(reviewEditReq.getID());
        reviewEditReq.edit(target);
        return reviewDao.updateReview(target);
    }

    @Override
    public boolean removeReview(int ID) {
        return reviewDao.deleteReview(ID);
    }

    @Override
    public String searchBelongedClubOfReview(int ID) {
        Review review = reviewDao.selectReviewWithID(ID);
        return review.getClubName();
    }
}
