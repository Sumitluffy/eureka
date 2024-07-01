package com.prac.dto;

public class Order {
	private Integer oid;
	
	private String category;
	private String status;
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(Integer oid, String category, String status) {
		super();
		this.oid = oid;
		this.category = category;
		this.status = status;
	}
	
	
	
}
