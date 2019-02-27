package org.calminfotech.system.daoInterface;
import java.util.List;

import org.calminfotech.system.forms.DiseasesCategoryForm;
import org.calminfotech.system.models.DiseasesCategory;

import org.calminfotech.system.models.DiseasesCategoryList;
//import org.calminfotech.system.models.DiseasesCategoryOuterRecursive;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface DiseasesCategoryDao {

	public List<DiseasesCategory> fetchAll(int organisationid);

	public DiseasesCategory getCategoryById(int categoryId);

	//public List<DiseasesCategoryOuterRecursive> fetchAllTypes();

	public void save(DiseasesCategory category);

	public void update(DiseasesCategory DiseasesCategory);
	
	public List<DiseasesCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	
	void delete(DiseasesCategory category);

	public List<DiseasesCategory> fetchAll();
	
	
	public List<DiseasesCategory> fetchTop50byOrganisation(int id);

	public List<DiseasesCategoryList> fetchAllCatlistByOrganisation(
			int organisationid);

	
}
