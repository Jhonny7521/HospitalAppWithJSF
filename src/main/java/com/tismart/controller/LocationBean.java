package com.tismart.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.tismart.dao.LocationDAO;
import com.tismart.model.Location;

@ManagedBean(name = "locationBean")
@RequestScoped
public class LocationBean {

	public String newLocation() {
		Location location = new Location();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("location", location);
		
		return "newLocation.xhtml";
	}
	
	public String saveLocation(Location location) {
		LocationDAO locationDAO = new LocationDAO();
		locationDAO.saveLocation(location);
		return "index.xhtml";
	}
	
	public List<Location> getAllLocations(){
		
		LocationDAO locationDAO = new LocationDAO();
		
		return locationDAO.getAllLocations();
	}
	
	public String editLocation(int id) {
		
		LocationDAO locationDAO = new LocationDAO();
		Location location = new Location();
		
		location = locationDAO.getLocationById(id);
		
		System.out.println(location);
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("location", location);
		return "editLocation.xhtml";
	}
	
	public String updateLocation(Location location) {
		
		LocationDAO locationDAO = new LocationDAO();
		locationDAO.updateLocation(location);
		
		return "index.xhtml";
	}
	
	public String deleteLocation(int id) {
		LocationDAO locationDAO = new LocationDAO();
		locationDAO.deleteLocation(id);
		System.out.println("Provincia eliminada");
		return "index.xhtml?faces-redirect=true";
	}
}
