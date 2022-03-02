package Dto.Post;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class PostDto {
    private int id;
    private String title;
    private String author;
    private LocalDateTime writtenDate;
    private LocalDateTime lastModifiedDate;
    private String content;

    public PostDto(int id, String title, String author, LocalDateTime writtenDate,
                   LocalDateTime lastModifiedDate, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.writtenDate = writtenDate;
        this.lastModifiedDate = lastModifiedDate;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getWrittenDate() {
        return writtenDate;
    }

    public void setWrittenDate(LocalDateTime writtenDate) {
        this.writtenDate = writtenDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "" +
                "[title]" + this.title + "\n" +
                "[author]" + this.author + "\n" +
                "[writtenDate]" + this.writtenDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + "\n" +
                "[lastModifiedDate]" + Optional.ofNullable(this.lastModifiedDate)
                                               .map(lmd -> lmd.format(DateTimeFormatter.ISO_LOCAL_DATE))
                                               .orElse("null") + "\n" +
                "[content]" + this.content;
    }
}
