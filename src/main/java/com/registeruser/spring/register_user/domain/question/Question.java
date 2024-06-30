package com.registeruser.spring.register_user.domain.question;

import com.registeruser.spring.register_user.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name="questions")
@Table(name = "questions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String question;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
