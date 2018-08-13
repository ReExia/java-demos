package com.salty.mongo.repository;

import com.salty.mongo.document.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, String>{

    List<Article> findByTitle(String title);

    List<Article> findByTitleAndVisitCount(String title, Long visitCount);

    List<Article> findByTitle(String title, Pageable pageable);

    List<Article> findByTitle(String title, Sort sort);

    List<Article> findTop2ByTitle(String title);

}
