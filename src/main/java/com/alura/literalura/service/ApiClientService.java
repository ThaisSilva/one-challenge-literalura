package com.alura.literalura.service;

import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ApiClientService {


    public String obterDados(String endereco) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();
        return json;
    }
}

//    private static final String URL = "https://gutendex.com/books/";
//    private final RestTemplate restClient;
//
//    @Autowired
//    public ApiClientService(RestTemplate restTemplate) {
//        this.restClient = restTemplate;
//    }
//
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
////    public Optional<Book> fetchBookByTitle(String title) {
////        String url = URL + "?title=" + title;
////        String body = restClient.get()
////                .uri(url)
////                .retrieve()
////                .body(String.class);
//
//    public Optional<Book> fetchBookByTitle(String title) {
//        String url = URL + "?title=" + title;
//        ResponseEntity<Book[]> responseEntity = restClient.getForEntity(url, Book[].class);
//        Book[] books = responseEntity.getBody();
//        if (books != null && books.length > 0) {
//            return Optional.of(books[0]);
//        } else {
//            return Optional.empty();
//        }
//    }
//
//    public Optional<Author> fetchAuthorByName(String name) {
//        String url = URL + "?author=" + name;
//        ResponseEntity<Book[]> responseEntity = restClient.getForEntity(url, Book[].class);
//        Book[] books = responseEntity.getBody();
//        if (books != null && books.length > 0) {
//            return Optional.of(books[0].getAuthor());
//        } else {
//            return Optional.empty();
//        }
//    }
//}
////    Book[] books = restClient.getForObject(url, Book[].class);
////    if (books != null && books.length > 0) {
////        return Optional.of(books[0]);
////    } else {
////        return Optional.empty();
////    }
////}
////    public Optional<Author> fetchAuthorByName(String name) {
////        String url = URL + "?author=" + name;
////        Book[] books = restClient.getForObject(url, Book[].class);
////        if (books != null && books.length > 0) {
////            return Optional.of(books[0].getAuthor());
//        } else {
//            return Optional.empty();
//        }
//    }
//}
//        try {
//            JsonNode root = objectMapper.readTree(body);
//            JsonNode bookNode = root.path("results").get(0);
//
//            Book book = objectMapper.treeToValue(bookNode, Book.class);
//            return Optional.of(book);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }