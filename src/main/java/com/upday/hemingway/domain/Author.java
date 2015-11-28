package com.upday.hemingway.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Author name is required")
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "authors")
    private Set<Article> articles;

    public String getName() {
        return name;
    }

}
