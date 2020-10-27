package com.olist.desafio.olist.desafio;


import com.olist.desafio.olist.desafio.controller.CategoryService;
import com.olist.desafio.olist.desafio.controller.ProductService;
import com.olist.desafio.olist.desafio.entity.Category;
import com.olist.desafio.olist.desafio.entity.Product;
import com.olist.desafio.olist.desafio.filtro.ProductFiler;
import com.olist.desafio.olist.desafio.utils.CategoryConstants;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductTest {

    @Test
    public void add() {
        CategoryService categoryService = new CategoryService();

        Set<Category> categories = new HashSet<>();
        categories.add(categoryService.findId(CategoryConstants.MOVEIS));
        categories.add(categoryService.findId(CategoryConstants.INFORMATICA));
        Product product = new Product();
        product.setDescription("Escrivaninha Gamer, seu conforto na hora da jogatina");
        product.setName("Kit Escrivaninha Gamer");
        product.setPrice(1554.32);
        product.setCategorias(categories);


        ProductService productService = new ProductService();

        boolean state = false;
        try {
            productService.add(product);
            state = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.isTrue(state);
    }

    @Test
    public void findByFilter(){
        CategoryService categoryService = new CategoryService();
        ProductService productService = new ProductService();

        Set<Category> categories = new HashSet<>();
        categories.add(categoryService.findId(CategoryConstants.INFORMATICA));
        ProductFiler filtro = new ProductFiler();
//        filtro.setCategoria(categorias);
//        filtro.setName("Tablet");
        filtro.setPrice(20000.00);
        List<Product> products = productService.findByFilter(filtro);
        System.out.println("size: " + products.size());
        Assert.notEmpty(products);

    }
}
