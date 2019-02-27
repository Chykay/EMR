package org.calminfotech.system.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.system.boInterface.XrayBo;
import org.calminfotech.system.daoInterface.XrayCategoryDao;

import org.calminfotech.system.daoInterface.XrayDao;
import org.calminfotech.system.forms.XrayForm;

import org.calminfotech.system.models.Xray;


//import org.calminfotech.system.models.XrayCategoryOuterRecursive;
//import org.calminfotech.system.models.XrayCategoryOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class XrayBoImpl implements XrayBo {
	@Autowired
	private XrayDao ItemDao;


	@Override
	public Xray getXrayById(int id)  {
	// TODO Auto-generated method stub
		return ItemDao.getXrayById(id);
	}

	@Override
	public void save(Xray Item) {
		// TODO Auto-generated method stub

		ItemDao.save(Item);
	}

	@Override
	public void update(Xray Item) {
		// TODO Auto-generated method stub

		ItemDao.update(Item);
	}

	@Override
	public void delete(Xray category) {
		// TODO Auto-generated method stub

		ItemDao.delete(category);

	}

	/*@Override
	public List<XrayCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public List<XrayCategoryOuterRecursive> fetchAllTypesNew() {
		// TODO Auto-generated method stub
		System.out.println("Inside the BO");
		return ItemDao.fetchAllTypesNew();

	}
*/
	@Override
	public List<Xray> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		return ItemDao.fetchAllByOgranisation(organisationId);
	}

	@Override
	public void save(XrayForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(XrayForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Xray> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Xray> fetchAll(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchAll(id);
	}

	@Override
	public List<Xray> fetchTop50byOrganisation(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchTop50byOrganisation(id);
	}

	@Override
	public List<Xray> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
