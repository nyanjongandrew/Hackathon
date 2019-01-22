package com.example.sample.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;



/**
 * The persistent class for the tbl_business_accounts database table.
 * 
 */
@Entity
@Table(name="tbl_business_accounts")
@NamedQuery(name="TblBusinessAccount.findAll", query="SELECT t FROM TblBusinessAccount t")
public class TblBusinessAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private String uuid;

	@Column(name="account_balance")
	private int accountBalance;

	@Column(name="account_name")
	private String accountName;

	@Column(name="account_no")
	private int accountNo;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Lob
	@Column(name="last_activity")
	private String lastActivity;

	private int status;

	public TblBusinessAccount() {
	}

	public String getString() {
		return this.uuid;
	}

	public void setString(String uuid) {
		this.uuid = uuid;
	}

	public int getAccountBalance() {
		return this.accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastActivity() {
		return this.lastActivity;
	}

	public void setLastActivity(String lastActivity) {
		this.lastActivity = lastActivity;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}