package com.olist.desafio.olist.desafio.service;

import com.olist.desafio.olist.desafio.entity.Categoria;
import com.olist.desafio.olist.desafio.repository.CategoriaRepository;

import java.util.List;

public class CategoriaService {

    private CategoriaRepository categoriaRepository;
    public CategoriaService(){
        categoriaRepository = CategoriaRepository.getInstance();
    }

    public void adicionar(Categoria categoria){
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
}
