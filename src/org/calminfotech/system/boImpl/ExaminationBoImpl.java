package org.calminfotech.system.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.system.boInterface.ExaminationBo;
import org.calminfotech.system.daoInterface.ExaminationCategoryDao;

import org.calminfotech.system.daoInterface.ExaminationDao;
import org.calminfotech.system.forms.ExaminationForm;

import org.calminfotech.system.models.Examination;


//import org.calminfotech.system.models.ExaminationCategoryOuterRecursive;
//import org.calminfotech.system.models.ExaminationCategoryOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExaminationBoImpl implements ExaminationBo {
	@Autowired
	private ExaminationDao ItemDao;


	@Override
	public Examination getExaminationById(int id)  {
	// TODO Auto-generated method stub
		return ItemDao.getExaminationById(id);
	}

	@Override
	public void save(Examination Item) {
		// TODO Auto-generated method stub

		ItemDao.save(Item);
	}

	@Override
	public void update(Examination Item) {
		// TODO Auto-generated method stub

		ItemDao.update(Item);
	}

	@Override
	public void delete(Examination category) {
		// TODO Auto-generated method stub

		ItemDao.delete(category);

	}

	/*@Override
	public List<ExaminationCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public List<ExaminationCategoryOuterRecursive> fetchAllTypesNew() {
		// TODO Auto-generated method stub
		System.out.println("Inside the BO");
		return ItemDao.fetchAllTypesNew();

	}
*/
	@Override
	public List<Examination> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		return ItemDao.fetchAllByOgranisation(organisationId);
	}

	@Override
	public void save(ExaminationForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ExaminationForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Examination> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Examination> fetchAll(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchAll(id);
	}

	@Override
	public List<Examination> fetchTop50byOrganisation(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchTop50byOrganisation(id);
	}

	@Override
	public List<Examination> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
