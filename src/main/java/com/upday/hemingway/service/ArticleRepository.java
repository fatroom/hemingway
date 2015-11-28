package com.upday.hemingway.service;

import com.upday.hemingway.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RepositoryRestResource
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
    Page<Article> findByKeywords(@Param("keyword") String keyword, Pageable page);

    Page<Article> findByPublishDateBetween(@DateTimeFormat(iso = DATE) @Param("start") Date earliest,
                                           @DateTimeFormat(iso = DATE) @Param("end") Date latest,
                                           Pageable page);



}
