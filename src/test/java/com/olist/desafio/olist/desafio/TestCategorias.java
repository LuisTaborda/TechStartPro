package com.olist.desafio.olist.desafio;


import com.olist.desafio.olist.desafio.entity.Categoria;
import com.olist.desafio.olist.desafio.service.CategoriaService;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class TestCategorias {

    @Test
    public void add() {
        Categoria categoria = new Categoria();
        categoria.setNome("Test");

        CategoriaService categoriaService = new CategoriaService();

        boolean state = false;
        try {
            categoriaService.adicionar(categoria);
            state = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        Assert.isTrue(state);
    }

    @Test
    public void buscar() {
        Categoria categoria = new Categoria();
        categoria.setId(4L);

        CategoriaService categoriaService = new CategoriaService();
        Categoria itemProcurado = null;
        try {
            itemProcurado = categoriaService.buscarId(categoria);
        } catch (Exception e) {
            System.out.println(e);
        }
        Assert.notNull(itemProcurado);
    }

    @Test
    public void buscarTodos() {
        Categoria categoria = new Categoria();
        categoria.setId(4L);

        CategoriaService categoriaService = new CategoriaService();
        List<Categoria> list = new ArrayList<>();
        try {
            list = categoriaService.buscarTodos();
        } catch (Exception e) {
            System.out.println(e);
        }
        Assert.notEmpty(list);
    }

    @Test
    public void Atualizar() {
        Categoria c = new Categoria();
        c.setNome("Patinetes Elétricos");

        CategoriaService categoriaService = new CategoriaService();
        c = categoriaService.buscarNome(c);

        boolean state = false;
        try {
            c.setNome("teste");
            categoriaService.atualizar(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.isTrue(state);
    }

    @Test
    public void Remover() {
        Categoria c = new Categoria();
        c.setId(4L);
        CategoriaService categoriaService = new CategoriaService();
        c = categoriaService.buscarId(c);

        boolean state = false;
        try {
            state = true;
            categoriaService.remover(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.isTrue(state);
    }
}
