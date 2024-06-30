package com.registeruser.spring.register_user.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(unique = true)
    private String email;

    private Integer age;

    private Double height;


}
