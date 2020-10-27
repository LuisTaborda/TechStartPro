package com.olist.desafio.olist.desafio.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String Nome;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name="PRODUTO_CATEGORIA",
            joinColumns={@JoinColumn(name="CATEGORIA_ID")},
            inverseJoinColumns={@JoinColumn(name="PRODUTO_ID")})
    private Set<Produto> produtos;

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

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }
}
