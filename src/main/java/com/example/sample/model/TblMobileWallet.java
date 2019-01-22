package com.example.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;


/**
 * The persistent class for the tbl_mobile_wallet database table.
 * 
 */
@Entity
@Table(name="tbl_mobile_wallet")
@NamedQuery(name="TblMobileWallet.findAll", query="SELECT t FROM TblMobileWallet t")
public class TblMobileWallet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private String uuid;

	@Column(name="account_bal")
	private int accountBal;

	private String firstname;

	@Lob
	@Column(name="last_activity")
	private String lastActivity;

	@Column(name="phone_number")
	private String phoneNumber;

	private String pin;

	@Column(name="reg_date")
	private Timestamp regDate;

	private String secondname;

	private int status;

	private String surname;

	public TblMobileWallet() {
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getAccountBal() {
		return this.accountBal;
	}

	public void setAccountBal(int accountBal) {
		this.accountBal = accountBal;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastActivity() {
		return this.lastActivity;
	}

	public void setLastActivity(String lastActivity) {
		this.lastActivity = lastActivity;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getSecondname() {
		return this.secondname;
	}

	public void setSecondname(String secondname) {
		this.secondname = secondname;
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