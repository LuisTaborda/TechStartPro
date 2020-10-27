package com.olist.desafio.olist.desafio.controller;

import com.olist.desafio.olist.desafio.entity.Categoria;
import com.olist.desafio.olist.desafio.entity.Produto;
import com.olist.desafio.olist.desafio.utils.ConstantsUtils;
import com.olist.desafio.olist.desafio.utils.CsvUtils;

import java.io.IOException;
import java.util.List;

public class ProdutoService {

    private ProdutoRepository produtoRepository;
    public ProdutoService(){
        produtoRepository = ProdutoRepository.getInstance();
    }

    public void adicionar(Produto produto){
        produtoRepository.adicionar(produto);
    }

//    public Categoria buscarId(Categoria categoria){
//        return categoriaRepository.buscarId(categoria);
//    }
//
//    public Categoria buscarNome(Categoria categoria){
//        return categoriaRepository.buscarNome(categoria);
//    }
//
//    public List<Categoria> buscarTodos(){
//        return categoriaRepository.buscarTodos();
//    }
//
//    public void atualizar(Categoria categoria){
//        categoriaRepository.atualizar(categoria);
//    }
//
//    public void remover(Categoria categoria){
//        categoriaRepository.remover(categoria);
//    }
//
//    public void adicionarCategoriasPorCSV() throws IOException {
//
//        List<String[]> categorias = CsvUtils.readCSV(ConstantsUtils.PATH_CSV);
//
//        for (String[] categoria: categorias) {
//            adicionar(categoria[0]);
//        }
//
//    }
}
