package com.prac.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;
	private String uname;
	
	
	
	private String email;
	
//	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Payment> payment;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public User(Integer uid, String uname, String email, String password, Address address, List<Payment> payment) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.payment = payment;
	}



	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
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



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public List<Payment> getPayment() {
		return payment;
	}



	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}



	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", email=" + email + ", password=" + password + ", address="
				+ address + ", payment=" + payment + "]";
	}
	
	
	
}
