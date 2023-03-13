package com.tismart.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import com.tismart.model.JPAUtil;
import com.tismart.model.Province;

/*
 * Esta clase contiene los metodos CRUD para el proyecto
 * Apoyados por Hibernate que es la depenedencia que estamos utilizando
 * para el mapeo Obj relacional hacia nuestra BD
 * */

public class ProvinceDAO {
	
	// Obj que nos permitira crear los metodos CRUD para nuestra base de datos
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	public void saveProvince(Province province) {
		// Abrimos una transaccion
		entity.getTransaction().begin();
		// guardamos el obj
		entity.persist(province);
		// Confirmamos la transaccion
		entity.getTransaction().commit();
		// cerramos la conexion
		//JPAUtil.shutdown();
	}
	
	public void updateProvince(Province province) {
		
		entity.getTransaction().begin();
		// busca un registro que coincida con el id del obj enviado
		entity.merge(province);
		entity.getTransaction().commit();
		//JPAUtil.shutdown();
	}
	
	public void deleteProvince(int id) {
		Province province = entity.find(Province.class, id);
		entity.getTransaction().begin();
		entity.remove(province);
		entity.getTransaction().commit();
	}
	
	public Province getProvinceById(int id) {
		Province province = new Province();
		//find permite encontrar un registro, se le envia la clase del objeto que deseo obtener y el Id
		System.out.println("--prueba--");
		province = entity.find(Province.class, id);
		// JPAUtil.shutdown();
		
		return province;		
	}
	
	public Province getProvinceById2(int id) {
		Province province = new Province();

		StoredProcedureQuery storedProcedure = entity.createStoredProcedureQuery("PROVINCES_PACKAGE.GET_ONE_PROVINCE");
		storedProcedure.registerStoredProcedureParameter("v_idProvince", Integer.class, ParameterMode.IN);
		storedProcedure.setParameter("v_idProvince", id);
		
		storedProcedure.execute();
		Object[] result = (Object[]) storedProcedure.getSingleResult();
		province.setId((int) result[0]);
		province.setProvinceDescription((String) result[1]);
		province.setCreatedAt((Date) result[2]);
		
		
		return province;		
	}
	
	public List<Province> getAllProvinces(){
		List<Province> provinceList = new ArrayList<>();
		Query query = entity.createQuery("SELECT p FROM Province p");
		provinceList = query.getResultList();
		
		return provinceList;
	}

}
