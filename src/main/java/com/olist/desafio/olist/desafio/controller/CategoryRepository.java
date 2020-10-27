package com.olist.desafio.olist.desafio.controller;

import com.olist.desafio.olist.desafio.entity.Category;
import com.olist.desafio.olist.desafio.utils.ConstantsUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {

    public void add(String name) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        EntityManager em = entityManagerFactory.createEntityManager();

        Category category = new Category();
        category.setName(name);
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("INSERT: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Category> findAll() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        List<Category> categories = null;

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            categories = em.createQuery(" from Category").getResultList();
        } catch (Exception e) {
            System.out.println("LIST ALL: " + e.getMessage());
        } finally {
            em.close();
        }

        em.detach(categories);
        if (!categories.isEmpty() && categories != null) return categories;
        else return new ArrayList<>();
    }

    public Category findId(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);

        Category category = null;
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            category = em.find(Category.class, id);
        } catch (Exception e) {
            System.out.println("LIST ALL: " + e.getMessage());
        } finally {
            em.close();
        }

        return category;
    }

    public Category findName(Category category) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        Category categorias = null;

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            categorias = (Category) em.createQuery("FROM Category where name = :name").setParameter("name", category.getName()).getSingleResult();

        } catch (Exception e) {
            System.out.println("LIST ALL: " + e.getMessage());
        } finally {
            em.close();
        }

        return categorias;
    }

    public void update(Category category) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("UPDATE: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void delete(Category category) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            Category c = em.find(Category.class, category.getId());
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
    private static CategoryRepository uniqueInstance;

    private CategoryRepository() {
    }

    public static synchronized CategoryRepository getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new CategoryRepository();

        return uniqueInstance;
    }
}
