package com.example.bookshelf.infrastructure.api;

import com.example.bookshelf.domain.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OpenBdClient implements ExternalBookApi {
    private static final String API_URL = "https://api.openbd.jp/v1/get?isbn=";
    private final RestTemplate restTemplate;

    @Override
    public Optional<Book> fetchByIsbn(String isbn) {
        OpenBdResponse[] responses = restTemplate.getForObject(API_URL + isbn, OpenBdResponse[].class);
        if (responses == null || responses.length == 0 || responses[0] == null) {
            return Optional.empty();
        }

        OpenBdResponse response = responses[0];
        if (response.getSummary() == null) {
            return Optional.empty();
        }

        return Optional.of(Book.builder()
                .isbn(response.getSummary().getIsbn())
                .title(response.getSummary().getTitle())
                .author(response.getSummary().getAuthor())
                .publisher(response.getSummary().getPublisher())
                .publishedDate(response.getSummary().getPubdate())
                .coverUrl(response.getSummary().getCover())
                .build());
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OpenBdResponse {
        private Summary summary;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Summary {
        private String isbn;
        private String title;
        private String author;
        private String publisher;
        private String pubdate;
        private String cover;
    }
}
