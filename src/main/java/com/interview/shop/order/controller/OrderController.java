package com.interview.shop.order.controller;

import com.interview.shop.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {
  private OrderService orderService;

  @PreAuthorize("hasAuthority('CLIENT')")
  @GetMapping("/purchase/{id}")
  public @ResponseBody ResponseEntity<?> purchaseProduct(@PathVariable("id") Integer id) {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    return new ResponseEntity<String>("Product purchased", HttpStatus.OK);
  }
}
