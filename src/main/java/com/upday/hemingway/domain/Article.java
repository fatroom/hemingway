package com.upday.hemingway.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
public class Article {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Header is required")
    @Column(nullable = false)
    private String header;

    @NotNull(message = "Short description is required")
    @Column(length = 1000, nullable = false)
    private String shortDescription;

    @NotNull(message = "Article text is required")
    @Column(length = 10000, nullable = false)
    private String text;

    private Date publishDate = new Date();

    @ElementCollection
    private Set<String> keywords;

    @NotNull(message = "At least one author is required")
    @ManyToMany
    private Set<Author> authors;

    public String getHeader() {
        return header;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getText() {
        return text;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public Set<Author> getAuthors() {
        return authors;
    }


}
