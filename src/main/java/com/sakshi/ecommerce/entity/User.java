package com.sakshi.ecommerce.entity;

//pulls tools to talk to database ex @Entity, @Id
import jakarta.persistence.*;
// pulls library lombok to write boring and repetitive code like getters, setters, constructors, toString, etc.
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// allows you to create new user object in very clean way
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    private boolean enabled = true;

    // many users can have same role
    // EAGER = when loading user we also need to load the roles of that user
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}
