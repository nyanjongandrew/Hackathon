package com.example.sample.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TransferRequest {
	@NotNull
	private String receiverphoneno;
	
	private String senderPhoneNo;
	@NotNull
	private Integer amount;
	private String pin;
    private Integer transType;
	public String getPhoneno() {
		return receiverphoneno;
	}

	public void setPhoneno(String receiverphoneno) {
		this.receiverphoneno = receiverphoneno;
	}

	public Integer getAmount() {
		return amount;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Integer getTransType() {
		return transType;
	}

	public void setTransType(Integer transType) {
		this.transType = transType;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getSenderPhoneNo() {
		return senderPhoneNo;
	}

	public void setSenderPhoneNo(String senderPhoneNo) {
		this.senderPhoneNo = senderPhoneNo;
	}

}
