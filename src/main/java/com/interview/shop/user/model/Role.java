package com.interview.shop.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @Column(name = "role_id")
  private int id;

  @NotNull(message = "Role value must be selected")
  @Column(name = "role_name")
  @Enumerated(value = EnumType.STRING)
  private RoleType value;
}
