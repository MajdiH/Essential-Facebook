package com.mh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "flows")
public class Flow {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Length(min = 20, max = 500)
	private String body;
	
	private java.sql.Timestamp created_at;
	private Integer created_by;
	private Integer likes;
	
	
	/**
	 * Method updates already existed {@link Flow} object with values from the inputed argument.
	 * @param sPhone - Object which contains new Book values.
	 * @return {@link Flow} object to which this method applied.
	 */
	public Flow update(Flow sFlow) {
		this.body = sFlow.body;
		this.created_at = sFlow.created_at;
		this.created_by = sFlow.created_by;
		this.likes = sFlow.likes;
		return this;
	}
	
	@Override
	public String toString() {
		return body + created_at + created_by + likes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public java.sql.Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(java.sql.Timestamp created_at) {
		this.created_at = created_at;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	
	
	

}
