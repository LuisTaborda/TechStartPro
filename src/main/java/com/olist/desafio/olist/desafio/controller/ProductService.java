package com.olist.desafio.olist.desafio.controller;

import com.olist.desafio.olist.desafio.entity.Product;
import com.olist.desafio.olist.desafio.filter.ProductFilter;

import java.util.List;

public class ProductService {

    private ProductRepository productRepository;

    public ProductService() {
        productRepository = ProductRepository.getInstance();
    }

    public void add(Product product) {
        productRepository.add(product);
    }

    public void update(Product product) {
        productRepository.update(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public List<Product> findByFilter(ProductFilter filter) {
        return productRepository.findByFilter(filter);
    }

    public Product findId(Long id) {
        return productRepository.findId(id);
    }
}
