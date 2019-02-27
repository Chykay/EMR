package org.calminfotech.system.daoInterface;
import java.util.List;

import org.calminfotech.system.forms.DrugsCategoryForm;
import org.calminfotech.system.models.BedCategory;

import org.calminfotech.system.models.BedCategoryList;
//import org.calminfotech.system.models.DrugsCategoryOuterRecursive;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface BedCategoryDao {

	public List<BedCategory> fetchAll(int organisationid);

	public BedCategory getCategoryById(int categoryId);

	//public List<DrugsCategoryOuterRecursive> fetchAllTypes();

	public void save(BedCategory category);

	public void update(BedCategory BedCategory);
	
	public List<BedCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	public List<BedCategory> fetchAllByOrganisationByCategoryHR(Integer hr);
	void delete(BedCategory category);

	public List<BedCategory> fetchAll();
	
	
	public List<BedCategory> fetchTop50byOrganisation(int id);

	public List<BedCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);

	
}
