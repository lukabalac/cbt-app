package com.interview.shop.user.controller;

import com.interview.shop.order.OrderService;
import com.interview.shop.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
  private UserService userService;
  private OrderService orderService;

  @GetMapping("/profile")
  public ModelAndView getLoginPage() {
    var mav = new ModelAndView("user/profile");
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    var user = userService.get(authentication.getName());
    mav.addObject("user", user);
    mav.addObject("orders", orderService.getAll(user));
    return mav;
  }
}
