package com.ecaremark.entity;

public class MemberInfo {
	public final long memberId;
	public final String fname;
	public final String lname;
	public final String dob;
	public final Address address;
	public final String phone;
	public final String email;
	
	
	public MemberInfo(long memberId, String fname, String lname, String dob, Address address, String phone,
			String email) {
		super();
		this.memberId = memberId;
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}


	@Override
	public String toString() {
		return "MemberInfo [memberId=" + memberId + ", fname=" + fname + ", lname=" + lname + ", dob=" + dob
				+ ", address=" + address + ", phone=" + phone + ", email=" + email + "]";
	}
	
}
