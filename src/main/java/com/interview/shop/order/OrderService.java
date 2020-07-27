package com.interview.shop.order;

import com.interview.shop.exception.EntityNotFoundException;
import com.interview.shop.order.model.Order;
import com.interview.shop.user.model.User;

import java.util.List;

public interface OrderService {

    Order purchase(int productId,String userName) throws EntityNotFoundException;
    List<Order> getAll(User user);

}
