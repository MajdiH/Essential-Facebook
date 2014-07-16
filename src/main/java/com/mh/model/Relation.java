package com.mh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relations")
public class Relation {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer applicant;
	private Integer receiver;
	
	private boolean enabled;
	
	/**
	 * Method updates already existed {@link Relation} object with values from the inputed argument.
	 * @param sRelation - Object which contains new Relation values.
	 * @return {@link Relation} object to which this method applied.
	 */
	public Relation update(Relation sRelation) {
		this.applicant = sRelation.applicant;
		this.receiver = sRelation.receiver;
		this.enabled = sRelation.enabled;
		return this;
	}
	
	@Override
	public String toString() {
		return applicant +" , "+ receiver +" and "+ enabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApplicant() {
		return applicant;
	}

	public void setApplicant(Integer applicant) {
		this.applicant = applicant;
	}

	public Integer getReceiver() {
		return receiver;
	}

	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	

}
