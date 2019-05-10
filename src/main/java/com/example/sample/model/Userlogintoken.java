package com.example.sample.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the userlogintoken database table.
 * 
 */
@Entity
@NamedQuery(name="Userlogintoken.findAll", query="SELECT u FROM Userlogintoken u")
public class Userlogintoken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String tokenId;

	private String refreshtime;

	@Lob
	private String token;

	private String userid;

	public Userlogintoken() {
	}

	public String getTokenId() {
		return this.tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getRefreshtime() {
		return this.refreshtime;
	}

	public void setRefreshtime(String refreshtime) {
		this.refreshtime = refreshtime;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}