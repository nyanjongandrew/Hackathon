package com.example.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_customer_details database table.
 * 
 */
@Entity
@Table(name="tbl_customer_details")
@NamedQuery(name="TblCustomerDetail.findAll", query="SELECT t FROM TblCustomerDetail t")
public class TblCustomerDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_number")
	private int idNumber;

	@Column(name="first_name")
	private String firstName;

	@Column(name="phone_number")
	private String phoneNumber;

	@Column(name="reg_date")
	private Timestamp regDate;

	@Column(name="second_name")
	private String secondName;

	private int status;

	private String surname;

	public TblCustomerDetail() {
	}

	public int getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getSecondName() {
		return this.secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}