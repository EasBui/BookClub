package Service.JDBC;

import Dao.Book.BookDao;
import Dao.Club.ClubDao;
import Dto.Club.BookInfoRes;
import Dto.Club.ClubBasicRes;
import Dto.Club.ClubBookRes;
import Dto.Club.ClubBookStackResult;
import Entity.Club.Book;
import Entity.Club.Club;
import Entity.Review.Review;
import Entity.Club.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Service.ClubService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JDBCClubService implements ClubService {
    @Autowired
    ClubDao clubDao;

    @Autowired
    BookDao bookDao;

    // TODO: DTO로 포장해서 반환하기
    /* 클럽 이름으로 정보를 검색 */
    @Override
    public Club searchClub(String clubName) {
        return clubDao.clubSelect(clubName);
    }

    /* 클럽 요약 정보를 모두 반환 */
    @Override
    public List<ClubBasicRes> listClub() {
        return clubDao.clubSelectAll().stream()
                .filter(c -> c.isOpened()) /* 공개된 클럽만 */
                .map(c -> new ClubBasicRes(c))
                .collect(Collectors.toList());
    }

    // TODO
    /* 신규 클럽을 리포지터리에 추가 */
    @Override
    public boolean addClub(Club club) {
        return false;
    }

    // TODO
    /* 클럽의 새로운 일정을 리포지터리에 추가 */
    @Override
    public boolean addSchedule(String clubName, Schedule schedule) {
        return false;
    }

    // TODO
    /* 새로운 리뷰를 추가 */
    @Override
    public boolean addReview(String clubName, Review review) {
        return false;
    }

    /* 클럽에서 읽은 책 정보를 가져옴 */
    @Override
    public List<ClubBookRes> getBookStack(String clubName) {
        List<ClubBookRes> clubBookResList = new ArrayList<>();

        for(ClubBookStackResult cbsr : clubDao.clubBookStackSelect(clubName)) {
            Book book = bookDao.seachBookWithISBN(cbsr.getISBN());

            clubBookResList.add(new ClubBookRes(new BookInfoRes(book), cbsr.getAverageRate(), cbsr.isRead()));
        }
        return clubBookResList;
    }

    /* 클럽의 가입 신청 목록에 유저를 추가 */
    @Override
    public boolean addSignUp(String userName, String clubName) {
        clubDao.insertSignUp(userName, clubName);
        return true;
    }

    /* 클럽의 가입 신청 목록에 있는 유저의 가입 승인 여부를 결정 */
    @Override
    public boolean decideSignUp(String userName, String clubName, boolean acceptance) {
        if(acceptance) {
            clubDao.clubMemberInsert(userName, clubName);
        }
        clubDao.deleteSignUp(userName, clubName);
        return true;
    }
}
