package com.olist.desafio.olist.desafio;


import com.olist.desafio.olist.desafio.service.CategoryService;
import com.olist.desafio.olist.desafio.service.ProductService;
import com.olist.desafio.olist.desafio.entity.Category;
import com.olist.desafio.olist.desafio.entity.Product;
import com.olist.desafio.olist.desafio.filter.ProductFilter;
import com.olist.desafio.olist.desafio.utils.CategoryConstants;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductTest {


    @Test
    public void add() {
        CategoryService categoryService = new CategoryService();

        Set<Category> categories = new HashSet<>();
        categories.add(categoryService.findId(CategoryConstants.BRINQUEDOS));
        categories.add(categoryService.findId(CategoryConstants.DECORACAO));
        Product product = new Product();
        product.setDescription("descrição qualquer");
        product.setName(" Produto qualquer ");
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
    public void findByFilter() {
        CategoryService categoryService = new CategoryService();
        ProductService productService = new ProductService();

        Set<Category> categories = new HashSet<>();
        categories.add(categoryService.findId(CategoryConstants.INFORMATICA));
        ProductFilter filtro = new ProductFilter();
//        filtro.setCategory(categories);
//        filtro.setName("Tablet");
//        filtro.setPrice(20000.00);
        List<Product> products = productService.findByFilter(filtro);

        System.out.println(products.toString());
        Assert.notEmpty(products);

    }

    @Test
    public void update() {

        ProductService productService = new ProductService();

        Product product = productService.findId(5L);
        product.setName("Peppa Pig Evil");

        boolean status = false;
        try {
            productService.update(product);
            status=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(product.toString());
        Assert.isTrue(status);
    }

    @Test
    public void delete() {

        ProductService productService = new ProductService();
        Product product = productService.findId(5L);
        boolean status = false;
        try {
            productService.delete(product);
            status=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.isTrue(status);
    }
}
