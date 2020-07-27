package com.interview.shop.product.impl;

import com.interview.shop.exception.EntityNotFoundException;
import com.interview.shop.product.ProductService;
import com.interview.shop.product.dao.ProductRepo;
import com.interview.shop.product.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;
    @Override
    public Product create(Product product) {
      return productRepo.save(product);
    }

    @Override
    public Product get(int id) {
        var productOpt = productRepo.findById(id);
        productOpt.orElseThrow(EntityNotFoundException::new);
        return productOpt.get();
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productRepo.findAll(pageable);
    }
}
