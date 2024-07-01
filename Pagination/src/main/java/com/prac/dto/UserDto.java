package com.prac.dto;

import java.util.List;

import com.prac.entity.Address;
import com.prac.entity.Payment;

public class UserDto {
	private Integer uid;
	private String uname;
	private String email;
	private String password;
	private Address address;
	private List<Payment> payment;
	private List<Order> orders;
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
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public UserDto(Integer uid, String uname, String email, String password, Address address, List<Payment> payment,
			List<Order> orders) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.payment = payment;
		this.orders = orders;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
