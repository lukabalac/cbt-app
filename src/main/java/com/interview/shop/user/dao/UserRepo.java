package com.interview.shop.user.dao;

import com.interview.shop.user.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
