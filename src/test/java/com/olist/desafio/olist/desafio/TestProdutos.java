package com.olist.desafio.olist.desafio;


import com.olist.desafio.olist.desafio.controller.CategoriaService;
import com.olist.desafio.olist.desafio.controller.ProdutoService;
import com.olist.desafio.olist.desafio.entity.Categoria;
import com.olist.desafio.olist.desafio.entity.Produto;
import com.olist.desafio.olist.desafio.utils.CategoriasConst;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

public class TestProdutos {

    @Test
    public void add() {
        CategoriaService categoriaService = new CategoriaService();

        Set<Categoria> categorias = new HashSet<>();
        categorias.add(categoriaService.buscarId(CategoriasConst.INFORMATICA));
        Produto produto = new Produto();
        produto.setDescricao("Notebook Gamer processador i7 16GB de ram Placa de Video NVIDIA GTX 1080");
        produto.setNome("Notebook Avell");
        produto.setValor(5.500);
//        for (Categoria c: categorias) {
//            Set<Produto> ps = new HashSet<>();
//            ps.add(produto);
//            c.setProdutos(ps);
//        }
        produto.setCategorias(categorias);


        ProdutoService produtoService = new ProdutoService();

        boolean state = false;
        try {
            produtoService.adicionar(produto);
            state = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.isTrue(state);
    }
}
