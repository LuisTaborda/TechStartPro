package com.olist.desafio.olist.desafio.controller;

import com.olist.desafio.olist.desafio.entity.Category;
import com.olist.desafio.olist.desafio.entity.Product;
import com.olist.desafio.olist.desafio.filtro.ProductFiler;
import com.olist.desafio.olist.desafio.utils.ConstantsUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.*;

@Repository
public class ProductRepository {

    public void add(Product product) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("INSERT: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Product> findByFilter(ProductFiler filter) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);

        List<Product> products = new ArrayList<>();

        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            long id = filter.getId() != null ? filter.getId() : 0;
            String name = filter.getName() != null ? filter.getName() : "";
            String description = filter.getDescription() != null ? filter.getDescription() : "";
            Double price = filter.getPrice() != null ? filter.getPrice() : 0;
            Set<Category> categories = filter.getCategory() != null ? filter.getCategory() : new HashSet<>();

            products = em.createQuery(" FROM Product p WHERE p.id = :id OR p.name like :name OR p.description like :description OR p.price <= :price ")
                    .setParameter("id", id)
                    .setParameter("name", "%" + name + "%")
                    .setParameter("description", "%" + description + "%")
                    .setParameter("price", price)
                    .getResultList();
            if (!categories.isEmpty() && categories != null) products = filterCategory(categories, products);

        } catch (Exception e) {
            System.out.println("LIST ALL: " + e.getMessage());
        } finally {
            em.close();
        }

        return products;
    }

    public List<Product> filterCategory(Set<Category> categories, List<Product> products) {
        List<Product> filter = new ArrayList<>();
        for (Product product : products) {
            for (Category category : categories) {
                for (Category pc : product.getCategorias()) {
                    if (category.getName().equals(pc.getName())) {
                        filter.add(product);
                    }
                }
            }
        }
        return filter;
    }

    public void update(Product product) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("UPDATE: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void delete(Product product) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            Product p = em.find(Product.class, product.getId());
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
    private static ProductRepository uniqueInstance;

    private ProductRepository() {
    }

    public static synchronized ProductRepository getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new ProductRepository();

        return uniqueInstance;
    }
}
