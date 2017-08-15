package com.ecaremark.entity;

public class ProcessImageRequest {
	public MemberInfo memberInfo;
	public String fileName;
	public String imageContentBase64;
	@Override
	public String toString() {
		return "ProcessImageRequest [memberInfo=" + memberInfo + ", fileName=" + fileName + ", imageContentBase64="
				+ imageContentBase64 + "]";
	}
	
}
