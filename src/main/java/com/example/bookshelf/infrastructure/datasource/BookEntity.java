package com.example.bookshelf.infrastructure.datasource;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    @Id
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String publishedDate;
    private String coverUrl;
    private LocalDate startDate;
    private LocalDate endDate;
}
