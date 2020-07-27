package com.interview.shop.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
  CLIENT("client");

  private String key;
}
