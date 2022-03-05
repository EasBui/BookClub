package Dto.Club;

import Entity.Club.Club;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class ClubNewReq {
    private String name;
    private int hostID;
    private String description;
    private boolean isOpened;
    private String tags;

    public Club toClub() {
        List<String> parsedTags = null;
        if(this.tags.contains("#")) {
            // 공백 모두 삭제 후, # 문자 기준으로 나누고, 빈 문자열 삭제
            // TODO: 오로지 한글, 영어, 숫자, _ 기호만 넣을 수 있게
            parsedTags = Arrays.asList(this.tags.replaceAll(" ","").split("#")).stream()
                                .filter(t -> t.length() > 0).collect(Collectors.toList());
        }

        return new Club(
                -1,
                this.name,
                this.hostID,
                Timestamp.valueOf(LocalDateTime.now()),
                this.description,
                this.isOpened,
                parsedTags
        );
    }

}
