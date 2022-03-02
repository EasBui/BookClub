package Dto.Club;

import Entity.Club.Club;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class ClubBasicRes {
    private String name;
    private String description;
    private int hostID;
    private List<String> tags;
    private LocalDateTime foundationDate;

    public ClubBasicRes(Club club) {
        this.name = club.getName();
        this.description = club.getDescription();
        this.hostID = club.getHostID();
        this.tags = club.getTags();
        this.foundationDate = club.getFoundationDate().toLocalDateTime();
    }
}
