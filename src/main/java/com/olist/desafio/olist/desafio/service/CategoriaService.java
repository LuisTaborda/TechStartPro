package com.olist.desafio.olist.desafio.service;

import com.olist.desafio.olist.desafio.entity.Categoria;
import com.olist.desafio.olist.desafio.repository.CategoriaRepository;
import com.olist.desafio.olist.desafio.utils.ConstantsUtils;
import com.olist.desafio.olist.desafio.utils.CsvUtils;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;

public class CategoriaService {

    private CategoriaRepository categoriaRepository;
    public CategoriaService(){
        categoriaRepository = CategoriaRepository.getInstance();
    }

    public void adicionar(String categoria){
        categoriaRepository.adicionar(categoria);
    }

    public Categoria buscarId(Categoria categoria){
        return categoriaRepository.buscarId(categoria);
    }

    public Categoria buscarNome(Categoria categoria){
        return categoriaRepository.buscarNome(categoria);
    }

    public List<Categoria> buscarTodos(){
        return categoriaRepository.buscarTodos();
    }

    public void atualizar(Categoria categoria){
        categoriaRepository.atualizar(categoria);
    }

    public void remover(Categoria categoria){
        categoriaRepository.remover(categoria);
    }

    public void adicionarCategoriasPorCSV() throws IOException {

        List<String[]> categorias = CsvUtils.readCSV(ConstantsUtils.PATH_CSV);

        for (String[] categoria: categorias) {
            adicionar(categoria[0]);
        }

    }
}
