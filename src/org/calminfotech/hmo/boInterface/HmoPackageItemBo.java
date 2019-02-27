package org.calminfotech.hmo.boInterface;

import java.util.List;

import org.calminfotech.hmo.forms.HmoPackageItemForm;

import org.calminfotech.hmo.models.HmoPackageItem;


public interface HmoPackageItemBo {

	public List<HmoPackageItem> fetchAll();

	public HmoPackageItem getHmoItemById(int id);
	
	public HmoPackageItem getHmoItemByAll(int id);
	
	
	public void save(HmoPackageItem ehmoItem);
	
	public void delete(HmoPackageItem ehmoItem);

	public void update(HmoPackageItem ehmoItem);
	
}