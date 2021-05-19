package com.real360.demo.features.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.real360.demo.features.companies.Company;
import com.real360.demo.features.roles.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"users\"")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID Id;

    @Basic
    @Column(name = "first_name", nullable = true, length = 40)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = true, length = 40)
    private String lastName;

    @Basic
    @Column(name = "email", nullable = true, length = 40, unique = true)
    private String email;

    @Basic
    @Column(name = "active")
    private boolean isActive;

    @Basic
    @Column(name = "profile_picture", nullable = true)
    private String profilePicture;

    @Basic
    @JsonIgnore
    @Column(name = "password", nullable = true)
    private String password;

    @Basic
    @Column(name = "created_at", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Basic
    @Column(name = "updated_at", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    // relations

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "users_roles",
            joinColumns = {
                    @JoinColumn(name = "role_id"),
            },
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "admin")
    Set<Company> companies = new HashSet<>();


}