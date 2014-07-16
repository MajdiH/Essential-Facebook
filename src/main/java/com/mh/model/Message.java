package com.mh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "messages")
public class Message {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer u_from;
	private Integer u_to;
	
	@Length(min = 20, max = 500)
	private String body;
	
	private java.sql.Timestamp created_at;
	
	/**
	 * Method updates already existed {@link Message} object with values from the inputed argument.
	 * @param sMessage - Object which contains new Message values.
	 * @return {@link Message} object to which this method applied.
	 */
	public Message update(Message sMessage) {
		this.u_from = sMessage.u_from;
		this.u_to = sMessage.u_to;
		this.body = sMessage.body;
		this.created_at = sMessage.created_at;
		return this;
	}
	
	@Override
	public String toString() {
		return u_from + u_to + body + created_at;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getU_from() {
		return u_from;
	}

	public void setU_from(Integer u_from) {
		this.u_from = u_from;
	}

	public Integer getU_to() {
		return u_to;
	}

	public void setU_to(Integer u_to) {
		this.u_to = u_to;
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


}
