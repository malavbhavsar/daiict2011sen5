package com.zimbra.ui;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.zimbra.user.zuser;

public class UserSettingsHandler {
    EntityManagerFactory factory;
    EntityManager em;
    zuser z;

    public UserSettingsHandler(String userName) {
	factory = Persistence.createEntityManagerFactory("m",
		System.getProperties());

	// Create a new EntityManager from the EntityManagerFactory. The
	// EntityManager is the main object in the persistence API, and is
	// used to create, delete, and query objects, as well as access
	// the current transaction
	em = factory.createEntityManager();
	em.getTransaction().begin();
	Query q = em.createNativeQuery("select gid_zuser from zuser where username='"+userName+"'");
	z=em.find(zuser.class,q.getSingleResult());
	em.flush();
	em.getTransaction().commit();
    }

    void close() {
	em.close();
	factory.close();
    }

    // Item methods
    public ArrayList<String> getAllSubject() {
	return z.getExcludeSubject();
    }

    public void addSubject(String name) 
    {
	if(z.getExcludeSubject().contains(name))
	{
	    return;
	}
	z.getExcludeSubject().add(name);
	em.getTransaction().begin();
	em.persist(z);
	em.flush();
	em.getTransaction().commit();
    }

    public void deleteSubject(String name) {
	if(!z.getExcludeSubject().contains(name))
	{
	    return;
	}
	z.getExcludeSubject().remove(name);
	em.getTransaction().begin();
	em.persist(z);
	em.flush();
	em.getTransaction().commit();
    }

    // Category Methods
    public ArrayList<String> getAllUser() {
	return  z.getExcludeUser();
    }

    public void addUser(String name) {
	if(z.getExcludeUser().contains(name))
	{
	    return;
	}
	z.getExcludeUser().add(name);
	em.getTransaction().begin();
	em.persist(z);
	em.flush();
	em.getTransaction().commit();
    }

    public void deleteUser(String name) {
	if(!z.getExcludeUser().contains(name))
	{
	    return;
	}
	z.getExcludeUser().remove(name);
	em.getTransaction().begin();
	em.persist(z);
	em.flush();
	em.getTransaction().commit();
    }
}
