package org.calminfotech.hmo.daoInterface;

import java.util.List;

import org.calminfotech.hmo.models.HmoPackageItem;
import org.calminfotech.hmo.models.HmoPackageItemContent;

public interface HmoPackageItemContentDao {

	public List<HmoPackageItemContent> fetchAll();

	public HmoPackageItemContent getHmoItemContentById(int id);

	public void save(HmoPackageItemContent ehmoItem);

	public void delete(HmoPackageItemContent ehmoItem);

	public void update(HmoPackageItemContent ehmoItem);

}
