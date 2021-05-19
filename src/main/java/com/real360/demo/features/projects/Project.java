package com.real360.demo.features.projects;


import com.real360.demo.features.companies.Company;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"projects\"")
public class Project {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID Id;

    @Basic
    @Column(name = "name", nullable = true, length = 40, unique = true)
    private String name;

    @Basic
    @Column(name = "address", nullable = true, length = 40)
    private String address;

    @Basic
    @Column(name = "country", nullable = true, length = 3)
    private String country;

    @Basic
    @Column(name = "city", nullable = true, length = 40)
    private String city;

    @Basic
    @Column(name = "currency", nullable = true, length = 3)
    private String currency;

    @Basic
    @Column(name = "area_total", nullable = true)
    private Float areaTotal;

    @Basic
    @Column(name = "created_at", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Basic
    @Column(name = "updated_at", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    // relations

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Company company;

}
