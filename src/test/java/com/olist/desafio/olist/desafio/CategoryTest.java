package com.olist.desafio.olist.desafio;


import com.olist.desafio.olist.desafio.entity.Category;
import com.olist.desafio.olist.desafio.controller.CategoryService;
import com.olist.desafio.olist.desafio.utils.CategoryConstants;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryTest {

    @Test
    public void add() {
        String categoria = "Test";
        CategoryService categoryService = new CategoryService();

        boolean state = false;
        try {
            categoryService.add(categoria);
            state = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        Assert.isTrue(state);
    }

    @Test
    public void findId() {
        Category category = new Category();
        category.setId(CategoryConstants.INFORMATICA);

        CategoryService categoryService = new CategoryService();
        Category itemProcurado = null;
        try {
            itemProcurado = categoryService.findId(CategoryConstants.INFORMATICA);
        } catch (Exception e) {
            System.out.println(e);
        }
        Assert.notNull(itemProcurado);
    }

    @Test
    public void findAll() {
        Category category = new Category();
        category.setId(4L);

        CategoryService categoryService = new CategoryService();
        List<Category> list = new ArrayList<>();
        try {
            list = categoryService.findAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        Assert.notEmpty(list);
    }

    @Test
    public void update() {
        Category c = new Category();
        c.setName("Patinetes Elétricos");

        CategoryService categoryService = new CategoryService();
        c = categoryService.findName(c);

        boolean state = false;
        try {
            c.setName("teste");
            categoryService.update(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.isTrue(state);
    }

    @Test
    public void delete() {
        Category c = new Category();
        CategoryService categoryService = new CategoryService();
        c = categoryService.findId(CategoryConstants.INFORMATICA);

        boolean state = false;
        try {
            state = true;
            categoryService.delete(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.isTrue(state);
    }

    @Test
    public void addCategoryWithFileCSV() throws IOException {
        CategoryService categoryService = new CategoryService();
        categoryService.addCategoryFileCSV();

        List<Category> categories = categoryService.findAll();
        org.junit.Assert.assertNotNull(categories);
    }
}
