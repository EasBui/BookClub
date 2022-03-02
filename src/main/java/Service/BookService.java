package Service;

import Dto.Club.BookInfoRes;

public interface BookService {
    BookInfoRes searchBookInfoWithISBN(String ISBN);
}
