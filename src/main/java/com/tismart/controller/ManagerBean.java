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
		return "/faces/index.xhtml";
	}
	
	public List<Manager> getAllManagers(){
		
		/*List<Manager> managerList = new ArrayList<>();
		Manager p1 = new Manager();
		p1.setManagerDescription("Provincia 1");
		Manager p2 = new Manager();
		p2.setManagerDescription("Provincia 2");
		
		managerList.add(p1);
		managerList.add(p2);
		
		return managerList;*/
		
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
		return "/faces/editManager.xhtml";
	}
	
	public String updateManager(Manager manager) {
		
		ManagerDAO managerDAO = new ManagerDAO();
		managerDAO.updateManager(manager);
		
		return "/faces/index.xhtml";
	}
	
	public String deleteManager(int id) {
		ManagerDAO managerDAO = new ManagerDAO();
		managerDAO.deleteManager(id);
		System.out.println("Provincia eliminada");
		return "/faces/index.xhtml";
	}
	
}
