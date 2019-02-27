package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.AssetCatgoryBo;
import org.calminfotech.system.daoInterface.AssetCategoryDao;
import org.calminfotech.system.models.AssetCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssetCatgoryBoImpl implements AssetCatgoryBo {

	@Autowired
	private AssetCategoryDao assetCategoryDao;
	
	@Override
	public List<AssetCategory> fetchAll() {
		return assetCategoryDao.fetchAll();
	}

	@Override
	public AssetCategory getAssetCategoryId(int id) {
		return assetCategoryDao.getAssetCategoryId(id);
	}

	@Override
	public void save(AssetCategory assetCategory) {
		assetCategoryDao.save(assetCategory);
	}

	@Override
	public void update(AssetCategory assetCategory) {
		assetCategoryDao.update(assetCategory);
	}

	@Override
	public void delete(AssetCategory assetCategory) {
		assetCategoryDao.delete(assetCategory);
	}

}
