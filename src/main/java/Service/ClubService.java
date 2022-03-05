package Service;

import Dto.Club.ClubBasicRes;
import Dto.Club.ClubBookRes;
import Dto.Club.ClubNewReq;
import Dto.User.UserProfileRes;
import Entity.Club.Club;
import Entity.Review.Review;
import Entity.Club.Schedule;

import java.util.List;

public interface ClubService {
    ClubBasicRes searchClub(String clubName);
    List<ClubBasicRes> listAllClub();
    List<ClubBasicRes> listClubWithTitle(String query);
    List<ClubBasicRes> listClubWithTags(String query);

    boolean addClub(ClubNewReq club);

    List<UserProfileRes> searchClubMembers(String clubName);

    List<UserProfileRes> searchClubSignUps(String clubName);

    boolean addSchedule(String clubName, Schedule schedule);

    List<ClubBookRes> getBookStack(String clubName);

    boolean addSignUp(String userName, String clubName);
    boolean decideSignUp(String userName, String clubName, boolean acceptance);

    List<ClubBasicRes> belongingClub(String userName);
}
