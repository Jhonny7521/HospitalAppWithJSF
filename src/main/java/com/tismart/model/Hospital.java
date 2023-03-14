package com.tismart.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;



@Entity
@Table(name = "HOSPITALS", schema = "USERRETO3")
public class Hospital implements Serializable{

	@Id
	@Column(name = "IDHOSPITAL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(name = "HOSPITALNAME")
	private String hospitalName;
	
	@Column(name = "HOSPITALAGE")
	private Integer hospitalAge;
	
	@Column(name = "HOSPITALAREA")
	private Double hospitalArea;
	
	@Column(name = "CREATEDAT")
	private Date createdAt;
	
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "IDMANAGER")
	@Column(name = "IDMANAGER")
	private Integer manager;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "IDCONDITION")
	@Column(name = "IDCONDITION")
	private Integer condition;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "IDDISTRICT")
	@Column(name = "IDDISTRICT")
	private Integer district;  
	
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "IDLOCATION")
	@Column(name = "IDLOCATION")
	private Integer location;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Integer getHospitalAge() {
		return hospitalAge;
	}

	public void setHospitalAge(Integer hospitalAge) {
		this.hospitalAge = hospitalAge;
	}

	public Double getHospitalArea() {
		return hospitalArea;
	}

	public void setHospitalArea(Double hospitalArea) {
		this.hospitalArea = hospitalArea;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getManager() {
		return manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Hospital [Id=" + Id + ", hospitalName=" + hospitalName + ", hospitalAge=" + hospitalAge
				+ ", hospitalArea=" + hospitalArea + ", createdAt=" + createdAt + ", condition=" + condition
				+ ", district=" + district + "]";
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
}
