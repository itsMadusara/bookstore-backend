package com.evernet.bookstore.model;

import com.evernet.bookstore.model.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // Enum: ADMIN or USER

    // Relationships
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
