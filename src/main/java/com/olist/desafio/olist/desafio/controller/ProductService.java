package com.olist.desafio.olist.desafio.controller;

import com.olist.desafio.olist.desafio.entity.Product;
import com.olist.desafio.olist.desafio.filtro.ProductFiler;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService(){
        productRepository = ProductRepository.getInstance();
    }

    public void add(Product product){
        productRepository.add(product);
    }

    public List<Product> findByFilter(ProductFiler filter){
        return productRepository.findByFilter(filter);
    }

}
