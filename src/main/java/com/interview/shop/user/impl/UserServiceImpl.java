package com.interview.shop.user.impl;

import com.interview.shop.exception.EntityNotFoundException;
import com.interview.shop.user.UserService;
import com.interview.shop.user.dao.UserRepo;
import com.interview.shop.user.model.Role;
import com.interview.shop.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService, UserDetailsService {

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

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = get(username);
    var userAccountNonExpired = true;
    var userCredentialsNonExpired = true;
    var userAccountNonLocked = true;
    return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            user.isActive(),
            userAccountNonExpired,
            userCredentialsNonExpired,
            userAccountNonLocked,
            getAuthorities(user.getRoles()));
  }
  private Collection<? extends GrantedAuthority> getAuthorities( Set<Role> roleSet) {
    List<String> collector = new ArrayList<>();
    for (Role r : roleSet) {
      collector.add(r.getValue().name());
    }
    return getGrantedAuthorities(collector);
  }

  private List<GrantedAuthority> getGrantedAuthorities(List<String> authoritiesAndRoles) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String privilege : authoritiesAndRoles) {
      authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
  }
}
