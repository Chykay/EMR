package org.calminfotech.hmo.boImpl;
//import java.util.Date;
import java.util.List;

import org.calminfotech.hmo.boInterface.HmoPackageBo;
import org.calminfotech.hmo.daoInterface.HmoPackageDao;

import org.calminfotech.hmo.models.HmoPackage;



//import org.calminfotech.admin.forms.DataTableForm
//import org.calminfotech.system.models.AllergyCategory;
//import org.calminfotech.user.utils.UserIdentity;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HmoPackageBoImpl implements HmoPackageBo {

	@Autowired
	private HmoPackageDao hmoPackageDao;

	@Override
	public List<HmoPackage> fetchAll(int organisationId) {
		// TODO Auto-generated method stub
		return hmoPackageDao.fetchAll( organisationId);
	}

	@Override
	public List<HmoPackage> fetchAllForuse(int organisationId) {
		// TODO Auto-generated method stub
		return hmoPackageDao.fetchAllForuse( organisationId);
	}
	
	
	@Override
	public HmoPackage getHmoPackageById(int id) {
		// TODO Auto-generated method stub
		return hmoPackageDao.getHmoPackageById(id);
	}

	
	@Override
	public void delete(HmoPackage hmoPackage) {
		// TODO Auto-generated method stub
		hmoPackageDao.delete(hmoPackage);
	}


	@Override
	public void save(HmoPackage hmoPackage) {
		// TODO Auto-generated method stub
		
		
		hmoPackageDao.save(hmoPackage);
		
	}


	@Override
	public void update(HmoPackage hmoPackageT) {
        hmoPackageDao.update(hmoPackageT);
	}
}
