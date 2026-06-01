package com.example.bookshelf;

import com.example.bookshelf.domain.model.Book;
import com.example.bookshelf.domain.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testRegisterAndList() throws Exception {
        // 1. Register a book
        mockMvc.perform(post("/register")
                .param("isbn", "9784048930734")
                .param("title", "Test Book")
                .param("author", "Test Author")
                .param("startDate", "2023-01-01"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/list"));

        // 2. Verify in list
        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Test Book")));
    }

    @Test
    void testUpdateEndDate() throws Exception {
        // Pre-condition: a book exists
        bookRepository.save(Book.builder()
                .isbn("1112223334445")
                .title("Update Test")
                .startDate(LocalDate.now())
                .build());

        // Update end date
        mockMvc.perform(post("/update-end-date")
                .param("isbn", "1112223334445")
                .param("endDate", "2023-12-31"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/list"));

        // Verify update
        mockMvc.perform(get("/list"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("2023-12-31")));
    }
}
