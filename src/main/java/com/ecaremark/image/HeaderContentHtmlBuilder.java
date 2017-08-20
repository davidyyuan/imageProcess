package com.ecaremark.image;

import java.io.StringWriter;

import org.springframework.stereotype.Component;

import com.ecaremark.entity.Address;
import com.ecaremark.entity.MemberInfo;

@Component("headerContentHtmlBuilder")
class HeaderContentHtmlBuilder implements IHeaderContentBuilder {
	private static final String TR_CONST = "<tr>";
	private static final String TD_CLOSE_CONST = "</td>";
	
	@Override
	public String buildContent(MemberInfo memberInfo) {
		StringWriter sw = new StringWriter();
		sw.append("<table style=\"width:100%;padding: 5px;font-size:24px;\">");
		sw.append(TR_CONST + buildNames(memberInfo) + buildStreetAddress(memberInfo.address)
				+ buildPhone(memberInfo) + "</tr>");
		sw.append(TR_CONST + buildInternalId(memberInfo) + buildAddress(memberInfo.address) + buildEmail(memberInfo) + "</tr>");
		sw.append(TR_CONST + buildDob(memberInfo) + "</tr>");
		sw.append("</table>");
		return sw.toString();
	}

	private String buildNames(MemberInfo mberInfo) {
		return "<td>Last, First: " + mberInfo.lname + ", " + mberInfo.fname + TD_CLOSE_CONST;
	}

	private String buildStreetAddress(Address address) {
		return "<td>Street address: " + address.streetAddress + TD_CLOSE_CONST;
	}

	private String buildPhone(MemberInfo mberInfo) {
		return "<td>Phone: " + mberInfo.phone + TD_CLOSE_CONST;
	}

	private String buildEmail(MemberInfo mberInfo) {
		return "<td>Email: " + mberInfo.email + TD_CLOSE_CONST;
	}
	
	private String buildInternalId(MemberInfo mberInfo) {
		return "<td>Internal ID: " + mberInfo.memberId + TD_CLOSE_CONST;
	}

	private String buildAddress(Address address) {
		return "<td>City, Zipcode, State: " + address.city + ", " + address.zipCode + ", " + address.state + TD_CLOSE_CONST;
	}

	private String buildDob(MemberInfo mberInfo) {
		return "<td>DOB: " + mberInfo.dob + TD_CLOSE_CONST;
	}
}
