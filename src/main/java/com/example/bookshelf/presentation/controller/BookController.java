package com.example.bookshelf.presentation.controller;

import com.example.bookshelf.domain.model.Book;
import com.example.bookshelf.presentation.form.BookRegistrationForm;
import com.example.bookshelf.presentation.form.IsbnForm;
import com.example.bookshelf.usecase.BookUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class BookController {
    private final BookUseCase bookUseCase;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("isbnForm", new IsbnForm());
        return "index";
    }

    @PostMapping("/confirm")
    public String confirm(@Validated @ModelAttribute IsbnForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "index";
        }
        Optional<Book> bookOpt = bookUseCase.findExternalBook(form.getIsbn());
        if (bookOpt.isEmpty()) {
            result.rejectValue("isbn", "error.isbn", "本が見つかりませんでした。");
            return "index";
        }

        Book book = bookOpt.get();
        BookRegistrationForm regForm = new BookRegistrationForm();
        regForm.setIsbn(book.getIsbn());
        regForm.setTitle(book.getTitle());
        regForm.setAuthor(book.getAuthor());
        regForm.setPublisher(book.getPublisher());
        regForm.setPublishedDate(book.getPublishedDate());
        regForm.setCoverUrl(book.getCoverUrl());

        model.addAttribute("bookRegistrationForm", regForm);
        return "confirm";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute BookRegistrationForm form) {
        Book book = Book.builder()
                .isbn(form.getIsbn())
                .title(form.getTitle())
                .author(form.getAuthor())
                .publisher(form.getPublisher())
                .publishedDate(form.getPublishedDate())
                .coverUrl(form.getCoverUrl())
                .startDate(form.getStartDate())
                .endDate(form.getEndDate())
                .build();
        bookUseCase.registerBook(book);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Book> books = bookUseCase.getAllBooks();
        model.addAttribute("books", books);
        return "list";
    }

    @PostMapping("/update-end-date")
    public String updateEndDate(@RequestParam String isbn, @RequestParam(required = false) String endDate) {
        bookUseCase.getBookByIsbn(isbn).ifPresent(book -> {
            if (endDate != null && !endDate.isEmpty()) {
                book.setEndDate(java.time.LocalDate.parse(endDate));
                bookUseCase.registerBook(book);
            }
        });
        return "redirect:/list";
    }
}
