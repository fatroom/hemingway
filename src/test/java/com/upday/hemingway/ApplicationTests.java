package com.upday.hemingway;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Hemingway.class)
@WebIntegrationTest(randomPort = true)
@ActiveProfiles("tests")
public class ApplicationTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FilterChainProxy filterChain;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(filterChain)
                .build();
    }

    @Test
    public void testHome() throws Exception {
        mvc.perform(get("/").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("articles")))
                .andExpect(content().string(containsString("authors")));
    }

    @Test
    public void checkUnauthorizedAccess() throws Exception {
        mvc.perform(post("/articles")
                .content("{}"))
                .andExpect(status().isUnauthorized());
        mvc.perform(put("/articles/1")
                .content("{}"))
                .andExpect(status().isUnauthorized());
        mvc.perform(patch("/articles/1")
                .content("{}"))
                .andExpect(status().isUnauthorized());
        mvc.perform(delete("/articles/1")
                .content("{}"))
                .andExpect(status().isUnauthorized());
    }

}
