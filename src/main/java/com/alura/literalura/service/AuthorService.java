package com.alura.literalura.service;

import com.alura.literalura.model.Author;
import com.alura.literalura.repository.AuthorRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    private final String BASE_URL = "https://gutendex.com/books?search=";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    Scanner scanner = new Scanner(System.in);
    //List<Author> searchedAuthors = new ArrayList<>();

    public Optional<Author> fetchAuthorByName(String author) {
        String url = BASE_URL + author.replace(" ", "+");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JsonNode root = objectMapper.readTree(response.body());
                JsonNode authorNode = root.path("results").get(0);
                System.out.println(authorNode);

                if (authorNode.isMissingNode()) {
                    return Optional.empty();
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        //String json = response.body();
        return Optional.empty();
    }
    public void listSearchedAuthors () {
        List<Author> authors = authorRepository.findAll();
        authors.forEach(author -> System.out.println(author.getName() + " - " + author.getBirthYear() + " - " + author.getDeathYear()));
    }

    public void listAliveAuthors() {
        System.out.println("Digite o ano para listar autores vivos:");
        int year = scanner.nextInt();
        List<Author> authorsAlive = authorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(year, year);
        authorsAlive.stream()
                .sorted(Comparator.comparing(Author::getName))
                .forEach(System.out::println);
    }
}



