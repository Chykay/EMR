package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.XrayCategoryForm;
import org.calminfotech.system.forms.XrayCategoryForm;
import org.calminfotech.system.models.XrayCategory;
import org.calminfotech.system.models.XrayCategory;
import org.calminfotech.system.models.XrayCategoryList;
//import org.calminfotech.system.models.XrayCategoryOuterRecursive;
import org.calminfotech.system.models.XrayType;

public interface XrayCategoryBo {

	public XrayCategory getGlobalCategoryItemById(int id);
	
	public List<XrayCategory> fetchXrayCategoryByItemTypeId(XrayType XrayTypId);

	public void save(XrayCategory globalCategoryItem);
	
	public void saveCategoryForm(XrayCategoryForm cForm);

	public void delete(XrayCategory globalCategoryItem);

	public void update(XrayCategory globalCategoryItem);
	
	public void updateForm(XrayCategoryForm cForm);
		
	public List<XrayCategory> fetchAll();

	public List<XrayCategory> fetchAllByOrganisation();
	
	public List<XrayCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	

	List<XrayCategory> fetchAll(int organisationid);

	XrayCategory getXrayCategoryById(int categoryId);

	//List<XrayCategoryOuterRecursive> fetchAllTypes();

	List<XrayCategory> fetchAllbyOrganisation(int organisationid);

	List<XrayCategory> fetchTop50byOrganisation(int organisationid);

	List<XrayCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);
	
}
