package com.interview.shop.user.dao;

import com.interview.shop.user.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role,Integer> {
}
