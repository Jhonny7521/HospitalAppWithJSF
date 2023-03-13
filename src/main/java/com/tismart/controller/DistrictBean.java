package com.tismart.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.tismart.dao.DistrictDAO;
import com.tismart.dao.ProvinceDAO;
import com.tismart.model.District;
import com.tismart.model.Province;

@ManagedBean(name = "districtBean")
@RequestScoped
public class DistrictBean {

	public String newDistrict() {
		District district = new District();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("district", district);
		
		
		return "newDistrict.xhtml";
	}
	
	public String saveDistrict(District district) {
		System.out.println(district);
		DistrictDAO districtDAO = new DistrictDAO();
		districtDAO.saveDistrict(district);
		return "index.xhtml";
	}
	
	public List<District> getAllDistricts(){
				
		DistrictDAO districtDAO = new DistrictDAO();
		
		return districtDAO.getAllDistricts();
	}
	
	public List<Province> getAllProvinces(){
		
		ProvinceDAO provinceDAO = new ProvinceDAO();
		
		return provinceDAO.getAllProvinces();
	}
	
	public String editDistrict(int id) {
		
		DistrictDAO districtDAO = new DistrictDAO();
		District district = new District();
		
		district = districtDAO.getDistrictById(id);
		
		System.out.println(district);
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("district", district);
		return "editDistrict.xhtml";
	}
	
	public String updateDistrict(District district) {
		
		DistrictDAO districtDAO = new DistrictDAO();
		districtDAO.updateDistrict(district);
		
		return "index.xhtml";
	}
	
	public String deleteDistrict(int id) {
		DistrictDAO districtDAO = new DistrictDAO();
		districtDAO.deleteDistrict(id);
		System.out.println("Distrito eliminado");
		return "index.xhtml?faces-redirect=true";
	}
	
}
