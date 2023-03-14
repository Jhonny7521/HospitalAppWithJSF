package com.tismart.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import com.tismart.model.Hospital;
import com.tismart.model.JPAUtil;

public class HospitalDAO {

	private EntityManager entity;
	
	public HospitalDAO() {
	    this.entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	}
	 
	
	public void saveHospital(Hospital hospital) {
		

		StoredProcedureQuery storedProcedure = entity.createStoredProcedureQuery("HOSPITALS_PACKAGE.INSERT_HOSPITAL");
		storedProcedure.registerStoredProcedureParameter("v_hospitalName", String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("v_hospitalAge", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("v_hospitalArea", Double.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("v_idManager", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("v_idCondition", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("v_idDistrict", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("v_idLocation", Integer.class, ParameterMode.IN);
		
		storedProcedure.setParameter("v_hospitalName", hospital.getHospitalName());
		storedProcedure.setParameter("v_hospitalAge", hospital.getHospitalAge());
		storedProcedure.setParameter("v_hospitalArea", hospital.getHospitalArea());
		storedProcedure.setParameter("v_idManager", hospital.getManager());
		storedProcedure.setParameter("v_idCondition", hospital.getCondition());
		storedProcedure.setParameter("v_idDistrict", hospital.getDistrict());
		storedProcedure.setParameter("v_idLocation", hospital.getLocation());
		
		storedProcedure.execute();		

	}
	
	public void updateHospital(Hospital hospital) {
		
		
		StoredProcedureQuery storedProcedure = entity.createStoredProcedureQuery("HOSPITALS_PACKAGE.UPDATE_HOSPITAL");
		storedProcedure.registerStoredProcedureParameter("v_idHospital", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("v_hospitalName", String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("v_hospitalAge", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("v_hospitalArea", Double.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("v_idManager", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("v_idCondition", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("v_idDistrict", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("v_idLocation", Integer.class, ParameterMode.IN);
		
		storedProcedure.setParameter("v_idHospital", hospital.getId());
		storedProcedure.setParameter("v_hospitalName", hospital.getHospitalName());
		storedProcedure.setParameter("v_hospitalAge", hospital.getHospitalAge());
		storedProcedure.setParameter("v_hospitalArea", hospital.getHospitalArea());
		storedProcedure.setParameter("v_idManager", hospital.getManager());
		storedProcedure.setParameter("v_idCondition", hospital.getCondition());
		storedProcedure.setParameter("v_idDistrict", hospital.getDistrict());
		storedProcedure.setParameter("v_idLocation", hospital.getLocation());
		
		storedProcedure.execute();
	}
	
	public void deleteHospital(int id) {
		
		StoredProcedureQuery storedProcedure = entity.createStoredProcedureQuery("HOSPITALS_PACKAGE.DELETE_HOSPITAL");
		storedProcedure.registerStoredProcedureParameter("v_idHospital", Integer.class, ParameterMode.IN);
		
		storedProcedure.setParameter("v_idHospital", id);
		
		storedProcedure.execute();
	}
	
	public Hospital getHospitalById(int id) {
		
		Hospital hospital = new Hospital();
		
		try {
	        StoredProcedureQuery storedProcedure = entity.createStoredProcedureQuery("HOSPITALS_PACKAGE.GET_ONE_HOSPITAL");
	        storedProcedure.registerStoredProcedureParameter("v_idHospital", Integer.class, ParameterMode.IN);
	        storedProcedure.registerStoredProcedureParameter("v_result", void.class, ParameterMode.REF_CURSOR);	        
	        storedProcedure.setParameter("v_idHospital", id);	        
	        storedProcedure.execute();	        
	        ResultSet resultSet = (ResultSet)storedProcedure.getOutputParameterValue("v_result");
	        	        
	        if(resultSet.next()){
	        	hospital.setId(resultSet.getInt("IDHOSPITAL"));
	        	hospital.setHospitalName(resultSet.getString("HOSPITALNAME"));
	        	hospital.setHospitalAge(Integer.parseInt(resultSet.getString("HOSPITALAGE")));
	        	hospital.setHospitalArea(Double.parseDouble(resultSet.getString("HOSPITALAREA")));
	        	hospital.setDistrict(Integer.parseInt(resultSet.getString("IDDISTRICT")));
	        	hospital.setLocation(Integer.parseInt(resultSet.getString("IDLOCATION")));
	        	hospital.setManager(Integer.parseInt(resultSet.getString("IDMANAGER")));
	        	hospital.setCondition(Integer.parseInt(resultSet.getString("IDCONDITION")));
	        	
	        	String dateString = resultSet.getString("CREATEDAT");
	        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        	Date date = format.parse(dateString);
	        	
	        	hospital.setCreatedAt(date);	        	
	        }
	        
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
		
		return hospital;
	}
		
	public List<Hospital> getAllHospitals(){

		List<Hospital> hospitalList = new ArrayList<>();
		
		try {
	        StoredProcedureQuery storedProcedure = entity.createStoredProcedureQuery("HOSPITALS_PACKAGE.GET_ALL_HOSPITALS");
	        storedProcedure.registerStoredProcedureParameter("c_result", void.class, ParameterMode.REF_CURSOR);
	        
	        storedProcedure.execute();
	        ResultSet resultSet = (ResultSet)storedProcedure.getOutputParameterValue("c_result");
	        
	        
	        while (resultSet.next()) {
	            Hospital hospital = new Hospital();
	            hospital.setId(resultSet.getInt("IDHOSPITAL"));
		        hospital.setHospitalName(resultSet.getString("HOSPITALNAME"));
		        hospital.setHospitalAge(Integer.parseInt(resultSet.getString("HOSPITALAGE")));
		        hospital.setHospitalArea(Double.parseDouble(resultSet.getString("HOSPITALAREA")));
		        hospital.setDistrict(Integer.parseInt(resultSet.getString("IDDISTRICT")));
		        hospital.setLocation(Integer.parseInt(resultSet.getString("IDLOCATION")));
		        hospital.setManager(Integer.parseInt(resultSet.getString("IDMANAGER")));
		        hospital.setCondition(Integer.parseInt(resultSet.getString("IDCONDITION")));
		        
		        String dateString = resultSet.getString("CREATEDAT");
		        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        Date date = format.parse(dateString);
		        
		        hospital.setCreatedAt(date);
	            
	            hospitalList.add(hospital);
	        }
	        
	       
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
		
		return hospitalList;
	}
}
