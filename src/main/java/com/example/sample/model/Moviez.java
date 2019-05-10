package com.example.sample.model;


import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the moviez database table.
 * 
 */
@Entity
@NamedQuery(name="Moviez.findAll", query="SELECT m FROM Moviez m")
public class Moviez implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="created_by")
	private String createdBy;

	@Lob
	private String description;

	@Column(name="last_updated")
	private Timestamp lastUpdated;

	@Lob
	private String recommendation;

	@Column(name="reg_date")
	private Timestamp regDate;

	private String title;

	@Column(name="updated_by")
	private String updatedBy;

	private int watched;

	public Moviez() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getRecommendation() {
		return this.recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getWatched() {
		return this.watched;
	}

	public void setWatched(int watched) {
		this.watched = watched;
	}

}