package com.upday.hemingway.resource;

import com.upday.hemingway.Hemingway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Hemingway.class)
@WebIntegrationTest(randomPort = true)
@ActiveProfiles("tests")
public class ArticleRestRepositoryIntegrationTests {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private FilterChainProxy filterChain;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).addFilters(filterChain).build();
    }

    // Data sanity check
    @Test
    public void listArticles() throws Exception {
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("page.totalElements", is(equalTo(5))))
                .andExpect(jsonPath("_embedded.articles[0].header", is(equalTo("The Awful German Language"))));
    }

    // Custom methods checks
    @Test
    public void findByDate() throws Exception {
        mvc.perform(get("/articles/search/findByPublishDateBetween")
                .param("start", "1880-01-01")
                .param("end", "1890-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.articles[0].header", is(equalTo("The Awful German Language"))));
    }

    @Test
    public void findByKeywords() throws Exception {
        mvc.perform(get("/articles/search/findByKeywords")
                .param("keyword", "tv"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("page.totalElements", is(equalTo(4))))
                .andExpect(jsonPath("_embedded.articles", hasSize(4)));
    }

    @Test
    public void checkArticleValidation() throws Exception {
        mvc.perform(post("/articles")
                .header(HttpHeaders.AUTHORIZATION, "Basic " + new String(Base64.encode(("editor:passw0rd").getBytes())))
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Input data is invalid"));
    }
}
