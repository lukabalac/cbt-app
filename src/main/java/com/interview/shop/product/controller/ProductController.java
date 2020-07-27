package com.interview.shop.product.controller;

import com.interview.shop.product.ProductService;
import com.interview.shop.product.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/products")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
  private ProductService productService;

  @GetMapping("/{id}")
  public ModelAndView getProductPage(@PathVariable("id") Integer id) {
    var mav = new ModelAndView("product");
    mav.addObject("product", productService.get(id));
    return mav;
  }

  @GetMapping("/popular")
  public @ResponseBody ResponseEntity<?> getPopularProducts() {
    return new ResponseEntity<List<Product>>(productService.getPopular(5), HttpStatus.OK);
  }
}
