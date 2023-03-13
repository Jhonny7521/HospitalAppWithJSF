package com.tismart.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "DISTRICTS", schema = "USERRETO3")
public class District {

	@Id
	@Column(name="IDDISTRICT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="DISTRICTDESC")
	private String districtDescription;
	
	@Column(name="CREATEDAT", updatable = false)
	private Date createdAt;
	
	@Column(name="IDPROVINCE")
	private Integer idProvince;
	
//	@OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
//	private List<Hospital> hospital;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDistrictDescription() {
		return districtDescription;
	}

	public void setDistrictDescription(String districtDescription) {
		this.districtDescription = districtDescription;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getIdProvince() {
		return idProvince;
	}

	public void setIdProvince(Integer idProvince) {
		this.idProvince = idProvince;
	}

	@Override
	public String toString() {
		return "Districts [id=" + id + ", districtDescription=" + districtDescription + ", createdAt=" + createdAt
				+ ", province=" + idProvince + "]";
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
}
