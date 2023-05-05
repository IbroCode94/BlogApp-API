package com.example.blogweek9.entity;

import com.example.blogweek9.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize =  1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    private String userName;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;


}
