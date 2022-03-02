package Entity.Club;

import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@ToString
public class Club {
    private int ID;
    private String name;
    private int hostID;
    private Timestamp foundationDate;
    private String description;
    private boolean isOpened;
    @Singular
    private List<String> tags;
}
