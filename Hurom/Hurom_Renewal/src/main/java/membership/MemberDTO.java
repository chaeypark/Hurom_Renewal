package membership;

import java.sql.Date;

public class MemberDTO {
	private String memberid;
	private String memberpw;
	private String membername;
	private String email;
	private Date birthdate;
	private Date signupdate;
	private String membertel;
	private String membernum;
	private String gender;
	private String required;
	private String optional;
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
	public String getOptional() {
		return optional;
	}
	public void setOptional(String optional) {
		this.optional = optional;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getMemberpw() {
		return memberpw;
	}
	public void setMemberpw(String memberpw) {
		this.memberpw = memberpw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMembertel() {
		return membertel;
	}
	public void setMembertel(String membertel) {
		this.membertel = membertel;
	}
	public String getMembernum() {
		return membernum;
	}
	public void setMembernum(String membernum) {
		this.membernum = membernum;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public Date getSignupdate() {
		return signupdate;
	}
	public void setSignupdate(Date signupdate) {
		this.signupdate = signupdate;
	}
}
