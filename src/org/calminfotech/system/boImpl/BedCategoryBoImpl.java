package org.calminfotech.system.boImpl;

import java.util.List;


import org.calminfotech.system.boInterface.BedCategoryBo;
import org.calminfotech.system.daoInterface.BedCategoryDao;

import org.calminfotech.system.forms.BedCategoryForm;


import org.calminfotech.system.models.BedCategory;
import org.calminfotech.system.models.BedCategoryList;

//import org.calminfotech.system.models.BedCategoryOuterRecursive;
import org.calminfotech.system.models.BedType;
//import org.calminfotech.system.models.BedCategoryOuterRecursive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BedCategoryBoImpl implements BedCategoryBo {
	@Autowired
	private BedCategoryDao CategoryDao;

	@Override
	public List<BedCategory> fetchAll(int organisationid) {
		// TODO Auto-generated method stub

		return CategoryDao.fetchAll(organisationid);
	}

	@Override
	public BedCategory getBedCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return CategoryDao.getCategoryById(categoryId);
	}

	@Override
	public void save(BedCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.save(category);
	}



	@Override
	public void delete(BedCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.delete(category);

	}

	/*@Override
	public List<BedCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return CategoryDao.fetchAllTypes();
	}*/

	@Override
	public void update(BedCategory category) {
		// TODO Auto-generated method stub
		CategoryDao.update(category);
	}

	@Override
	public List<BedCategory> fetchBedCategoryByItemTypeId(
			BedType BedTypId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<BedCategory> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCategoryForm(BedCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateForm(BedCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BedCategoryList> fetchAllCatlistByOrganisation(int organisationid) {

		return CategoryDao.fetchAllCatlistByOrganisation(organisationid);
				//.fetchAllCatlistByOrganisation(organisationid);
	}

	public List<BedCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid)
	{
	return CategoryDao.fetchAllByOrganisationByCategoryType(cattypeid);	
 }
	
	public List<BedCategory> fetchAllByOrganisationByCategoryHR(Integer hr)
	{
	return CategoryDao.fetchAllByOrganisationByCategoryHR(hr);	
 }
	

	@Override
	public List<BedCategory> fetchTop50byOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return  CategoryDao.fetchTop50byOrganisation(organisationid);
	}

	@Override
	public BedCategory getGlobalCategoryItemById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BedCategory> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BedCategory> fetchAllbyOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
