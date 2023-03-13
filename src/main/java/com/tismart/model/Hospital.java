package com.tismart.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;



@Entity
@Table(name = "HOSPITALS", schema = "USERRETO3")
public class Hospital {

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
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDMANAGER")
	private Manager manager;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDCONDITION")
	private Condition condition;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDDISTRICT")
	private District district;  
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDLOCATION")
	private Location location;

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

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
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
