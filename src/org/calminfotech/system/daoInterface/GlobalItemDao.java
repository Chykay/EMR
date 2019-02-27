package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemCategory;
import org.calminfotech.system.models.GlobalItemCategoryOuterRecursive;
import org.calminfotech.system.models.GlobalItemReorder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface GlobalItemDao {

	public List<GlobalItem> fetchAll(int id);
	
	public List<GlobalItem> fetchAll();
	
	public List<GlobalItem> fetchAllByOgranisation(int organisationId);

	//public GlobalItem getCategoryItemById(int itemId);

	public GlobalItemReorder getGlobalItemReorder(int id);

	public void save(GlobalItemReorder item);


	public List<GlobalItemCategoryOuterRecursive> fetchAllTypesNew();

	public void save(GlobalItem globalitem);

	public void update(GlobalItem globalitem);

	public void delete(GlobalItem globalitem);
	
	public List<GlobalItem> fetchAllByType(Integer typeid);
	
	public List<GlobalItem> fetchAllByKind(Integer typeid);
	
	public List<GlobalItem> fetchAllByKind(String code);
	
	
	public List<GlobalItem> fetchAllByType_room(Integer typeid);
	

	
	public List<GlobalItem> fetchTop50byOrganisation(int id);


	public GlobalItem getGlobalItemById(int id);

	public List<GlobalItem> fetchAllByKind_room(String code);

	public List<GlobalItem> fetchAllByKind_bed(String code);

}
