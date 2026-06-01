package com.example.bookshelf.presentation.form;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookRegistrationForm {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String publishedDate;
    private String coverUrl;
    private LocalDate startDate;
    private LocalDate endDate;
}
