package Service.API;

import Dao.Book.BookDao;
import Dto.Club.BookInfoRes;
import Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* 카카오 책검색 API를 이용한 도서 검색 서비스 */
@Service
public class APIBookService implements BookService {
    @Autowired
    BookDao bookDao;

    @Override
    public BookInfoRes searchBookInfoWithISBN(String ISBN) {
        return new BookInfoRes(bookDao.seachBookWithISBN(ISBN));
    }
}
