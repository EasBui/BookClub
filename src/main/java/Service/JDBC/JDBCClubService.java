package Service.JDBC;

import Dao.Book.BookDao;
import Dao.Club.ClubDao;
import Dao.User.UserDao;
import Dto.Club.*;
import Dto.User.UserProfileRes;
import Entity.Club.Book;
import Entity.Club.Club;
import Entity.Review.Review;
import Entity.Club.Schedule;
import Entity.User.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Service.ClubService;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    @Autowired
    UserDao userDao;

    // TODO: DTO로 포장해서 반환하기
    /* 클럽 이름으로 정보를 검색 */
    @Override
    public ClubBasicRes searchClub(String clubName) {
        return new ClubBasicRes(clubDao.clubSelect(clubName));
    }

    /* 모든 '클럽 요약 정보' 반환 */
    @Override
    public List<ClubBasicRes> listAllClub() {
        return clubDao.clubSelectAll().stream()
                .filter(c -> c.isOpened()) /* 공개된 클럽만 */
                .map(c -> new ClubBasicRes(c))
                .collect(Collectors.toList());
    }

    /* 제목에 특정 단어가 들어간 클럽의 `클럽 요약 정보` 반환 */
    @Override
    public List<ClubBasicRes> listClubWithTitle(String query) {
        return clubDao.clubSelectWithTitleQuery(query).stream()
                .filter(c -> c.isOpened()) /* 공개된 클럽만 */
                .map(c -> new ClubBasicRes(c))
                .collect(Collectors.toList());
    }

    /* 특정태그를 포함하는 클럽의 `클럽 요약 정보` 반환 */
    @Override
    public List<ClubBasicRes> listClubWithTags(String tagString) {
        return clubDao.clubSelectWithTags(tagString).stream()
                .filter(c -> c.isOpened()) /* 공개된 클럽만 */
                .map(c -> new ClubBasicRes(c))
                .collect(Collectors.toList());
    }


    /* 신규 클럽을 리포지터리에 추가 */
    @Override
    public boolean addClub(ClubNewReq clubNewReq) {
        clubDao.clubInsert(clubNewReq.toClub());
        return true;
    }

    /* 클럽 멤버 조회 */
    @Override
    public List<UserProfileRes> searchClubMembers(String clubName) {
        return clubDao.clubMemberSelect(clubName).stream()
                .map(user -> new UserProfileRes(user)).collect(Collectors.toList());
    }

    /* 클럽 가입희망자 조회 */
    @Override
    public List<UserProfileRes> searchClubSignUps(String clubName) {
        return clubDao.selectSignUps(clubName).stream()
                .map(user -> new UserProfileRes(user)).collect(Collectors.toList());
    }

    /* 클럽의 가입 신청 목록에 유저를 추가 */
    @Override
    public boolean addSignUp(String userName, String clubName) {
        return clubDao.insertSignUp(userName, clubName);
    }

    /* 클럽의 가입 신청 목록에 있는 유저의 가입 승인 여부를 결정 */
    @Override
    public boolean decideSignUp(String userName, String clubName, boolean acceptance) {
        userDao.insertMessage(new Message(
                -1,
                clubDao.clubHostSelect(clubName).getName(),
                true,
                userName,
                true,
                Timestamp.valueOf(LocalDateTime.now()),
                "<" + clubName + "> 클럽의 가입이 " + (acceptance ? "승인" : "거절") + "되었습니다"
        ));
        if(acceptance) {
            return clubDao.clubMemberInsert(userName, clubName);
        }
        return clubDao.deleteSignUp(userName, clubName);
    }

    // TODO
    /* 클럽의 새로운 일정을 리포지터리에 추가 */
    @Override
    public boolean addSchedule(String clubName, Schedule schedule) {
        return false;
    }

    /* 클럽에서 읽은 책 정보를 가져옴 */
    @Override
    public List<ClubBookRes> getBookStack(String clubName) {
        List<ClubBookRes> clubBookResList = new ArrayList<>();
        List<ClubBookStackResult> cbsrs = clubDao.clubBookStackSelect(clubName);
        for(ClubBookStackResult cbsr : cbsrs) {
            Book book = bookDao.seachBookWithISBN(cbsr.getISBN());
            clubBookResList.add(new ClubBookRes(new BookInfoRes(book), cbsr.getAverageRate(), cbsr.isRead()));
        }
        return clubBookResList;
    }


    /* 사용자가 가입한 클럽 정보 */
    @Override
    public List<ClubBasicRes> belongingClub(String userName) {
        return clubDao.selectClubWithMember(userName).stream()
                .filter(c -> c != null).map(c -> new ClubBasicRes(c)).collect(Collectors.toList());
    }
}
