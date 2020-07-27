package com.interview.shop.product;

import com.interview.shop.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product create(Product product);
    Product get(int id);
    Page<Product> getAll(Pageable pageable);
    void updateTimesPurchased(Product product);
    List<Product> getPopular(int trigger);
}
