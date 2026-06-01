package com.example.bookshelf.infrastructure.api;

import com.example.bookshelf.domain.model.Book;
import java.util.Optional;

public interface ExternalBookApi {
    Optional<Book> fetchByIsbn(String isbn);
}
