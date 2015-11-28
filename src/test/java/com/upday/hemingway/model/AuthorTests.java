package com.upday.hemingway.model;

import com.upday.hemingway.Hemingway;
import com.upday.hemingway.domain.Author;
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
public class AuthorTests {
    @Autowired
    private Validator validator;

    @Test
    public void checkValidation() {
        Set<ConstraintViolation<Author>> violations = validator.validate(new Author());
        assertThat(violations.size(), is(equalTo(1)));
        assertThat(violations, hasItem(hasProperty("message", is(equalTo("Author name is required")))));
    }
}
