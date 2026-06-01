package com.example.bookshelf.domain.repository;

import com.example.bookshelf.domain.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    void save(Book book);
    List<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
}
