package Dao.Book;

import Dto.Club.BookAPIResult;
import Entity.Club.Book;


import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.util.Collections;
import java.util.Properties;

@Repository
public class KaKaoApiBookDao implements BookDao {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Book seachBookWithISBN(String ISBN) {
        String resource = "kakao.properties";
        Properties properties = new Properties();
        try {
            Reader reader = Resources.getResourceAsReader("kakao.properties");
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("카카오 프로퍼티를 읽는데 문제 생김");
        }

        URI uri = UriComponentsBuilder.fromHttpUrl("https://dapi.kakao.com/v3/search/book")
                .queryParam("target", "isbn")
                .queryParam("query", ISBN)
                .build().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", properties.getProperty("api.key"));
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
