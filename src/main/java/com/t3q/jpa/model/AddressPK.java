package com.t3q.jpa.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the address database table.
 * 
 */
@Embeddable
public class AddressPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="EMAIL", insertable=false, updatable=false, unique=true, nullable=false, length=50)
	private String email;

	@Column(unique=true, nullable=false, length=200)
	private String address;

	public AddressPK() {
	}
	public AddressPK(String email, String address) {
		this.email = email;
		this.address = address;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AddressPK)) {
			return false;
		}
		AddressPK castOther = (AddressPK)other;
		return 
			this.email.equals(castOther.email)
			&& this.address.equals(castOther.address);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.email.hashCode();
		hash = hash * prime + this.address.hashCode();
		
		return hash;
	}
}