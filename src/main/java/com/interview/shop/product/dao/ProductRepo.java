package com.interview.shop.product.dao;

import com.interview.shop.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Query("select p from Product p WHERE p.timesPurchased > (:trigger) ")
    List<Product> findAllPopularProducts(@Param("trigger")int trigger);
}
