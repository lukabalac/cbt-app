package com.interview.shop.order.impl;

import com.interview.shop.exception.EntityNotFoundException;
import com.interview.shop.order.OrderService;
import com.interview.shop.order.dao.OrderRepo;
import com.interview.shop.order.model.Order;
import com.interview.shop.product.ProductService;
import com.interview.shop.user.UserService;
import com.interview.shop.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {
  private ProductService productService;
  private UserService userService;
  private OrderRepo orderRepo;

  @Transactional
  @Override
  public Order purchase(int productId, String userName) throws EntityNotFoundException {
    var product = productService.get(productId);
    var order = orderRepo.save(new Order(product, userService.get(userName)));
    productService.updateTimesPurchased(product);
    return order;
  }

  @Override
  public List<Order> getAll(User user) {
    return orderRepo.findAllByUser(user);
  }
}
