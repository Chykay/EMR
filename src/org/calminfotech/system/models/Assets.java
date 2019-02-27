package org.calminfotech.system.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assets")
public class Assets {
	
	@Id
	@GeneratedValue
	@Column(name = "assetId")
	private Integer assetId;
	
	@Column(name = "assetName")
	private String assetName;
	
	@Column(name = "assetQty")
	private Integer assetQty;
	
	@Column(name = "assetDesc")
	private String assetDesc;
	
	@Column(name = "asset_year_bought")
	private String assetYearBought;
	
	@Column(name = "asset_year_expired")
	private String assetYearExpired;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assetCatId")
	private AssetCategory assetCategory;

	//Getters and Setters
	
	public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public Integer getAssetQty() {
		return assetQty;
	}

	public void setAssetQty(Integer assetQty) {
		this.assetQty = assetQty;
	}

	public String getAssetDesc() {
		return assetDesc;
	}

	public void setAssetDesc(String assetDesc) {
		this.assetDesc = assetDesc;
	}

	public String getAssetYearBought() {
		return assetYearBought;
	}

	public void setAssetYearBought(String assetYearBought) {
		this.assetYearBought = assetYearBought;
	}

	public String getAssetYearExpired() {
		return assetYearExpired;
	}

	public void setAssetYearExpired(String assetYearExpired) {
		this.assetYearExpired = assetYearExpired;
	}

	public AssetCategory getAssetCategory() {
		return assetCategory;
	}

	public void setAssetCategory(AssetCategory assetCategory) {
		this.assetCategory = assetCategory;
	}
	
	
	
	
	
	
	

}
