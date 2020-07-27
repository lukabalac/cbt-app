package com.interview.shop.order.dao;

import com.interview.shop.order.model.Order;
import com.interview.shop.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Integer> {

    Page<Order> findAllByUser(User user, Pageable pageable);

}
