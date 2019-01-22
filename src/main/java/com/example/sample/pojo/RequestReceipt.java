package com.example.sample.pojo;
/**
 * 
 * @author user
 * Holds request and receipt details for unique subtransctions
 */
public class RequestReceipt {
	private String receipt;
	private Integer request;

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public Integer getRequest() {
		return request;
	}

	public void setRequest(Integer request) {
		this.request = request;
	}

}
