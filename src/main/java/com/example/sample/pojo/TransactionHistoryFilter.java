package com.example.sample.pojo;

import java.util.Date;
/**
 * 
 * @author user
 * Filtering of data by params
 */
public class TransactionHistoryFilter {
	private Date start_date;
	private Date end_date;
	private String phone;

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
