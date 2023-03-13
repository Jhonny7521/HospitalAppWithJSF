package com.tismart.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import com.tismart.model.Hospital;
import com.tismart.model.JPAUtil;

public class HospitalDAO {

EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	public void saveHospital(Hospital hospital) {
		
		entity.getTransaction().begin();
		entity.persist(hospital);
		entity.getTransaction().commit();
		//JPAUtil.shutdown();
	}
	
	public void updateHospital(Hospital hospital) {
		
		entity.getTransaction().begin();			
		entity.merge(hospital);
		entity.getTransaction().commit();
		//JPAUtil.shutdown();
	}
	
	public void deleteHospital(int id) {
		Hospital hospital = entity.find(Hospital.class, id);
		entity.getTransaction().begin();
		entity.remove(hospital);
		entity.getTransaction().commit();
	}
	
	public Hospital getHospitalById(int id) {
		Hospital hospital = new Hospital();
		
		System.out.println("--prueba--");
		hospital = entity.find(Hospital.class, id);
		// JPAUtil.shutdown();
		
		return hospital;		
	}
	
	public Hospital getHospitalById2(int id) {
		Hospital hospital = new Hospital();

		StoredProcedureQuery storedProcedure = entity.createStoredProcedureQuery("CONDITIONS_PACKAGE.GET_ONE_CONDITION");
		storedProcedure.registerStoredProcedureParameter("v_idHospital", Integer.class, ParameterMode.IN);
		storedProcedure.setParameter("v_idHospital", id);
		
		storedProcedure.execute();
		Object[] result = (Object[]) storedProcedure.getSingleResult();
		hospital.setId((int) result[0]);
		hospital.setHospitalName((String) result[1]);
		hospital.setCreatedAt((Date) result[2]);
		
		
		return hospital;		
	}
	
	public List<Hospital> getAllHospitals(){
		List<Hospital> hospitalList = new ArrayList<>();
		Query query = entity.createQuery("SELECT p FROM Hospital p");
		hospitalList = query.getResultList();
		
		return hospitalList;
	}
}
