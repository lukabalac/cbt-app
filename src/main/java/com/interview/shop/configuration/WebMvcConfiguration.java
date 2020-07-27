package com.interview.shop.configuration;

import com.interview.shop.product.ProductService;
import com.interview.shop.product.model.Product;
import com.interview.shop.user.RoleService;
import com.interview.shop.user.UserService;
import com.interview.shop.user.model.Role;
import com.interview.shop.user.model.RoleType;
import com.interview.shop.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
  @Value("${spring.jpa.hibernate.ddl-auto}")
  private String hibernateDdl;
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
  }

  @Bean
  CommandLineRunner initialSetup(
      RoleService roleService,
      UserService userService,
      BCryptPasswordEncoder encoder,
      ProductService productService) {

    System.out.println("Running initial setup ");
    return (args) -> {
      if (hibernateDdl.equals("create-drop") || hibernateDdl.equals("create")) {
        var clientRole = roleService.create(new Role(RoleType.CLIENT));
        var user =
            userService.create(new User("client@foo.com", encoder.encode("client123"), clientRole));
        var product =
            productService.create(
                new Product(
                    "Kitten for sale", new BigDecimal("1000.00"), "https://picsum.photos/200"));
      }
    };
  }
}
