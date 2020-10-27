package com.olist.desafio.olist.desafio.controller;

import com.olist.desafio.olist.desafio.entity.Produto;
import com.olist.desafio.olist.desafio.filtro.ProdutoFiltro;
import com.olist.desafio.olist.desafio.utils.ConstantsUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    public void adicionar(Produto produto) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("INSERT: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Produto> buscarTodos() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        List<Produto> produtos = null;

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            produtos = em.createQuery(" from Produtos").getResultList();
        } catch (Exception e) {
            System.out.println("LIST ALL: " + e.getMessage());
        } finally {
            em.close();
        }

        if (!produtos.isEmpty() && produtos != null) return produtos;
        else return new ArrayList<>();
    }

    public Produto buscarId(Produto produto) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            produto = em.find(Produto.class, produto.getId());
        } catch (Exception e) {
            System.out.println("LIST ALL: " + e.getMessage());
        } finally {
            em.close();
        }

        return produto;
    }

    public List<Produto> buscarPorFiltro(ProdutoFiltro filtro) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);

        List<Produto> produto = new ArrayList<>();

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            produto = em.createQuery("FROM Categoria where nome = :nome OR descricao = :descricao OR valor = :valor")
                    .setParameter("nome", filtro.getNome())
                    .setParameter("descricao", filtro.getDescricao())
                    .setParameter("valor", filtro.getValor())
                    .getResultList();

        } catch (Exception e) {
            System.out.println("LIST ALL: " + e.getMessage());
        } finally {
            em.close();
        }

        return produto;
    }


    public void atualizar(Produto produto) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("UPDATE: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void remover(Produto produto) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            Produto p = em.find(Produto.class, produto.getId());
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("DELETE: " + e.getMessage());
        } finally {
            em.close();
        }
    }


    /*
    * Singleton pattern
    * */
    private static ProdutoRepository uniqueInstance;

    private ProdutoRepository() {
    }

    public static synchronized ProdutoRepository getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new ProdutoRepository();

        return uniqueInstance;
    }
}
