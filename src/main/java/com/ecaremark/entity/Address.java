package com.ecaremark.entity;

public class Address {
	public final String streetAddress;
	public final String city;
	public final String zipCode;
	public final String state;
	
	public Address(String streetAddress, String city, String zipCode, String state) {
		super();
		this.streetAddress = streetAddress;
		this.city = city; 
		this.zipCode = zipCode;
		this.state = state;
	}
}
