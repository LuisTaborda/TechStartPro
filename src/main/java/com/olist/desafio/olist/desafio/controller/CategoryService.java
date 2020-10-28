package com.olist.desafio.olist.desafio.controller;

import com.olist.desafio.olist.desafio.entity.Category;
import com.olist.desafio.olist.desafio.utils.ConstantsUtils;
import com.olist.desafio.olist.desafio.utils.CsvUtils;

import java.io.IOException;
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

    public Category findName(Category category) {
        return categoryRepository.findName(category);
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

    public void addCategoryFileCSV() throws IOException {

        List<String[]> categories = CsvUtils.readCSV(ConstantsUtils.PATH_CSV);
        for (String[] category : categories) {
            add(category[0]);
        }

    }
}
