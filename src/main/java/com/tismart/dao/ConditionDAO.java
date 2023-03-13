package com.tismart.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import com.tismart.model.JPAUtil;
import com.tismart.model.Condition;

public class ConditionDAO {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	public void saveCondition(Condition condition) {
		
		entity.getTransaction().begin();
		entity.persist(condition);
		entity.getTransaction().commit();
		//JPAUtil.shutdown();
	}
	
	public void updateCondition(Condition condition) {
		
		entity.getTransaction().begin();			
		entity.merge(condition);
		entity.getTransaction().commit();
		//JPAUtil.shutdown();
	}
	
	public void deleteCondition(int id) {
		Condition condition = entity.find(Condition.class, id);
		entity.getTransaction().begin();
		entity.remove(condition);
		entity.getTransaction().commit();
	}
	
	public Condition getConditionById(int id) {
		Condition condition = new Condition();
		
		System.out.println("--prueba--");
		condition = entity.find(Condition.class, id);
		// JPAUtil.shutdown();
		
		return condition;		
	}
	
	public Condition getConditionById2(int id) {
		Condition condition = new Condition();

		StoredProcedureQuery storedProcedure = entity.createStoredProcedureQuery("CONDITIONS_PACKAGE.GET_ONE_CONDITION");
		storedProcedure.registerStoredProcedureParameter("v_idCondition", Integer.class, ParameterMode.IN);
		storedProcedure.setParameter("v_idCondition", id);
		
		storedProcedure.execute();
		Object[] result = (Object[]) storedProcedure.getSingleResult();
		condition.setId((int) result[0]);
		condition.setConditionDescription((String) result[1]);
		condition.setCreatedAt((Date) result[2]);
		
		
		return condition;		
	}
	
	public List<Condition> getAllConditions(){
		List<Condition> conditionList = new ArrayList<>();
		Query query = entity.createQuery("SELECT p FROM Condition p");
		conditionList = query.getResultList();
		
		return conditionList;
	}
}
