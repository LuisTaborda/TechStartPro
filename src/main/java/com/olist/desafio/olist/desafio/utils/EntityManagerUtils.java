package com.olist.desafio.olist.desafio.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtils {

    public static EntityManager getInstance() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConstantsUtils.PERSISTENCE_UNIT_NAME);
        return entityManagerFactory.createEntityManager();
    }
}
