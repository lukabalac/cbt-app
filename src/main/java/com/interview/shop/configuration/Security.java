package com.interview.shop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class Security extends WebSecurityConfigurerAdapter {


  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .ignoringAntMatchers("/login/authenticate")
        .and()
        .authorizeRequests()
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
        .permitAll()
        .antMatchers("/login", "/access-denied", "/products/**","/login/**")
        .permitAll()
        .antMatchers("/orders/**","/user/**")
        .hasAuthority("CLIENT")
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login/authenticate")
        .failureUrl("/login?error=true")
        .usernameParameter("username")
        .passwordParameter("password")
        .defaultSuccessUrl("/user/profile")
        .and()
        .logout()
        .deleteCookies("JSESSIONID")
        .logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
        .logoutSuccessUrl("/login")
        .and()
        .exceptionHandling()
        .accessDeniedPage("/access-denied")
        .and()
        .sessionManagement()
        .and()
        .headers()
        .contentTypeOptions()
        .and()
        .xssProtection()
        .and()
        .cacheControl()
        .and()
        .httpStrictTransportSecurity()
        .and()
        .frameOptions()
        .and()
        .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "script-src 'self'"));

    ;
  }
}
