package com.olist.desafio.olist.desafio.controller;

import com.olist.desafio.olist.desafio.entity.Categoria;
import com.olist.desafio.olist.desafio.utils.ConstantsUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepository {

    public void adicionar(String nome) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        EntityManager em = entityManagerFactory.createEntityManager();

        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        try {
            em.getTransaction().begin();
            em.persist(categoria);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("INSERT: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Categoria> buscarTodos() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        List<Categoria> categorias = null;

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            categorias = em.createQuery(" from Categoria").getResultList();
        } catch (Exception e) {
            System.out.println("LIST ALL: " + e.getMessage());
        } finally {
            em.close();
        }

        if (!categorias.isEmpty() && categorias != null) return categorias;
        else return new ArrayList<>();
    }

    public Categoria buscarId(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);

        Categoria categoria = null;
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            categoria = em.find(Categoria.class, id);
        } catch (Exception e) {
            System.out.println("LIST ALL: " + e.getMessage());
        } finally {
            em.close();
        }

        return categoria;
    }

    public Categoria buscarNome(Categoria categoria) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        Categoria categorias = null;

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            categorias = (Categoria) em.createQuery("FROM Categoria where nome = :nome").setParameter("nome", categoria.getNome()).getSingleResult();

        } catch (Exception e) {
            System.out.println("LIST ALL: " + e.getMessage());
        } finally {
            em.close();
        }

        return categorias;
    }

    public void atualizar(Categoria categoria) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(categoria);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("UPDATE: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void remover(Categoria categoria) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            Categoria c = em.find(Categoria.class, categoria.getId());
            em.getTransaction().begin();
            em.remove(c);
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
    private static CategoriaRepository uniqueInstance;

    private CategoriaRepository() {
    }

    public static synchronized CategoriaRepository getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new CategoriaRepository();

        return uniqueInstance;
    }
}
