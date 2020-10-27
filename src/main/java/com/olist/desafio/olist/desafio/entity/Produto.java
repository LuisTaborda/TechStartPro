package com.olist.desafio.olist.desafio.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String Nome;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "VALOR", nullable = false)
    private Double valor;

    @ManyToMany(mappedBy="produto", fetch = FetchType.LAZY)
    private List<Categoria> categoriaList;

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

    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }
}
