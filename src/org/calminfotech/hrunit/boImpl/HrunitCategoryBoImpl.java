package org.calminfotech.hrunit.boImpl;

import java.util.List;


import org.calminfotech.hrunit.boInterface.HrunitCategoryBo;
import org.calminfotech.hrunit.daoInterface.HrunitCategoryDao;
import org.calminfotech.hrunit.forms.HrunitCategoryForm;
import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.hrunit.models.HrunitCategoryList;
import org.calminfotech.hrunit.models.HrunitType;




//import org.calminfotech.system.models.HrunitCategoryOuterRecursive;
//import org.calminfotech.system.models.HrunitCategoryOuterRecursive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HrunitCategoryBoImpl implements HrunitCategoryBo {
	@Autowired
	private HrunitCategoryDao CategoryDao;

	@Override
	public List<HrunitCategory> fetchAll(int organisationid) {
		// TODO Auto-generated method stub

		return CategoryDao.fetchAll(organisationid);
	}

	@Override
	public HrunitCategory getHrunitCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return CategoryDao.getCategoryById(categoryId);
	}

	@Override
	public void save(HrunitCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.save(category);
	}



	@Override
	public void delete(HrunitCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.delete(category);

	}

	/*@Override
	public List<HrunitCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return CategoryDao.fetchAllTypes();
	}*/

	@Override
	public void update(HrunitCategory category) {
		// TODO Auto-generated method stub
		CategoryDao.update(category);
	}

	@Override
	public List<HrunitCategory> fetchHrunitCategoryByItemTypeId(
			HrunitType HrunitTypId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<HrunitCategory> fetchAllByOrganisationbyqueue() {
		// TODO Auto-generated method stub
		return CategoryDao.fetchAllByOrganisationbyqueue();
	}
	
	

	@Override
	public List<HrunitCategory> fetchAllByOrganisationbyqueuebypoint(Integer pointid) {
		// TODO Auto-generated method stub
		return CategoryDao.fetchAllByOrganisationbyqueuebypoint(pointid);
	}

	@Override
	public void saveCategoryForm(HrunitCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateForm(HrunitCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<HrunitCategoryList> fetchAllCatlistByOrganisation(int organisationid) {

		return CategoryDao.fetchAllCatlistByOrganisation(organisationid);
				//.fetchAllCatlistByOrganisation(organisationid);
	}

	public List<HrunitCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid)
	{
	return CategoryDao.fetchAllByOrganisationByCategoryType(cattypeid);	
 }
	
	

	@Override
	public List<HrunitCategory> fetchTop50byOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return  CategoryDao.fetchTop50byOrganisation(organisationid);
	}

	

	

	@Override
	public List<HrunitCategory> fetchAllbyOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		
		
		return CategoryDao.fetchAllbyOrganisation(organisationid);
	}

	@Override
	public List<HrunitCategoryList> fetchAllCategoryList(int organisationid) {
		// TODO Auto-generated method stub
		 return null;
	}

	
}
