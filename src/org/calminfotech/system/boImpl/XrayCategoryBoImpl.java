package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.XrayCategoryBo;
import org.calminfotech.system.daoInterface.XrayCategoryDao;
import org.calminfotech.system.forms.XrayCategoryForm;
import org.calminfotech.system.models.XrayCategory;
import org.calminfotech.system.models.XrayCategoryList;
import org.calminfotech.system.models.XrayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.calminfotech.system.models.XrayCategoryOuterRecursive;
//import org.calminfotech.system.models.XrayCategoryOuterRecursive;

@Service
@Transactional
public class XrayCategoryBoImpl implements XrayCategoryBo {
	@Autowired
	private XrayCategoryDao CategoryDao;

	@Override
	public List<XrayCategory> fetchAll(int organisationid) {
		// TODO Auto-generated method stub

		return CategoryDao.fetchAll(organisationid);
	}

	@Override
	public XrayCategory getXrayCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return CategoryDao.getCategoryById(categoryId);
	}

	@Override
	public void save(XrayCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.save(category);
	}



	@Override
	public void delete(XrayCategory category) {
		// TODO Auto-generated method stub

		CategoryDao.delete(category);

	}

	/*@Override
	public List<XrayCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return CategoryDao.fetchAllTypes();
	}*/

	@Override
	public void update(XrayCategory category) {
		// TODO Auto-generated method stub
		CategoryDao.update(category);
	}

	@Override
	public List<XrayCategory> fetchXrayCategoryByItemTypeId(
			XrayType XrayTypId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<XrayCategory> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCategoryForm(XrayCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateForm(XrayCategoryForm cForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<XrayCategoryList> fetchAllCatlistByOrganisation(int organisationid) {

		return CategoryDao.fetchAllCatlistByOrganisation(organisationid);
				//.fetchAllCatlistByOrganisation(organisationid);
	}

	public List<XrayCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid)
	{
	return CategoryDao.fetchAllByOrganisationByCategoryType(cattypeid);	
 }
	
	

	@Override
	public List<XrayCategory> fetchTop50byOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return  CategoryDao.fetchTop50byOrganisation(organisationid);
	}

	@Override
	public XrayCategory getGlobalCategoryItemById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<XrayCategory> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<XrayCategory> fetchAllbyOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
