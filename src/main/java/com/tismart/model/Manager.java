package com.tismart.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "MANAGERS", schema = "USERRETO3")
public class Manager {

	@Id
	@Column(name = "IDMANAGER")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "MANAGERDESC")
	private String managerDescription;
	
	@Column(name = "CREATEDAT", updatable = false)
	private Date createdAt;
	
	@OneToOne(mappedBy="manager", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Hospital hospital;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManagerDescription() {
		return managerDescription;
	}

	public void setManagerDescription(String managerDescription) {
		this.managerDescription = managerDescription;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", managerDescription=" + managerDescription + ", createdAt=" + createdAt
				+ ", hospital=" + hospital + "]";
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
}
