package com.example.bookshelf.usecase;

import com.example.bookshelf.domain.model.Book;
import com.example.bookshelf.domain.repository.BookRepository;
import com.example.bookshelf.infrastructure.api.ExternalBookApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BookUseCaseTest {

    private BookUseCase bookUseCase;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ExternalBookApi externalBookApi;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookUseCase = new BookUseCase(bookRepository, externalBookApi);
    }

    @Test
    void testFindExternalBook() {
        String isbn = "9784048930734";
        Book book = Book.builder().isbn(isbn).title("Test Book").build();
        when(externalBookApi.fetchByIsbn(isbn)).thenReturn(Optional.of(book));

        Optional<Book> result = bookUseCase.findExternalBook(isbn);

        assertTrue(result.isPresent());
        assertEquals("Test Book", result.get().getTitle());
        verify(externalBookApi, times(1)).fetchByIsbn(isbn);
    }

    @Test
    void testRegisterBook() {
        Book book = Book.builder().isbn("123").title("Test Book").build();

        bookUseCase.registerBook(book);

        verify(bookRepository, times(1)).save(book);
    }
}
