package com.tismart.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import com.tismart.model.District;
import com.tismart.model.JPAUtil;

public class DistrictDAO {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	public void saveDistrict(District district) {
		
		System.out.println(district);
		entity.getTransaction().begin();
		entity.persist(district);
		entity.getTransaction().commit();
		//JPAUtil.shutdown();
	}
	
	public void updateDistrict(District district) {
		
		entity.getTransaction().begin();			
		entity.merge(district);
		entity.getTransaction().commit();
		//JPAUtil.shutdown();
	}
	
	public void deleteDistrict(int id) {
		District district = entity.find(District.class, id);
		entity.getTransaction().begin();
		entity.remove(district);
		entity.getTransaction().commit();
	}
	
	public District getDistrictById(int id) {
		District district = new District();
		
		System.out.println("--prueba--");
		district = entity.find(District.class, id);
		// JPAUtil.shutdown();
		
		return district;		
	}
	
	public District getDistrictById2(int id) {
		District district = new District();

		StoredProcedureQuery storedProcedure = entity.createStoredProcedureQuery("CONDITIONS_PACKAGE.GET_ONE_CONDITION");
		storedProcedure.registerStoredProcedureParameter("v_idDistrict", Integer.class, ParameterMode.IN);
		storedProcedure.setParameter("v_idDistrict", id);
		
		storedProcedure.execute();
		Object[] result = (Object[]) storedProcedure.getSingleResult();
		district.setId((int) result[0]);
		district.setDistrictDescription((String) result[1]);
		district.setCreatedAt((Date) result[2]);
		
		
		return district;		
	}
	
	public List<District> getAllDistricts(){
		List<District> districtList = new ArrayList<>();
		Query query = entity.createQuery("SELECT p FROM District p");
		districtList = query.getResultList();
		
		return districtList;
	}
}
