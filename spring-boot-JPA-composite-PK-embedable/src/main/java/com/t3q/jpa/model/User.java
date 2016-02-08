package com.t3q.jpa.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	private String email;

	@Column(nullable=false)
	private int age;

	@Column(length=50)
	private String name;

	@Column(name="REG_USER", length=10)
	private String regUser;

	@Column(name="USE_YN", length=1)
	private String useYn;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="user", cascade={CascadeType.ALL})
	private List<Address> addresses;

	//bi-directional many-to-one association to UserGroup
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="GROUP_ID", nullable=false)
	@JsonIgnore
	private UserGroup userGroup;

	public User() {
	}

	public User(String email) {
		this.email = email;
	}

	public User(String email, Long groupId, String name, Integer age, String useYn, String regUser) {
		this.email = email;
		this.userGroup = new UserGroup(groupId);
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegUser() {
		return this.regUser;
	}

	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}

	public String getUseYn() {
		return this.useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setUser(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setUser(null);

		return address;
	}

	public UserGroup getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

}