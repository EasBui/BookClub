package Dto.Club;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClubDetailRes {
    private String name;
    private String memberCount;
    private String recentBookTitle;
}
