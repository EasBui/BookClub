package Dao.Book;

import Entity.Club.Book;

public interface BookDao {
    Book seachBookWithISBN(String ISBN);
}
