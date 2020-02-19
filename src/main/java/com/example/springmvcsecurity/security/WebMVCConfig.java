package com.example.springmvcsecurity.security;

import com.example.springmvcsecurity.service.UserDetailServiceImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@EnableWebSecurity
public class WebMVCConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailServiceImpl service;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebMVCConfig(UserDetailServiceImpl service, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.service = service;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
////                .withUser("kyaw")
////                .password("{noop}kyaw")
////                .roles("ADMIN","USER")
////                .and()
////                .withUser("thaw")
////                .password("{noop}thaw")
////                .roles("USER")
////                .and()
////                .withUser("Thomas")
////                .password("{noop}thomas")
////                .roles("USER");
        auth.userDetailsService(service).passwordEncoder(bCryptPasswordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();

    }
}
