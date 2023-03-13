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
		return "/faces/index.xhtml";
	}
	
	public List<Location> getAllLocations(){
		
		/*List<Location> locationList = new ArrayList<>();
		Location p1 = new Location();
		p1.setLocationDescription("Provincia 1");
		Location p2 = new Location();
		p2.setLocationDescription("Provincia 2");
		
		locationList.add(p1);
		locationList.add(p2);
		
		return locationList;*/
		
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
		return "/faces/editLocation.xhtml";
	}
	
	public String updateLocation(Location location) {
		
		LocationDAO locationDAO = new LocationDAO();
		locationDAO.updateLocation(location);
		
		return "/faces/index.xhtml";
	}
	
	public String deleteLocation(int id) {
		LocationDAO locationDAO = new LocationDAO();
		locationDAO.deleteLocation(id);
		System.out.println("Provincia eliminada");
		return "/faces/index.xhtml";
	}
}
