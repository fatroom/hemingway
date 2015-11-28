package com.upday.hemingway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("editor").password("passw0rd").roles("EDITOR")
                .and()
                .withUser("admin").password("s3cr3t").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.POST).hasAnyRole("EDITOR", "ADMIN")
                .antMatchers(HttpMethod.PUT).hasAnyRole("EDITOR", "ADMIN")
                .antMatchers(HttpMethod.PATCH).hasAnyRole("EDITOR", "ADMIN")
                .antMatchers(HttpMethod.DELETE).hasAnyRole("EDITOR", "ADMIN").and().
                csrf().disable();
    }
}
