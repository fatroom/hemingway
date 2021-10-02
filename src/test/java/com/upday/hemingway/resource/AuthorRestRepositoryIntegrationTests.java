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
public class AuthorRestRepositoryIntegrationTests {
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
    public void listAuthors() throws Exception {
        mvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("page.totalElements", is(equalTo(4))))
                .andExpect(jsonPath("_embedded.authors[0].name", is(equalTo("Mark Twain"))));
    }

    // Custom methods checks
    @Test
    public void checkAuthorValidation() throws Exception {
        mvc.perform(post("/authors")
                .header(HttpHeaders.AUTHORIZATION, "Basic " + new String(Base64.encode(("editor:passw0rd").getBytes())))
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Input data is invalid"));
    }

}
