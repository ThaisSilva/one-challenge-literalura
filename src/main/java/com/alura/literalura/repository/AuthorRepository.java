package com.alura.literalura.repository;

import com.alura.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAll();

    List<Author> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(int birthYear, int deathYear);

//    Optional<Author> findByName(String name);
//
//    @Query("SELECT a.name, a.birthYear, a.deathYear FROM Author a WHERE a.id = :authorId")
//    List<Object[]> getAuthorData(@Param("autorId") Long autorId);
//
//    @Query("SELECT a FROM Autor a WHERE a.nome ILIKE %:partOfAuthorName%")
//    List<Author> authorByParteOfTheName(String authorByParteOfThename);
}