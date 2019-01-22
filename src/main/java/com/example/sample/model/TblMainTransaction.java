package com.example.sample.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_main_transactions database table.
 * 
 */
@Entity
@Table(name="tbl_main_transactions")
@NamedQuery(name="TblMainTransaction.findAll", query="SELECT t FROM TblMainTransaction t")
public class TblMainTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private String uuid;

	private int amount;

	private int charge;

	private Timestamp dte;

	private String partya;

	private String partyb;

	private String receipt;

	@Column(name="request_id")
	private long requestId;

	private int status;

	public TblMainTransaction() {
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCharge() {
		return this.charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public Timestamp getDate() {
		return this.dte;
	}

	public void setDate(Timestamp dte) {
		this.dte = dte;
	}

	public String getPartya() {
		return this.partya;
	}

	public void setPartya(String partya) {
		this.partya = partya;
	}

	public String getPartyb() {
		return this.partyb;
	}

	public void setPartyb(String partyb) {
		this.partyb = partyb;
	}

	public String getReceipt() {
		return this.receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public long getRequestId() {
		return this.requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}