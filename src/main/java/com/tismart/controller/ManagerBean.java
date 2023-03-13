package com.tismart.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.tismart.dao.ManagerDAO;
import com.tismart.model.Manager;

@ManagedBean(name = "managerBean")
@RequestScoped
public class ManagerBean {

	public String newManager() {
		Manager manager = new Manager();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("manager", manager);
		
		return "newManager.xhtml";
	}
	
	public String saveManager(Manager manager) {
		ManagerDAO managerDAO = new ManagerDAO();
		managerDAO.saveManager(manager);
		return "index.xhtml";
	}
	
	public List<Manager> getAllManagers(){
				
		ManagerDAO managerDAO = new ManagerDAO();
		
		return managerDAO.getAllManagers();
	}
	
	public String editManager(int id) {
		
		ManagerDAO managerDAO = new ManagerDAO();
		Manager manager = new Manager();
		
		manager = managerDAO.getManagerById(id);
		
		System.out.println(manager);
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("manager", manager);
		return "editManager.xhtml";
	}
	
	public String updateManager(Manager manager) {
		
		ManagerDAO managerDAO = new ManagerDAO();
		managerDAO.updateManager(manager);
		
		return "index.xhtml";
	}
	
	public String deleteManager(int id) {
		ManagerDAO managerDAO = new ManagerDAO();
		managerDAO.deleteManager(id);
		System.out.println("Provincia eliminada");
		return "index.xhtml?faces-redirect=true";
	}
	
}
