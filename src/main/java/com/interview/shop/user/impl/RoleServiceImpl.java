package com.interview.shop.user.impl;

import com.interview.shop.user.RoleService;
import com.interview.shop.user.dao.RoleRepo;
import com.interview.shop.user.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl implements RoleService {
    private RoleRepo roleRepo;
    @Override
    public Role create(Role role) {
        return roleRepo.save(role);
    }
}
