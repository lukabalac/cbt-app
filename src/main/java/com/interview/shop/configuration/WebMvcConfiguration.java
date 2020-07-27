package com.interview.shop.configuration;

import com.interview.shop.user.RoleService;
import com.interview.shop.user.UserService;
import com.interview.shop.user.model.Role;
import com.interview.shop.user.model.RoleType;
import com.interview.shop.user.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
  }

  @Bean
  CommandLineRunner initialSetup(
      RoleService roleService, UserService userService, BCryptPasswordEncoder encoder) {

    System.out.println("Running initial setup ");
    return (args) -> {
      var clientRole = roleService.create(new Role(RoleType.CLIENT));
      var user =
          userService.create(new User("client@foo.com", encoder.encode("client123"), clientRole));
    };
  }
}
