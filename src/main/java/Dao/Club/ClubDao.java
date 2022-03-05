package Dao.Club;

import Dto.Club.ClubBookStackResult;
import Entity.Club.Book;
import Entity.Club.Club;
import Entity.Review.Review;
import Entity.Club.Schedule;
import Entity.User.User;

import java.util.List;

public interface ClubDao {
    List<Club> clubSelectAll();
    List<Club> clubSelectWithTitleQuery(String pattern);
    List<Club> clubSelectWithTags(String tagString);
    Club clubSelect(String name);
    boolean clubInsert(Club club);
    boolean clubDelete(String name);
    boolean clubUpdate(Club club);

    List<User> clubMemberSelect(String clubName);
    boolean clubMemberInsert(String clubName, String userName);
    boolean clubMemberDelete(String clubName, String userName);
    User clubHostSelect(String clubName);


    Schedule clubScheduleSelect(String clubName, int scheduleID);
    boolean clubScheduleInsert(String clubName, Schedule schedule);
    boolean clubScheduleUpdate(String clubName, Schedule schedule);
    boolean clubScheduleDelete(String clubName, int scheduleID);

    List<ClubBookStackResult> clubBookStackSelect(String clubName);
    boolean clubBookStackInsert(int isbn);
    boolean clubBookStackDelete(int isbn);

    List<User> selectSignUps(String clubName);
    boolean insertSignUp(String userName, String clubName);
    boolean deleteSignUp(String userName, String clubName);

    List<Club> selectClubWithMember(String userName);
}
