package Dao.Book;

import Dto.Club.BookAPIResult;
import Entity.Club.Book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@Repository
public class KaKaoApiBookDao implements BookDao {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Book seachBookWithISBN(String ISBN) {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://dapi.kakao.com/v3/search/book")
                .queryParam("target", "isbn")
                .queryParam("query", ISBN)
                .build().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK 626faaf3574a5cadf1b0576fd70378ae");
        headers.setAccept(Collections.singletonList(new MediaType("application", "json")));
        HttpEntity<?> request = new HttpEntity<Object>(headers);

        ResponseEntity<BookAPIResult> bookInfoResResponseEntity = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                request,
                BookAPIResult.class
        );

        BookAPIResult.Document doc = bookInfoResResponseEntity.getBody().getDocuments().get(0);

        return new Book(
                doc.getTitle(),
                doc.getAuthors(),
                doc.getPublisher(),
                doc.getThumbnail(),
                doc.getUrl(),
                ISBN
        );
    }
}
