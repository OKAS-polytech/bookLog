package com.example.bookshelf.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String publishedDate;
    private String coverUrl;
    private LocalDate startDate;
    private LocalDate endDate;

    public boolean isCompleted() {
        return endDate != null;
    }
}
