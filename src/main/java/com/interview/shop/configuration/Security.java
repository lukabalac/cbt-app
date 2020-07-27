package com.interview.shop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@Order(1)
public class Security extends WebSecurityConfigurerAdapter {
  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired private DataSource dataSource;

  @Value("${spring.queries.users-query}")
  private String usersQuery;

  @Value("${spring.queries.roles-query}")
  private String rolesQuery;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
        .usersByUsernameQuery(usersQuery)
        .authoritiesByUsernameQuery(rolesQuery)
        .dataSource(dataSource)
        .passwordEncoder(bCryptPasswordEncoder);
  }

  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .ignoringAntMatchers("/login/authenticate")
        .and()
        .authorizeRequests()
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
        .permitAll()
        .antMatchers("/login", "/access-denied", "/products/**")
        .permitAll()
        .antMatchers("/api/**","/user/**")
        .hasAnyAuthority("CLIENT")
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login/authenticate")
        .failureUrl("/login?error=true")
        .usernameParameter("email")
        .passwordParameter("password")
        .defaultSuccessUrl("/user/profile")
        .and()
        .logout()
        .deleteCookies("JSESSIONID")
        .logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
        .logoutSuccessUrl("/products")
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
