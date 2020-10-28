package com.olist.desafio.olist.desafio.service;

import com.olist.desafio.olist.desafio.entity.Category;
import com.olist.desafio.olist.desafio.repository.CategoryRepository;
import com.olist.desafio.olist.desafio.utils.ConstantsUtils;
import com.olist.desafio.olist.desafio.utils.CsvUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService() {
        categoryRepository = CategoryRepository.getInstance();
    }

    public void add(String categoria) {
        categoryRepository.add(categoria);
    }

    public Category findId(Long id) {
        return categoryRepository.findId(id);
    }

    public Category findName(String name) {
        return categoryRepository.findName(name);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void update(Category category) {
        categoryRepository.update(category);
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    public void addCategoryFileCSV(String path) throws IOException {

        List<Category> listCurrents = findAll();
        List<String> currentsCategoryName = new ArrayList<>();
        for (Category c2: listCurrents) {
            currentsCategoryName.add(c2.getName());
        }

        List<String[]> categories = CsvUtils.readCSV(path);
        for (String[] category : categories) {
            if(currentsCategoryName.contains(category[0])){
                System.out.println("true");
            } else{
                add(category[0]);
                System.out.println(category[0] + " add to database");
            }
        }

    }
}
