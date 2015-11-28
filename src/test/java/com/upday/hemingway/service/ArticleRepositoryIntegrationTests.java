package com.upday.hemingway.service;

import com.upday.hemingway.Hemingway;
import com.upday.hemingway.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;

import static java.util.Calendar.JANUARY;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Hemingway.class)
@IntegrationTest
@ActiveProfiles("tests")
public class ArticleRepositoryIntegrationTests {
    @Autowired
    private ArticleRepository repository;

    // Data sanity check
    @Test
    public void findsFirstPageOfArticles() {
        Page<Article> articles = repository.findAll(new PageRequest(0, 3));
        assertThat(articles.getTotalElements(), is(greaterThan(3L)));
    }

    // Custom methods checks
    @Test
    public void findByPeriod() {
        Calendar earliest = Calendar.getInstance();
        earliest.set(1880, JANUARY, 1);

        Calendar latest = Calendar.getInstance();
        latest.set(1890, JANUARY, 1);

        Page<Article> articles = repository.findByPublishDateBetween(earliest.getTime(), latest.getTime(), new PageRequest(0, 3));
        assertThat(articles.getTotalElements(), is(equalTo(1L)));
    }

    @Test
    public void findByKeywords() {
        Page<Article> articles = repository.findByKeywords("tv", new PageRequest(0, 3));
        assertThat(articles.getTotalElements(), is(equalTo(4L)));
    }
}
