package org.calminfotech.system.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.system.boInterface.LabtestBo;
import org.calminfotech.system.daoInterface.LabtestCategoryDao;

import org.calminfotech.system.daoInterface.LabtestDao;
import org.calminfotech.system.forms.LabtestForm;

import org.calminfotech.system.models.Labtest;


//import org.calminfotech.system.models.LabtestCategoryOuterRecursive;
//import org.calminfotech.system.models.LabtestCategoryOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LabtestBoImpl implements LabtestBo {
	@Autowired
	private LabtestDao ItemDao;


	@Override
	public Labtest getLabtestById(int id)  {
	// TODO Auto-generated method stub
		return ItemDao.getLabtestById(id);
	}

	@Override
	public void save(Labtest Item) {
		// TODO Auto-generated method stub

		ItemDao.save(Item);
	}

	@Override
	public void update(Labtest Item) {
		// TODO Auto-generated method stub

		ItemDao.update(Item);
	}

	@Override
	public void delete(Labtest category) {
		// TODO Auto-generated method stub

		ItemDao.delete(category);

	}

	/*@Override
	public List<LabtestCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public List<LabtestCategoryOuterRecursive> fetchAllTypesNew() {
		// TODO Auto-generated method stub
		System.out.println("Inside the BO");
		return ItemDao.fetchAllTypesNew();

	}
*/
	@Override
	public List<Labtest> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		return ItemDao.fetchAllByOgranisation(organisationId);
	}

	@Override
	public void save(LabtestForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(LabtestForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Labtest> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Labtest> fetchAll(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchAll(id);
	}

	@Override
	public List<Labtest> fetchTop50byOrganisation(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchTop50byOrganisation(id);
	}

	@Override
	public List<Labtest> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
