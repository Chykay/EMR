package org.calminfotech.system.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.system.boInterface.AllergyBo;
import org.calminfotech.system.daoInterface.AllergyCategoryDao;

import org.calminfotech.system.daoInterface.AllergyDao;
import org.calminfotech.system.forms.AllergyForm;

import org.calminfotech.system.models.Allergy;


//import org.calminfotech.system.models.AllergyCategoryOuterRecursive;
//import org.calminfotech.system.models.AllergyCategoryOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AllergyBoImpl implements AllergyBo {
	@Autowired
	private AllergyDao ItemDao;


	@Override
	public Allergy getAllergyById(int id)  {
	// TODO Auto-generated method stub
		return ItemDao.getAllergyById(id);
	}

	@Override
	public void save(Allergy Item) {
		// TODO Auto-generated method stub

		ItemDao.save(Item);
	}

	@Override
	public void update(Allergy Item) {
		// TODO Auto-generated method stub

		ItemDao.update(Item);
	}

	@Override
	public void delete(Allergy category) {
		// TODO Auto-generated method stub

		ItemDao.delete(category);

	}

	/*@Override
	public List<AllergyCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public List<AllergyCategoryOuterRecursive> fetchAllTypesNew() {
		// TODO Auto-generated method stub
		System.out.println("Inside the BO");
		return ItemDao.fetchAllTypesNew();

	}
*/
	@Override
	public List<Allergy> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		return ItemDao.fetchAllByOgranisation(organisationId);
	}

	@Override
	public void save(AllergyForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AllergyForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Allergy> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Allergy> fetchAll(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchAll(id);
	}

	@Override
	public List<Allergy> fetchTop50byOrganisation(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchTop50byOrganisation(id);
	}

	@Override
	public List<Allergy> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
