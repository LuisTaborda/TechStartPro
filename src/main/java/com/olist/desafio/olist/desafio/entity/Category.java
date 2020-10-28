package com.olist.desafio.olist.desafio.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
@PersistenceContext
public class Category {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "product_category",
            joinColumns = {@JoinColumn(name = "CATEGORY_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID")})
    private Set<Product> products = new HashSet<>();

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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append(" Category#" + id + "{");
        builder.append("\n\tid = " + id);
        builder.append("\n\tname = " + this.name);
        if (this.products != null) {
            builder.append("products{");
            for (Product p : products) {
                builder.append(" " + p.getName());
            }
            builder.append("}");
        }
        builder.append("}");

        return builder.toString();
    }
}
