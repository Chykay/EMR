package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.GlobalItemForm;

import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemCategoryOuterRecursive;
import org.calminfotech.system.models.GlobalItemReorder;

public interface GlobalItemBo {
	
	public List<GlobalItem> fetchAllByOrganisation();

	public GlobalItem getGlobalItemById(int id);

	public void save(GlobalItemForm iForm);


	public GlobalItemReorder getGlobalItemReorder(int id);

	public void save(GlobalItemReorder item);

	
	
	public void delete(GlobalItem gblItem);

	public void update(GlobalItemForm iForm);
		
	public List<GlobalItem> fetchAll();

	
	public List<GlobalItem> fetchAllByType(Integer typeid);
	

	public List<GlobalItem> fetchAllByType_room(Integer typeid);
	
	
	void save(GlobalItem Item);

	List<GlobalItemCategoryOuterRecursive> fetchAllTypesNew();

	public void update(GlobalItem Item);

	List<GlobalItemCategoryOuterRecursive> fetchAllTypes();

	public List<GlobalItem> fetchAllByOgranisation(int organisationId);

	public List<GlobalItem> fetchAll(Integer id);

	
	
	public List<GlobalItem> fetchTop50byOrganisation(Integer id);

	public List<GlobalItem> fetchAllByKind(String code);

	 
	public List<GlobalItem> fetchAllByKind_bed(String code);

	public List<GlobalItem> fetchAllByKind_room(String typeid);



	
	
}
