package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.GlobalItemCategoryForm;
import org.calminfotech.system.models.GlobalItemCategory;
import org.calminfotech.system.models.GlobalItemCategoryList;
import org.calminfotech.system.models.GlobalItemCategoryOuterRecursive;
import org.calminfotech.system.models.GlobalItemKind;
import org.calminfotech.system.models.GlobalItemType;

public interface GlobalItemCategoryBo {

	public GlobalItemCategory getGlobalCategoryItemById(int id);
	
	public List<GlobalItemCategory> fetchGlobalItemCategoryByItemTypeId(GlobalItemType globalItemTypId);

	public void save(GlobalItemCategory globalCategoryItem);
	
	public void saveCategoryForm(GlobalItemCategoryForm cForm);

	public void delete(GlobalItemCategory globalCategoryItem);

	public void update(GlobalItemCategory globalCategoryItem);
	
	public void updateForm(GlobalItemCategoryForm cForm);
		
	public List<GlobalItemCategory> fetchAll();

	public List<GlobalItemCategory> fetchAllByOrganisation();
	
	public List<GlobalItemCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	
	public List<GlobalItemKind> fetchAllKindByOrganisationByCategoryType(Integer cattypeid);
	
	List<GlobalItemCategory> fetchAll(int organisationid);

	GlobalItemCategory getGlobalItemCategoryById(int categoryId);

	List<GlobalItemCategoryOuterRecursive> fetchAllTypes();

	List<GlobalItemCategory> fetchAllbyOrganisation(int organisationid);

	List<GlobalItemCategory> fetchTop50byOrganisation(int organisationid);

	List<GlobalItemCategory> fetchAllCatlistByOrganisation(
			int organisationid);
}
