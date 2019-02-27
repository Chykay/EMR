package org.calminfotech.system.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.system.boInterface.ExaminationResultSetupBo;
import org.calminfotech.system.daoInterface.ExaminationCategoryDao;

import org.calminfotech.system.daoInterface.ExaminationResultSetupDao;
import org.calminfotech.system.forms.ExaminationResultSetupForm;

import org.calminfotech.system.models.ExaminationResultSetup;


//import org.calminfotech.system.models.ExaminationResultSetupCategoryOuterRecursive;
//import org.calminfotech.system.models.ExaminationResultSetupCategoryOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExaminationResultSetupBoImpl implements ExaminationResultSetupBo {

	@Autowired
	private ExaminationResultSetupDao ItemDao;


	@Override
	public ExaminationResultSetup getExaminationResultSetupById(int id)  {
	// TODO Auto-generated method stub
		return ItemDao.getExaminationResultSetupById(id);
	}

	@Override
	public void save(ExaminationResultSetup Item) {
		// TODO Auto-generated method stub

		ItemDao.save(Item);
	}

	@Override
	public void update(ExaminationResultSetup Item) {
		// TODO Auto-generated method stub

		ItemDao.update(Item);
	}

	@Override
	public void delete(ExaminationResultSetup category) {
		// TODO Auto-generated method stub

		ItemDao.delete(category);

	}

	/*@Override
	public List<ExaminationResultSetupCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public List<ExaminationResultSetupCategoryOuterRecursive> fetchAllTypesNew() {
		// TODO Auto-generated method stub
		System.out.println("Inside the BO");
		return ItemDao.fetchAllTypesNew();

	}
*/
	@Override
	public List<ExaminationResultSetup> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		return ItemDao.fetchAllByOgranisation(organisationId);
	}

	@Override
	public void save(ExaminationResultSetupForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ExaminationResultSetupForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ExaminationResultSetup> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExaminationResultSetup> fetchAll(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchAll(id);
	}

	@Override
	public List<ExaminationResultSetup> fetchTop50byOrganisation(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchTop50byOrganisation(id);
	}

	@Override
	public List<ExaminationResultSetup> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
