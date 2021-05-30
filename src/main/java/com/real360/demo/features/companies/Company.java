package com.real360.demo.features.companies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.real360.demo.features.projects.Project;
import com.real360.demo.features.users.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"companies\"")
public class Company {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID Id;

    @Basic
    @Column(name = "name", nullable = true, length = 40, unique = true)
    private String name;

    @Basic
    @Column(name = "address", nullable = true)
    private String address;

    @Basic
    @Column(name = "state", nullable = true, length = 40)
    private String state;

    @Basic
    @Column(name = "city", nullable = true, length = 40)
    private String city;

    @Basic
    @Column(name = "phone", nullable = true, length = 50)
    private String phone;

    @Basic
    @Column(name = "num_employees", nullable = true)
    private Integer numEmployees;

    @Basic
    @Column(name = "logo_url", nullable = true)
    private String logoUrl;

    @Basic
    @Column(name = "created_at", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Basic
    @Column(name = "updated_at", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    //relations

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User admin;

    @OneToMany(
            mappedBy = "company"
            , fetch = FetchType.EAGER
    )
    @JsonIgnore
    private Set<Project> projects = new HashSet<>();

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
        updatedAt = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }

}
