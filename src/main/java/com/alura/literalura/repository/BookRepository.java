package com.alura.literalura.repository;

import com.alura.literalura.model.Book;
import com.alura.literalura.model.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

//    Optional<Book> findByTitleContainingIgnoreCase(String bookName);
//    @Query("SELECT a FROM Author a " +
//            "WHERE a.birthYear <= :year " +
//            "AND (a.deathYear IS NULL OR a.deathYear >= :year)")
//
//    List<Author> getAliveAuthorsThatYear(int year);

    List<Book> findByLanguage(Languages language);
}

