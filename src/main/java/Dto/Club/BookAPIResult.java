package Dto.Club;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class BookAPIResult {
    private List<Document> documents;
    private Meta meta;

    @Getter
    @Setter
    @ToString
    public static class Document {
        private List<String> authors;
        private String contents;
        private LocalDateTime dateTime;
        private String isbn;
        private int price;
        private String publisher;
        private String status;
        private String thumbnail;
        private String title;
        private List<String> translators;
        private String url;
    }

    @Getter
    @Setter
    @ToString
    public static class Meta {
        private boolean isEnd;
        private int pageableCount;
        private int totalCount;
    }
}
