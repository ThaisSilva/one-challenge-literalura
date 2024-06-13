package com.alura.literalura.service;

import com.alura.literalura.model.Book;
import com.alura.literalura.model.Languages;
import com.alura.literalura.repository.BookRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private final String BASE_URL = "https://gutendex.com/books?search=";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    Scanner scanner = new Scanner(System.in);
    ApiClientService apiClientService = new ApiClientService();
    ConvertsData converter = new ConvertsData();


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private Optional<Book> searchedBook;


    public Optional<Book> fetchBookByTitle(String title) {
        String url = BASE_URL + title.replace(" ", "+");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JsonNode root = objectMapper.readTree(response.body());
                JsonNode bookNode = root.path("results").get(0);
                System.out.println(bookNode);
                if (bookNode.isMissingNode()) {
                    return Optional.empty();
                }
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public void listBooksByLanguages() {
        System.out.println("""
                    Escolha o idioma do livro que deseja buscar:                
                    en - Inglês
                    es - Espanhol
                    fr - Francês                
                    pt - Português            
                    """);
        String text = scanner.nextLine();
        var language = Languages.fromString(text);
        List<Book> bookLanguage = bookRepository.findByLanguage(language);
        System.out.println("\n****** Livro(os) encontrado(os) para o idioma (" + language + ") ******");
        bookLanguage.forEach(b ->
                System.out.println("Título: " + b.getTitle() + "\n Autor: " + b.getAuthor() + "\nIdioma: " + b.getLanguage() + "Número de downloads: " + b.getDownloads()));
    }

    public void listSearchedBooks() {
        List<Book> booksList = bookRepository.findAll();
        booksList.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);
    }
}


    //    public void searchBook() {
//        System.out.println("Digite o título do livro:");
//        var bookTitle = scanner.nextLine();
//        searchedBook = bookRepository.findByTitleContainingIgnoreCase(bookTitle);
//
//        if (searchedBook.isPresent()) {
//            System.out.println("Dados do livro:" + searchedBook.get());
//        } else {
//            System.out.println("Livro não encontrado");
//        }
//    }


