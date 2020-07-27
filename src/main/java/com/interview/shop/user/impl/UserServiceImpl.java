package com.interview.shop.user.impl;

import com.interview.shop.exception.EntityNotFoundException;
import com.interview.shop.user.UserService;
import com.interview.shop.user.dao.UserRepo;
import com.interview.shop.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

  private UserRepo userRepo;

  @Override
  public User create(User user) {
    return userRepo.save(user);
  }

  @Override
  public User get(String email) {
    var userOpt = userRepo.findByEmail(email);
    userOpt.orElseThrow(EntityNotFoundException::new);
    return userOpt.get();
  }
}
