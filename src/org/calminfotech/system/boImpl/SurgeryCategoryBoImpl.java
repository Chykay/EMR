package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.SurgeryCategoryBo;
import org.calminfotech.system.daoInterface.SurgeryCategoryDao;
import org.calminfotech.system.forms.SurgeryCategoryForm;
import org.calminfotech.system.models.SurgeryCategory;
import org.calminfotech.system.models.SurgeryCategoryList;
import org.calminfotech.system.models.SurgeryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.calminfotech.system.models.SurgeryCategoryOuterRecursive;
//import org.calminfotech.system.models.SurgeryCategoryOuterRecursive;

@Service
@Transactional
public class SurgeryCategoryBoImpl implements SurgeryCategoryBo {
	@Autowired
	private SurgeryCategoryDao CategoryDao;

	@Override
	public List<SurgeryCategory> fetchAll(int organisationid) {
		// TODO Auto-generated method stub

		return CategoryDao.fetchAll(organisationid);
	}

	@Override
	public SurgeryCategory getSurgeryCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return CategoryDao.getCategoryById(categoryId);
	}

	@Override
	public void save(SurgeryCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.save(category);
	}



	@Override
	public void delete(SurgeryCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.delete(category);

	}

	/*@Override
	public List<SurgeryCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return CategoryDao.fetchAllTypes();
	}*/

	@Override
	public void update(SurgeryCategory category) {
		// TODO Auto-generated method stub
		CategoryDao.update(category);
	}

	@Override
	public List<SurgeryCategory> fetchSurgeryCategoryByItemTypeId(
			SurgeryType SurgeryTypId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<SurgeryCategory> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCategoryForm(SurgeryCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateForm(SurgeryCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SurgeryCategoryList> fetchAllCatlistByOrganisation(int organisationid) {

		return CategoryDao.fetchAllCatlistByOrganisation(organisationid);
				//.fetchAllCatlistByOrganisation(organisationid);
	}

	public List<SurgeryCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid)
	{
	return CategoryDao.fetchAllByOrganisationByCategoryType(cattypeid);	
 }
	
	

	@Override
	public List<SurgeryCategory> fetchTop50byOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return  CategoryDao.fetchTop50byOrganisation(organisationid);
	}

	@Override
	public SurgeryCategory getGlobalCategoryItemById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SurgeryCategory> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SurgeryCategory> fetchAllbyOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
