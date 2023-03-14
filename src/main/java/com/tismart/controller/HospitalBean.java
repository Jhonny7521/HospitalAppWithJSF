package com.tismart.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import com.tismart.dao.HospitalDAO;
import com.tismart.model.Hospital;

@ManagedBean(name = "hospitalBean")
@RequestScoped
public class HospitalBean {
	
	private HospitalDAO hospitalDAO;

    public HospitalBean() {
        this.hospitalDAO = new HospitalDAO();
    }

	public String newHospital() {
		Hospital hospital = new Hospital();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("hospital", hospital);
		
		return "newHospital.xhtml";
	}
	
	public String saveHospital(Hospital hospital) {

		hospitalDAO.saveHospital(hospital);
		return "index.xhtml";
	}
	
	public List<Hospital> getAllHospitals(){
						
		return hospitalDAO.getAllHospitals();
	}
	
	public String editHospital(int id) {
		
		Hospital hospital = hospitalDAO.getHospitalById(id);
				
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("hospital", hospital);
		return "editHospital.xhtml";
	}
	
	public String updateHospital(Hospital hospital) {
		
		hospitalDAO.updateHospital(hospital);
		
		return "index.xhtml";
	}
	
	public String deleteHospital(int id) {

		hospitalDAO.deleteHospital(id);
		System.out.println("Provincia eliminada");
		return "index.xhtml?faces-redirect=true";
	}
	
	public String findAndUpdateHospitalById() {
		Hospital hospital = new Hospital();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("hospital", hospital);
		
		return "pages/hospital/updateHospital.xhtml";
	}
	
	public String findHospitalById() {
			
		FacesContext context = FacesContext.getCurrentInstance();
	    UIInput input = (UIInput) context.getViewRoot().findComponent("formId:hospitalId");
	    String idStr = (String) input.getLocalValue();
	    Integer id = Integer.parseInt(idStr);
	    
	    Hospital hospital = hospitalDAO.getHospitalById(id);
	    
	    Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("hospital", hospital);
	    
		return "updateHospital.xhtml?faces-redirect=true";
	}

}
