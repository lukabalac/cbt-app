package com.interview.shop.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @Column(name = "user_id")
  private int id;

  @Column(name = "active")
  private boolean active = true;

  @Column(name = "email", unique = true)
  @NotBlank(message = "*Email must not be blank")
  private String email;

  @Column(name = "password")
  @Size(min = 6, message = "Must be minimum of 6 characters")
  @NotBlank(message = "*Must not be blank")
  private String password;
  @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User(String email,String password,Role role) {
    this.email = email;
    this.password = password;
    this.roles.add(role);
  }
}
