package com.tismart.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.NamedStoredProcedureQuery;
//import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

//import javax.persistence.ParameterMode;

//@NamedStoredProcedureQuery(name = "getProvince", procedureName = "PROVINCES_PACKAGE.GET_ONE_PROVINCE", parameters = {
//		@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "parametroEntrada1")
//})
@Entity
@Table(name="PROVINCES", schema = "USERRETO3")
public class Province {

	@Id
	@Column(name="IDPROVINCE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="PROVINCEDESC")
	private String provinceDescription;
	
	@Column(name="CREATEDAT", updatable = false)
	private Date createdAt;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getProvinceDescription() {
		return provinceDescription;
	}
	
	public void setProvinceDescription(String provinceDescription) {
		this.provinceDescription = provinceDescription;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return "Province [id=" + id + ", provinceDescription=" + provinceDescription + ", createdAt=" + createdAt + "]";
	}
		
}
