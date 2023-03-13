package com.tismart.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.tismart.dao.ConditionDAO;
import com.tismart.model.Condition;

@ManagedBean(name = "conditionBean")
@RequestScoped
public class ConditionBean {
	
	public String homeCondition() {
		return "/pages/index.xhtml";
	}

	public String newCondition() {
		Condition condition = new Condition();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("condition", condition);
		
		return "newCondition.xhtml";
	}
	
	public String saveCondition(Condition condition) {
		ConditionDAO conditionDAO = new ConditionDAO();
		conditionDAO.saveCondition(condition);
		return "/faces/index.xhtml";
	}
	
	public List<Condition> getAllConditions(){
		
		ConditionDAO conditionDAO = new ConditionDAO();
		
		return conditionDAO.getAllConditions();
	}
	
	public String editCondition(int id) {
		
		ConditionDAO conditionDAO = new ConditionDAO();
		Condition condition = new Condition();
		
		condition = conditionDAO.getConditionById(id);
		
		System.out.println(condition);
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("condition", condition);
		return "/pages/condition/editCondition.xhtml";
	}
	
	public String updateCondition(Condition condition) {
		
		ConditionDAO conditionDAO = new ConditionDAO();
		conditionDAO.updateCondition(condition);
		
		return "/faces/index.xhtml";
	}
	
	public String deleteCondition(int id) {
		ConditionDAO conditionDAO = new ConditionDAO();
		conditionDAO.deleteCondition(id);
		System.out.println("Provincia eliminada");
		return "/faces/index.xhtml";
	}
	
}
