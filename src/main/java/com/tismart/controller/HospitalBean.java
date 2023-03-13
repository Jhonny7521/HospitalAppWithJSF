package com.tismart.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.tismart.dao.HospitalDAO;
import com.tismart.model.Hospital;

@ManagedBean(name = "hospitalBean")
@RequestScoped
public class HospitalBean {

	public String newHospital() {
		Hospital hospital = new Hospital();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("hospital", hospital);
		
		return "newHospital.xhtml";
	}
	
	public String saveHospital(Hospital hospital) {
		HospitalDAO hospitalDAO = new HospitalDAO();
		hospitalDAO.saveHospital(hospital);
		return "/faces/index.xhtml";
	}
	
	public List<Hospital> getAllHospitals(){
		
		/*List<Hospital> hospitalList = new ArrayList<>();
		Hospital p1 = new Hospital();
		p1.setHospitalDescription("Provincia 1");
		Hospital p2 = new Hospital();
		p2.setHospitalDescription("Provincia 2");
		
		hospitalList.add(p1);
		hospitalList.add(p2);
		
		return hospitalList;*/
		
		HospitalDAO hospitalDAO = new HospitalDAO();
		
		return hospitalDAO.getAllHospitals();
	}
	
	public String editHospital(int id) {
		
		HospitalDAO hospitalDAO = new HospitalDAO();
		Hospital hospital = new Hospital();
		
		hospital = hospitalDAO.getHospitalById(id);
		
		System.out.println(hospital);
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("hospital", hospital);
		return "/faces/editHospital.xhtml";
	}
	
	public String updateHospital(Hospital hospital) {
		
		HospitalDAO hospitalDAO = new HospitalDAO();
		hospitalDAO.updateHospital(hospital);
		
		return "/faces/index.xhtml";
	}
	
	public String deleteHospital(int id) {
		HospitalDAO hospitalDAO = new HospitalDAO();
		hospitalDAO.deleteHospital(id);
		System.out.println("Provincia eliminada");
		return "/faces/index.xhtml";
	}
	
}
