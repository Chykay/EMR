package org.calminfotech.hrunit.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.hrunit.boInterface.HrunitBo;
import org.calminfotech.hrunit.daoInterface.HrunitCategoryDao;
import org.calminfotech.hrunit.daoInterface.HrunitDao;
import org.calminfotech.hrunit.forms.HrunitForm;
import org.calminfotech.hrunit.models.Hrunit;




//import org.calminfotech.system.models.HrunitCategoryOuterRecursive;
//import org.calminfotech.system.models.HrunitCategoryOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HrunitBoImpl implements HrunitBo {
	@Autowired
	private HrunitDao ItemDao;


	@Override
	public Hrunit getHrunitById(int id)  {
	// TODO Auto-generated method stub
		return ItemDao.getHrunitById(id);
	}

	@Override
	public void save(Hrunit Item) {
		// TODO Auto-generated method stub

		ItemDao.save(Item);
	}

	@Override
	public void update(Hrunit Item) {
		// TODO Auto-generated method stub

		ItemDao.update(Item);
	}

	@Override
	public void delete(Hrunit category) {
		// TODO Auto-generated method stub

		ItemDao.delete(category);

	}

	/*@Override
	public List<HrunitCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public List<HrunitCategoryOuterRecursive> fetchAllTypesNew() {
		// TODO Auto-generated method stub
		System.out.println("Inside the BO");
		return ItemDao.fetchAllTypesNew();

	}
*/
	@Override
	public List<Hrunit> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		return ItemDao.fetchAllByOgranisation(organisationId);
	}

	@Override
	public void save(HrunitForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(HrunitForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Hrunit> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hrunit> fetchAll(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchAll(id);
	}

	@Override
	public List<Hrunit> fetchTop50byOrganisation(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchTop50byOrganisation(id);
	}

	@Override
	public List<Hrunit> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
