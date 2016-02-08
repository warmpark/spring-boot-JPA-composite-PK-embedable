package com.t3q.jpa.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Table(name="address")
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AddressPK id;

	@Column(length=200)
	private String comments;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="EMAIL", nullable=false, insertable=false, updatable=false)
	@JsonIgnore
	private User user;

	public Address() {
	}

	public Address(String email, String address, String comments) {
		this.id = new AddressPK(email, address);
		this.comments = comments;
	}

	public Address(AddressPK addressPK) {
		this.id = addressPK;
	}

	public AddressPK getId() {
		return this.id;
	}

	public void setId(AddressPK id) {
		this.id = id;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}