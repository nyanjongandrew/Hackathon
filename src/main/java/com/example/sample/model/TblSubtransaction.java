package com.example.sample.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the tbl_subtransactions database table.
 * 
 */
@Entity
@Table(name="tbl_subtransactions")
@NamedQuery(name="TblSubtransaction.findAll", query="SELECT t FROM TblSubtransaction t")
public class TblSubtransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private String uuid;

	private int amount;

//	@Column(name="amount_type")
//	private int amountType;

	private String phone;

	private String receipt;

	@Column(name="request_id")
	private int requestId;

	@Column(name="transaction_type")
	private String transactionType;

	public TblSubtransaction() {
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

	/*public int getAmountType() {
		return this.amountType;
	}

	public void setAmountType(int amountType) {
		this.amountType = amountType;
	}*/

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReceipt() {
		return this.receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public int getRequestId() {
		return this.requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

}