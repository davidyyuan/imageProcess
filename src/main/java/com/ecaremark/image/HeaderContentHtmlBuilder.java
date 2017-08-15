package com.ecaremark.image;

import java.io.StringWriter;

import org.springframework.stereotype.Component;

import com.ecaremark.entity.Address;
import com.ecaremark.entity.MemberInfo;

@Component("headerContentHtmlBuilder")
class HeaderContentHtmlBuilder implements IHeaderContentBuilder {
	
	@Override
	public String buildContent(MemberInfo memberInfo) {
		StringWriter sw = new StringWriter();
		sw.append("<table style=\"width:100%;padding: 5px;font-size:24px;\">");
		sw.append("<tr>" + buildNames(memberInfo) + buildStreetAddress(memberInfo.address)
				+ buildPhone(memberInfo) + "</tr>");
		sw.append("<tr>" + buildInternalId(memberInfo) + buildAddress(memberInfo.address) + buildEmail(memberInfo) + "</tr>");
		sw.append("<tr>" + buildDob(memberInfo) + "</tr>");
		sw.append("</table>");
		return sw.toString();
	}

	private String buildNames(MemberInfo mberInfo) {
		return "<td>Last, First: " + mberInfo.lname + ", " + mberInfo.fname + "</td>";
	}

	private String buildStreetAddress(Address address) {
		return "<td>Street address: " + address.streetAddress + "</td>";
	}

	private String buildPhone(MemberInfo mberInfo) {
		return "<td>Phone: " + mberInfo.phone + "</td>";
	}

	private String buildEmail(MemberInfo mberInfo) {
		return "<td>Email: " + mberInfo.email + "</td>";
	}
	
	private String buildInternalId(MemberInfo mberInfo) {
		return "<td>Internal ID: " + mberInfo.memberId + "</td>";
	}

	private String buildAddress(Address address) {
		return "<td>City, Zipcode, State: " + address.city + ", " + address.zipCode + ", " + address.state + "</td>";
	}

	private String buildDob(MemberInfo mberInfo) {
		return "<td>DOB: " + mberInfo.dob + "</td>";
	}
}
