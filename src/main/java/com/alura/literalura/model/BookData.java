package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData (@JsonAlias({"title"}) String title,
                        @JsonAlias({"authors"}) Author author,
                        @JsonAlias("languages") Languages language,
                        @JsonAlias("download_count") Double downloads) {

    public BookData(Book book) {
        this(book.getTitle(), book.getAuthor(), book.getLanguage(), book.getDownloads());
    }
    }