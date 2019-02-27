package org.calminfotech.system.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asset_category")
public class AssetCategory {
	
	@Id
	@GeneratedValue
	@Column(name = "assetCatId")
	private Integer assetCatId;
	
	@Column(name = "assetCatName")
	private String assetCatName;
	
	@Column(name = "assetCatDesc")
	private String assetCatDesc;

	//Getters and Setters
	
	public Integer getAssetCatId() {
		return assetCatId;
	}

	public void setAssetCatId(Integer assetCatId) {
		this.assetCatId = assetCatId;
	}

	public String getAssetCatName() {
		return assetCatName;
	}

	public void setAssetCatName(String assetCatName) {
		this.assetCatName = assetCatName;
	}

	public String getAssetCatDesc() {
		return assetCatDesc;
	}

	public void setAssetCatDesc(String assetCatDesc) {
		this.assetCatDesc = assetCatDesc;
	}
	
	
	
	
	
	

}
