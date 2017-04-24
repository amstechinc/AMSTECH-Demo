package com.amstech.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the restaurant database table.
 * 
 */
@Entity
@Table(name = "restaurant")
@NamedQuery(name = "Restaurant.findAll", query = "SELECT r FROM Restaurant r")
public class Restaurant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String capacity;

	@Column(name = "category_id")
	private int categoryId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "is_deleted")
	private int isDeleted;

	@Column(name = "license_number")
	private String licenseNumber;

	private String logo;

	@Column(name = "max_cost")
	private double maxCost;

	@Column(name = "min_cost")
	private double minCost;

	private String name;

	private String specialities;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	private Date updateTime;

	// bi-directional many-to-one association to RestBranch
	@OneToMany(mappedBy = "restaurant")
	private List<RestBranch> restBranches;

	// bi-directional many-to-one association to City
	@ManyToOne
	private City city;

	public Restaurant() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCapacity() {
		return this.capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getLicenseNumber() {
		return this.licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public double getMaxCost() {
		return this.maxCost;
	}

	public void setMaxCost(double maxCost) {
		this.maxCost = maxCost;
	}

	public double getMinCost() {
		return this.minCost;
	}

	public void setMinCost(double minCost) {
		this.minCost = minCost;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialities() {
		return this.specialities;
	}

	public void setSpecialities(String specialities) {
		this.specialities = specialities;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<RestBranch> getRestBranches() {
		return this.restBranches;
	}

	public void setRestBranches(List<RestBranch> restBranches) {
		this.restBranches = restBranches;
	}

	public RestBranch addRestBranch(RestBranch restBranch) {
		getRestBranches().add(restBranch);
		restBranch.setRestaurant(this);

		return restBranch;
	}

	public RestBranch removeRestBranch(RestBranch restBranch) {
		getRestBranches().remove(restBranch);
		restBranch.setRestaurant(null);

		return restBranch;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}