package com.interview.shop.order.model;

import com.interview.shop.product.model.Product;
import com.interview.shop.user.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_orders")
@Data
@NoArgsConstructor
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  @Column(name = "order_id")
  private int id;

  @NotNull(message = "Product must be attached to order")
  @OneToOne(cascade = CascadeType.DETACH)
  private Product product;

  @NotNull(message = "User must be attached to order")
  @OneToOne(cascade = CascadeType.DETACH)
  private User user;

  public Order(Product product,User user) {
    this.product = product;
    this.user = user;
  }
}
