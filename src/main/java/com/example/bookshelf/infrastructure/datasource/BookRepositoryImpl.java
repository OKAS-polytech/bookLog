package com.example.bookshelf.infrastructure.datasource;

import com.example.bookshelf.domain.model.Book;
import com.example.bookshelf.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final JpaBookRepository jpaBookRepository;

    @Override
    public void save(Book book) {
        jpaBookRepository.save(toEntity(book));
    }

    @Override
    public List<Book> findAll() {
        return jpaBookRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return jpaBookRepository.findById(isbn).map(this::toDomain);
    }

    private BookEntity toEntity(Book book) {
        return BookEntity.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publishedDate(book.getPublishedDate())
                .coverUrl(book.getCoverUrl())
                .startDate(book.getStartDate())
                .endDate(book.getEndDate())
                .build();
    }

    private Book toDomain(BookEntity entity) {
        return Book.builder()
                .isbn(entity.getIsbn())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .publisher(entity.getPublisher())
                .publishedDate(entity.getPublishedDate())
                .coverUrl(entity.getCoverUrl())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();
    }
}
