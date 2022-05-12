package com.kiran.demo.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Component

@Table(name = "Details")
public class Details {

	

	public Details(@Pattern( regexp = "^\\d{10}$", message = " Account Id must be 10 digits" ) String accountid,
			       @NotNull String customername, 
			       @NotNull int pan, 
			       @Past(message = "Enter valid date") Date dob,
			       String accounttype,
			       boolean accountstatus, 
			       BigDecimal accountbal,
			       @NotNull @Pattern(regexp = " ^\\d{10}$ ", message = " PhoneNumber must be 10 digits") 
	               String phonenum,
			       @NotBlank @Email(message = " Enter a Valid Email Id ") String email,
			       long mab) {
		super();
		this.accountid = accountid;
		this.customername = customername;
		this.pan = pan;
		this.dob = dob;
		this.accounttype = accounttype;
		this.accountstatus = accountstatus;
		this.accountbal = accountbal;
		this.phonenum = phonenum;
		this.email = email;
		this.mab = mab;
	}

	@Id
	@Column(name = "Account_Id")
	@Pattern( regexp = "^\\d{10}$", message = " Account Id must be 10 digits" )
	private String accountid;

	@NotNull
	@Column(name = "Customer_Name")
	private String customername;

	@NotNull
	@Column(name = "Tax_Id_Number")
	private int pan;

	@Column(name = "DOB")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern ="dd/MM/yyyy")
	@Past(message="Enter Valid Date")
	private Date dob;
	
	@Column(name = "Account_Type")
	private String accounttype;

	@Column(name = "Account_Status")
	private boolean accountstatus;

	@Column(name = "Account_Bal")
	private BigDecimal accountbal;

	@NotNull
	@Column(name = "Phone_No")
	@Pattern( regexp = "^\\d{10}$", message = " PhoneNumber must be 10 digits" )
	private String phonenum;

	@NotBlank
	@Email(message = " Enter a Valid Email Id ")
	@Column(name = "Email")
	private String email;

	@Column(name = "MAB")
	private long mab;

	public Details() {
		super();
		
	}
	
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public int getPan() {
		return pan;
	}

	public void setPan(int pan) {
		this.pan = pan;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public boolean isAccountstatus() {
		return accountstatus;
	}

	public void setAccountstatus(boolean accountstatus) {
		this.accountstatus = accountstatus;
	}

	public BigDecimal getAccountbal() {
		return accountbal;
	}

	public void setAccountbal(BigDecimal accountbal) {
		this.accountbal = accountbal;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMab() {
		return mab;
	}

	public void setMab(long mab) {
		this.mab = mab;
	}

	@Override
	public String toString() {
		return "Details [accountid=" + accountid + ", customername=" + customername + ", pan=" + pan + ", dob=" + dob
				+ ", accounttype=" + accounttype + ", accountstatus=" + accountstatus + ", accountbal=" + accountbal
				+ ", phonenum=" + phonenum + ", email=" + email + ", mab=" + mab + "]";
	}
	
	
}
