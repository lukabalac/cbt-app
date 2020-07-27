package com.interview.shop.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
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

    @JsonIgnore
    @org.springframework.data.annotation.Transient
    @Column(name = "password")
    @Size(min = 6, message = "{val.size.min.6}")
    @NotBlank(message = "{val.ne}")
    private String password;

    @NotNull(message = "{val.nn.roles}")
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
