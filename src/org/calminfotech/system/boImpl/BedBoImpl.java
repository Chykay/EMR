package org.calminfotech.system.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.system.boInterface.BedBo;
import org.calminfotech.system.daoInterface.BedDao;
import org.calminfotech.system.forms.BedForm;

import org.calminfotech.system.models.Bed;


//import org.calminfotech.system.models.AdmissionRoomOuterRecursive;
//import org.calminfotech.system.models.AdmissionRoomOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BedBoImpl implements BedBo {
	@Autowired
	private BedDao ItemDao;


	@Override
	public Bed getBedById(int id)  {
	// TODO Auto-generated method stub
		return ItemDao.getBedById(id);
	}

	@Override
	public void save(Bed Item) {
		// TODO Auto-generated method stub

		ItemDao.save(Item);
	}

	@Override
	public void update(Bed Item) {
		// TODO Auto-generated method stub

		ItemDao.update(Item);
	}

	@Override
	public void delete(Bed category) {
		// TODO Auto-generated method stub

		ItemDao.delete(category);

	}

	/*@Override
	public List<AdmissionRoomOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public List<AdmissionRoomOuterRecursive> fetchAllTypesNew() {
		// TODO Auto-generated method stub
		System.out.println("Inside the BO");
		return ItemDao.fetchAllTypesNew();

	}
*/
	@Override
	public List<Bed> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		return ItemDao.fetchAllByOgranisation(organisationId);
	}

	@Override
	public void save(BedForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BedForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Bed> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bed> fetchAll(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchAll(id);
	}


	
	@Override
	public List<Bed> fetchAllByCategory(Integer catid) {
		// TODO Auto-generated method stub
		return ItemDao.fetchAllByCategory(catid);
	}

	
	
	@Override
	public List<Bed> fetchTop50byOrganisation(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchTop50byOrganisation(id);
	}

	@Override
	public List<Bed> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
