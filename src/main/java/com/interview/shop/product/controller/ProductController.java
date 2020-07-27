package com.interview.shop.product.controller;

import com.interview.shop.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
  private ProductService productService;

  @GetMapping("/{id}")
  public ModelAndView getLoginPage(@PathVariable("id") Integer id) {
    var mav = new ModelAndView("product");
    mav.addObject("product", productService.get(id));
    return mav;
  }
}
