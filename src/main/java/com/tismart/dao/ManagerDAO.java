package com.tismart.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import com.tismart.model.Manager;
import com.tismart.model.JPAUtil;

public class ManagerDAO {

EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	public void saveManager(Manager manager) {
		
		entity.getTransaction().begin();
		entity.persist(manager);
		entity.getTransaction().commit();
		//JPAUtil.shutdown();
	}
	
	public void updateManager(Manager manager) {
		
		entity.getTransaction().begin();			
		entity.merge(manager);
		entity.getTransaction().commit();
		//JPAUtil.shutdown();
	}
	
	public void deleteManager(int id) {
		Manager manager = entity.find(Manager.class, id);
		entity.getTransaction().begin();
		entity.remove(manager);
		entity.getTransaction().commit();
	}
	
	public Manager getManagerById(int id) {
		Manager manager = new Manager();
		
		System.out.println("--prueba--");
		manager = entity.find(Manager.class, id);
		// JPAUtil.shutdown();
		
		return manager;		
	}
	
	public Manager getManagerById2(int id) {
		Manager manager = new Manager();

		StoredProcedureQuery storedProcedure = entity.createStoredProcedureQuery("MANAGERS_PACKAGE.GET_ONE_MANAGER");
		storedProcedure.registerStoredProcedureParameter("v_idManager", Integer.class, ParameterMode.IN);
		storedProcedure.setParameter("v_idManager", id);
		
		storedProcedure.execute();
		Object[] result = (Object[]) storedProcedure.getSingleResult();
		manager.setId((int) result[0]);
		manager.setManagerDescription((String) result[1]);
		manager.setCreatedAt((Date) result[2]);
		
		
		return manager;		
	}
	
	public List<Manager> getAllManagers(){
		List<Manager> managerList = new ArrayList<>();
		Query query = entity.createQuery("SELECT p FROM Manager p");
		managerList = query.getResultList();
		
		return managerList;
	}
}
