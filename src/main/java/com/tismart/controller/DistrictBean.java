package com.tismart.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.tismart.dao.DistrictDAO;
import com.tismart.model.District;

@ManagedBean(name = "districtBean")
@RequestScoped
public class DistrictBean {

	public String newDistrict() {
		District district = new District();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("district", district);
		
		return "/pages/district/districtForm.xhtml";
	}
	
	public String saveDistrict(District district) {
		DistrictDAO districtDAO = new DistrictDAO();
		districtDAO.saveDistrict(district);
		return "/pages/district/index.xhtml";
	}
	
	public List<District> getAllDistricts(){
				
		DistrictDAO districtDAO = new DistrictDAO();
		
		return districtDAO.getAllDistricts();
	}
	
	public String editDistrict(int id) {
		
		DistrictDAO districtDAO = new DistrictDAO();
		District district = new District();
		
		district = districtDAO.getDistrictById(id);
		
		System.out.println(district);
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("district", district);
		return "/pages/district/districtForm.xhtml";
	}
	
	public String updateDistrict(District district) {
		
		DistrictDAO districtDAO = new DistrictDAO();
		districtDAO.updateDistrict(district);
		
		return "/faces/index.xhtml";
	}
	
	public String deleteDistrict(int id) {
		DistrictDAO districtDAO = new DistrictDAO();
		districtDAO.deleteDistrict(id);
		System.out.println("Provincia eliminada");
		return "/faces/index.xhtml";
	}
	
}
