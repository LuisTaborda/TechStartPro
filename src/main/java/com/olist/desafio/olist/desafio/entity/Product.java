package com.olist.desafio.olist.desafio.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "product_category",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID")})
    private Set<Category> categories = new HashSet<>();


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Category> getCategorias() {
        return categories;
    }

    public void setCategorias(Set<Category> categories) {
        this.categories = categories;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Product#" + id + "{");
        builder.append("\n\tid = " + id);
        builder.append("\n\tname = " + name);
        builder.append("\n\tprice = $$" + price);
        builder.append("\n\tdescription = " + description);
        if (categories != null) {
            builder.append("\n\tcategories{");
            for (Category c : categories) {
                builder.append(" " + c.getName());
            }
            builder.append(" }");
        }
        builder.append("\n}");

        return builder.toString();
    }
}
