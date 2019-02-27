package org.calminfotech.hmo.daoInterface;

import java.util.List;

import org.calminfotech.hmo.models.HmoPackageItem;

public interface HmoPackageItemDao {

	public List<HmoPackageItem> fetchAll();

	public HmoPackageItem getHmoItemById(int id);
	

	public void save(HmoPackageItem ehmoItem);

	public void delete(HmoPackageItem ehmoItem);

	public void update(HmoPackageItem ehmoItem);

	public HmoPackageItem getHmoItemByAll(int id);

}
