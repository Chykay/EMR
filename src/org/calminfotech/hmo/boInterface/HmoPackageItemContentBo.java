package org.calminfotech.hmo.boInterface;

import java.util.List;

import org.calminfotech.hmo.forms.HmoPackageItemForm;

import org.calminfotech.hmo.models.HmoPackageItem;
import org.calminfotech.hmo.models.HmoPackageItemContent;


public interface HmoPackageItemContentBo {

	public List<HmoPackageItemContent> fetchAll();

	public HmoPackageItemContent getHmoItemContentById(int id);
	
	public void save(HmoPackageItemContent ehmoItem);
	
	public void delete(HmoPackageItemContent ehmoItem);

	public void update(HmoPackageItemContent ehmoItem);
	
}