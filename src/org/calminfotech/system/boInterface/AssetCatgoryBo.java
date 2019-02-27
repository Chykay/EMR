package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.AssetCategory;

public interface AssetCatgoryBo {
	
	public List<AssetCategory> fetchAll();	

	public AssetCategory getAssetCategoryId(int id);
	
	public void save(AssetCategory assetCategory);
	
	public void update(AssetCategory assetCategory);
	
	public void delete(AssetCategory assetCategory);

}
