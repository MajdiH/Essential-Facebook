package com.mh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Email
	private String email;
	
	@Length(min = 5, max = 200)
	private String password;
	
	@Length(min = 1, max = 20)
	private String name;
	
	private String picture;
	
	private String introduction;
	
	private String profession;
	private String website;
	private String city;
	private String country;
	
	private Integer role_id;
	
	private java.sql.Timestamp created_at;
	
	/**
	 * Method updates already existed {@link User} object with values from the inputed argument.
	 * @param sPhone - Object which contains new Book values.
	 * @return {@link User} object to which this method applied.
	 */
	public User update(User sUser) {
		this.email = sUser.email;
		this.password = sUser.password;
		this.name = sUser.name;
		this.picture = sUser.picture;
		this.introduction = sUser.introduction;
		this.profession = sUser.profession;
		this.website = sUser.website;
		this.city = sUser.city;
		this.country = sUser.country;
		this.role_id = sUser.role_id;
		this.created_at = sUser.created_at;
		return this;
	}
	
	@Override
	public String toString() {
		return email + name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public java.sql.Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(java.sql.Timestamp created_at) {
		this.created_at = created_at;
	}

	

}
