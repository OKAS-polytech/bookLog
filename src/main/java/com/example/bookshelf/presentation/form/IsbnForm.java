package com.example.bookshelf.presentation.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IsbnForm {
    @NotBlank
    private String isbn;
}
