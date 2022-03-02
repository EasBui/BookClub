package Entity.Club;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Book {
    private String title;
    private List<String> authors;
    private String publisher;
    private String thumbnail;
    private String url;
    private String ISBN;
}
