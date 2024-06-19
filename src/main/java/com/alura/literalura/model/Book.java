package com.alura.literalura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Enumerated(EnumType.STRING)
    private Languages language;
    private double downloads;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(){
    }

    public Book(BookData data){
        this.title = data.title();
        this.author = data.author();
        this.language = data.language();
        // this.language = Languages.fromString(data.language().get(0));
        this.downloads = data.downloads();
    }

//    public Book(List<BookData> results) {
//        for (BookData data : results) {
//            Author author = new Author(data.authors()
//                    .get(0).name(), data.authors()
//                    .get(0).birthYear(), data.authors()
//                    .get(0).deathYear());
//            Book book = new Book(data, author);
//            author.getBooks().add(book);
//        }
//    }
//    public Book(String title, List<String> language, Double downloads,  List<AuthorData> authors) {
//        this.title = title;
//        this.language = Languages.fromString(language.get(0));
//        this.downloads = downloads;
//        Author author = new Author(authors.get(0)
//                .name(), authors.get(0)
//                .birthYear(), authors.get(0)
//                .deathYear());
//        this.author = author;
//    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Languages getLanguage() {
        return language;
    }

    public void setLanguage(Languages language) {
        this.language = language;
    }

    public double getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    @Override
    public String toString() {
        return "título = " + title + '\'' +
                ", autor = " + author +
                ", idioma = " + language + '\'' +
                ", número de downloads = " + downloads;
    }


}

