package org.calminfotech.system.daoInterface;
import java.util.List;

import org.calminfotech.system.forms.LabtestCategoryForm;
import org.calminfotech.system.models.LabtestCategory;

import org.calminfotech.system.models.LabtestCategoryList;
//import org.calminfotech.system.models.LabtestCategoryOuterRecursive;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface LabtestCategoryDao {

	public List<LabtestCategory> fetchAll(int organisationid);

	public LabtestCategory getCategoryById(int categoryId);

	//public List<LabtestCategoryOuterRecursive> fetchAllTypes();

	public void save(LabtestCategory category);

	public void update(LabtestCategory LabtestCategory);
	
	public List<LabtestCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	
	void delete(LabtestCategory category);

	public List<LabtestCategory> fetchAll();
	
	
	public List<LabtestCategory> fetchTop50byOrganisation(int id);

	public List<LabtestCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);

	
}
