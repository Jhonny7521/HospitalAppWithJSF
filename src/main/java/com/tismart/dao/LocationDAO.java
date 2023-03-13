package com.tismart.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import com.tismart.model.Location;
import com.tismart.model.JPAUtil;

public class LocationDAO {

EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	public void saveLocation(Location location) {
		
		entity.getTransaction().begin();
		entity.persist(location);
		entity.getTransaction().commit();
		//JPAUtil.shutdown();
	}
	
	public void updateLocation(Location location) {
		
		entity.getTransaction().begin();			
		entity.merge(location);
		entity.getTransaction().commit();
		//JPAUtil.shutdown();
	}
	
	public void deleteLocation(int id) {
		Location location = entity.find(Location.class, id);
		entity.getTransaction().begin();
		entity.remove(location);
		entity.getTransaction().commit();
	}
	
	public Location getLocationById(int id) {
		Location location = new Location();
		
		System.out.println("--prueba--");
		location = entity.find(Location.class, id);
		// JPAUtil.shutdown();
		
		return location;		
	}
	
	public Location getLocationById2(int id) {
		Location location = new Location();

		StoredProcedureQuery storedProcedure = entity.createStoredProcedureQuery("LOCATIONS_PACKAGE.GET_ONE_LOCATION");
		storedProcedure.registerStoredProcedureParameter("v_idLocation", Integer.class, ParameterMode.IN);
		storedProcedure.setParameter("v_idLocation", id);
		
		storedProcedure.execute();
		Object[] result = (Object[]) storedProcedure.getSingleResult();
		location.setId((int) result[0]);
		location.setLocationDescription((String) result[1]);
		location.setCreatedAt((Date) result[2]);
		
		
		return location;		
	}
	
	public List<Location> getAllLocations(){
		List<Location> locationList = new ArrayList<>();
		Query query = entity.createQuery("SELECT p FROM Location p");
		locationList = query.getResultList();
		
		return locationList;
	}
}
