package com.sakher.gestiondestock.repository;

import com.sakher.gestiondestock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    /*@Query("select a from article where codearticle = :code and designation= :designation")
    List<Article> findByCustomQuery(@Param("code") String c,@Param("designation") String d);*/

    //JPQL
    @Query(value = "select * from article where code= :code",nativeQuery = true)
    List<Article> findByCustomNativeQuery(@Param("code") String c);


    // Hibernate can handle this query by searching attributes mentionned below
    // ignoreCase allows us to get data by ignoring case
    //List<Article> findByCodeArticleIgnoreCaseAndDesignationIgnoreCase(String codeArticle,String designation);

    Optional<Article> findByCodeArticle(String codeArticle);
}
