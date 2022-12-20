package com.pension.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class PensionSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/checkApplication/{id}").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET, "/checkBalance/{id}").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET, "/checkstatus/{id}").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST, "/create/applicant").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/issuepension").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/loadpension").hasAnyRole("ADMIN")
                .and().csrf().disable().headers()
                .frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user123").password("{noop}password").roles("USER").and()
                .withUser("admin123").password("{noop}password").roles("ADMIN").and().withUser("test123")
                .password("{noop}password").roles("USER").and().withUser("ramashanker").password("{noop}password")
                .roles("ADMIN");
    }
}
