package com.zimbra.resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerofMPool {

    private static final String PERSISTENCE_UNIT_NAME_M = "m";
    private static EntityManagerFactory factory;
    private static EntityManager em;

    public static void create() {
	factory = Persistence
		.createEntityManagerFactory(PERSISTENCE_UNIT_NAME_M);
	em = factory.createEntityManager();
    }

    public static EntityManager getEntityManagerofM() {
	return em;
    }

    public EntityManagerofMPool() {
    }
}
