package org.calminfotech.system.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.system.boInterface.SurgeryBo;
import org.calminfotech.system.daoInterface.SurgeryCategoryDao;

import org.calminfotech.system.daoInterface.SurgeryDao;
import org.calminfotech.system.forms.SurgeryForm;

import org.calminfotech.system.models.Surgery;


//import org.calminfotech.system.models.SurgeryCategoryOuterRecursive;
//import org.calminfotech.system.models.SurgeryCategoryOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SurgeryBoImpl implements SurgeryBo {
	@Autowired
	private SurgeryDao ItemDao;


	@Override
	public Surgery getSurgeryById(int id)  {
	// TODO Auto-generated method stub
		return ItemDao.getSurgeryById(id);
	}

	@Override
	public void save(Surgery Item) {
		// TODO Auto-generated method stub

		ItemDao.save(Item);
	}

	@Override
	public void update(Surgery Item) {
		// TODO Auto-generated method stub

		ItemDao.update(Item);
	}

	@Override
	public void delete(Surgery category) {
		// TODO Auto-generated method stub

		ItemDao.delete(category);

	}

	/*@Override
	public List<SurgeryCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public List<SurgeryCategoryOuterRecursive> fetchAllTypesNew() {
		// TODO Auto-generated method stub
		System.out.println("Inside the BO");
		return ItemDao.fetchAllTypesNew();

	}
*/
	@Override
	public List<Surgery> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		return ItemDao.fetchAllByOgranisation(organisationId);
	}

	@Override
	public void save(SurgeryForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SurgeryForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Surgery> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Surgery> fetchAll(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchAll(id);
	}

	@Override
	public List<Surgery> fetchTop50byOrganisation(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchTop50byOrganisation(id);
	}

	@Override
	public List<Surgery> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
