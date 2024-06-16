package com.vietbevis.movies.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 255)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Byte isActive;

    @Size(max = 255)
    @Column(name = "provider_id", nullable = false)
    private String providerId;

    @Size(max = 255)
    @Column(name = "profile_image")
    private String profileImage;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}