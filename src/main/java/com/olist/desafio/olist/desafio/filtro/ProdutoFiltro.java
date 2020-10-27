package com.olist.desafio.olist.desafio.filtro;

import com.olist.desafio.olist.desafio.entity.Categoria;

import java.util.List;

public class ProdutoFiltro {

    private Long id;
    private String Nome;
    private String descricao;
    private Double valor;
    private List<Categoria> categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<Categoria> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;
    }
}
