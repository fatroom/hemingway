package com.upday.hemingway.model;

import com.upday.hemingway.Hemingway;
import com.upday.hemingway.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Hemingway.class)
public class ArticleTests {
    @Autowired
    private Validator validator;

    @Test
    public void checkValidation() {
        Set<ConstraintViolation<Article>> violations = validator.validate(new Article());
        assertThat(violations.size(), is(equalTo(4)));
        assertThat(violations, hasItem(hasProperty("message", is(equalTo("Article text is required")))));
        assertThat(violations, hasItem(hasProperty("message", is(equalTo("At least one author is required")))));
        assertThat(violations, hasItem(hasProperty("message", is(equalTo("Short description is required")))));
        assertThat(violations, hasItem(hasProperty("message", is(equalTo("Header is required")))));
    }
}
