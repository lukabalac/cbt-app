package com.interview.shop.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "product_id")
    private int id;
    @NotBlank(message = "*Name must not be blank")
    private String name;
    @NotNull(message = "*Price must be defined")
    private BigDecimal price;
    private String imagePath;
    private int timesPurchased = 0;

    public Product(String name,BigDecimal price,String imagePath) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }
}
