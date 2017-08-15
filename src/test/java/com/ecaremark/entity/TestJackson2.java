package com.ecaremark.entity;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJackson2 {

	@Test
	public void test_entity() throws IOException {
		ObjectMapper om = new ObjectMapper();
		JacksonEntity e = new JacksonEntity();
		e.setField1("test1");
		e.setFIEld2("testme");
		e.setFIeld2("testme3");
		String json = om.writeValueAsString(e);
		System.out.println(json);
		JacksonEntity je = om.readValue(json, JacksonEntity.class);
		System.out.println("JE: " + je);
		
	}
}

class JacksonEntity {
	private String field1;
	private String FIEld2;
	private String FIeld2;
	

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	@JsonProperty("FIEld2")
	public String getFIEld2() {
		return FIEld2;
	}

	@JsonProperty("FIEld2")
	public void setFIEld2(String fIEld2) {
		FIEld2 = fIEld2;
	}

	@JsonProperty("FIeld2")
	public String getFIeld2() {
		return FIeld2;
	}


	@JsonProperty("FIeld2")
	public void setFIeld2(String fIeld2) {
		FIeld2 = fIeld2;
	}

	@Override
	public String toString() {
		return "JacksonEntity [field1=" + field1 + ", FIEld2=" + FIEld2 + ", FIeld2=" + FIeld2 + "]";
	}
}