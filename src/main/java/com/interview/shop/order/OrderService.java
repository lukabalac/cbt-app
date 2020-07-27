package com.interview.shop.order;

import com.interview.shop.order.model.Order;
import com.interview.shop.user.model.User;

import java.util.List;

public interface OrderService {

    Order purchase(int productId,String userName);
    List<Order> getAll(User user);

}
