package org.calminfotech.system.boImpl;

import java.util.List;


import org.calminfotech.system.boInterface.DiseasesCategoryBo;
import org.calminfotech.system.daoInterface.DiseasesCategoryDao;

import org.calminfotech.system.forms.DiseasesCategoryForm;


import org.calminfotech.system.models.DiseasesCategory;
import org.calminfotech.system.models.DiseasesCategoryList;

//import org.calminfotech.system.models.DiseasesCategoryOuterRecursive;
import org.calminfotech.system.models.DiseasesType;
//import org.calminfotech.system.models.DiseasesCategoryOuterRecursive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DiseasesCategoryBoImpl implements DiseasesCategoryBo {
	@Autowired
	private DiseasesCategoryDao CategoryDao;

	@Override
	public List<DiseasesCategory> fetchAll(int organisationid) {
		// TODO Auto-generated method stub

		return CategoryDao.fetchAll(organisationid);
	}

	@Override
	public DiseasesCategory getDiseasesCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return CategoryDao.getCategoryById(categoryId);
	}

	@Override
	public void save(DiseasesCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.save(category);
	}



	@Override
	public void delete(DiseasesCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.delete(category);

	}

	/*@Override
	public List<DiseasesCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return CategoryDao.fetchAllTypes();
	}*/

	@Override
	public void update(DiseasesCategory category) {
		// TODO Auto-generated method stub
		CategoryDao.update(category);
	}

	@Override
	public List<DiseasesCategory> fetchDiseasesCategoryByItemTypeId(
			DiseasesType DiseasesTypId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<DiseasesCategory> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCategoryForm(DiseasesCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateForm(DiseasesCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DiseasesCategoryList> fetchAllCatlistByOrganisation(int organisationid) {

		return CategoryDao.fetchAllCatlistByOrganisation(organisationid);
				//.fetchAllCatlistByOrganisation(organisationid);
	}

	public List<DiseasesCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid)
	{
	return CategoryDao.fetchAllByOrganisationByCategoryType(cattypeid);	
 }
	
	

	@Override
	public List<DiseasesCategory> fetchTop50byOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return  CategoryDao.fetchTop50byOrganisation(organisationid);
	}

	@Override
	public DiseasesCategory getGlobalCategoryItemById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DiseasesCategory> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DiseasesCategory> fetchAllbyOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
