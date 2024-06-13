package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData (@JsonAlias({"title"}) String title,
                        @JsonAlias({"authors"}) List<AuthorData> authors,
                        @JsonAlias("languages") List<String> language,
                        @JsonAlias("download_count") Double downloads) {
    }