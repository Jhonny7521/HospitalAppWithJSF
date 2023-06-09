package com.tismart.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.tismart.dao.ProvinceDAO;
import com.tismart.model.Province;
// Componente que recibe todas las peticiones HTTP del navegador web. El provinceBean es utilizado para haver referencia desde
// las vistas
@ManagedBean(name = "provinceBean")
// INdicamos el ambito o scope, mientras se realice la peticion al servidor web se va a mantener este bean
@RequestScoped
public class ProvinceBean {
	
	public String newProvince() {
		Province province = new Province();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("province", province);
		
		return "newProvince.xhtml";
	}
	
	public String saveProvince(Province province) {
		ProvinceDAO provinceDAO = new ProvinceDAO();
		provinceDAO.saveProvince(province);
		return "index.xhtml";
	}
	
	public List<Province> getAllProvinces(){
				
		ProvinceDAO provinceDAO = new ProvinceDAO();
		
		return provinceDAO.getAllProvinces();
	}
	
	public String editProvince(int id) {
		
		ProvinceDAO provinceDAO = new ProvinceDAO();
		Province province = new Province();
		
		province = provinceDAO.getProvinceById(id);
		
		System.out.println(province);
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("province", province);
		return "editProvince.xhtml";
	}
	
	public String updateProvince(Province province) {
		
		ProvinceDAO provinceDAO = new ProvinceDAO();
		provinceDAO.updateProvince(province);
		
		return "index.xhtml";
	}
	
	public String deleteProvince(int id) {
		ProvinceDAO provinceDAO = new ProvinceDAO();
		provinceDAO.deleteProvince(id);
		System.out.println("Provincia eliminada");
		return "index.xhtml?faces-redirect=true";
	}
}
