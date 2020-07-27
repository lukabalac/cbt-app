package com.interview.shop.order;

import com.interview.shop.order.model.Order;

public interface OrderService {

    Order purchase(int productId,String userName);

}
