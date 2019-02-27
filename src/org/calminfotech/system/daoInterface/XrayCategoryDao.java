package org.calminfotech.system.daoInterface;
import java.util.List;

import org.calminfotech.system.forms.XrayCategoryForm;
import org.calminfotech.system.models.XrayCategory;

import org.calminfotech.system.models.XrayCategoryList;
//import org.calminfotech.system.models.XrayCategoryOuterRecursive;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface XrayCategoryDao {

	public List<XrayCategory> fetchAll(int organisationid);

	public XrayCategory getCategoryById(int categoryId);

	//public List<XrayCategoryOuterRecursive> fetchAllTypes();

	public void save(XrayCategory category);

	public void update(XrayCategory XrayCategory);
	
	public List<XrayCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	
	void delete(XrayCategory category);

	public List<XrayCategory> fetchAll();
	
	
	public List<XrayCategory> fetchTop50byOrganisation(int id);

	public List<XrayCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);

	
}
