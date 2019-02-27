package org.calminfotech.system.forms;




public class AssetCategoryForm {	

	private Integer assetCatId;	
	
	//@NotBlank(message = "Field cannot be empty!")
	private String assetCatName;
	
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
