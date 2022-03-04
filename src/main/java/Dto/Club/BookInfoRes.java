package Dto.Club;

import Entity.Club.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Data
public class BookInfoRes {
    private String title;
    private List<String> authors;
    private String publisher;
    private String thumbnail;
    private String ISBN;
    private String url;

    public BookInfoRes(Book book) {
        this.title = book.getTitle();
        this.authors = book.getAuthors();
        this.publisher = book.getPublisher();
        this.thumbnail = book.getThumbnail();
        this.ISBN = book.getISBN();
        this.url = book.getUrl();
    }
}
