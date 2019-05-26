package com.restaurant.model;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Kuldeep Gupta
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> customers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<User> customers) {
        this.customers = customers;
    }
}