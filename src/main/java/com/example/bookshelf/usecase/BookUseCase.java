package com.example.bookshelf.usecase;

import com.example.bookshelf.domain.model.Book;
import com.example.bookshelf.domain.repository.BookRepository;
import com.example.bookshelf.infrastructure.api.ExternalBookApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookUseCase {
    private final BookRepository bookRepository;
    private final ExternalBookApi externalBookApi;

    public Optional<Book> findExternalBook(String isbn) {
        return externalBookApi.fetchByIsbn(isbn);
    }

    public void registerBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
}
