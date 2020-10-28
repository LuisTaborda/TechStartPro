package com.olist.desafio.olist.desafio.filter;

import com.olist.desafio.olist.desafio.entity.Category;

import java.util.Set;

public class ProductFilter {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Set<Category> category;

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

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }
}
