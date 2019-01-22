package com.example.sample.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;


/**
 * The persistent class for the tbl_charges database table.
 * 
 */
@Entity
@Table(name="tbl_charges")
@NamedQuery(name="TblCharge.findAll", query="SELECT t FROM TblCharge t")
public class TblCharge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int max;
   
	@Column(name="reg_date")
	private Timestamp regDate;
	
	
	private int min;

	private int sendtoregistered;

	private int sendtounregistred;

	private int withdrawcharge;

	public TblCharge() {
	}

	public Timestamp getEffectiveDate() {
		return regDate;
	}

	public void setEffectiveDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMax() {
		return this.max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return this.min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSendtoregistered() {
		return this.sendtoregistered;
	}

	public void setSendtoregistered(int sendtoregistered) {
		this.sendtoregistered = sendtoregistered;
	}

	public int getSendtounregistred() {
		return this.sendtounregistred;
	}

	public void setSendtounregistred(int sendtounregistred) {
		this.sendtounregistred = sendtounregistred;
	}

	public int getWithdrawcharge() {
		return this.withdrawcharge;
	}

	public void setWithdrawcharge(int withdrawcharge) {
		this.withdrawcharge = withdrawcharge;
	}

}