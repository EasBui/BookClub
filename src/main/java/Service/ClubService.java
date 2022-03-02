package Service;

import Dto.Club.BookInfoRes;
import Dto.Club.ClubBasicRes;
import Dto.Club.ClubBookRes;
import Entity.Club.Club;
import Entity.Review.Review;
import Entity.Club.Schedule;

import java.util.List;

public interface ClubService {
    Club searchClub(String clubName);
    List<ClubBasicRes> listClub();

    boolean addClub(Club club);
    boolean addSchedule(String clubName, Schedule schedule);

    boolean addReview(String clubName, Review review);


    List<ClubBookRes> getBookStack(String clubName);

    boolean addSignUp(String userName, String clubName);
    boolean decideSignUp(String userName, String clubName, boolean acceptance);
}
