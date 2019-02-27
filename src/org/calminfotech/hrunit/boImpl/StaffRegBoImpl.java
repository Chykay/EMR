package org.calminfotech.hrunit.boImpl;

import java.util.Date;
import java.util.List;
import org.calminfotech.hrunit.boInterface.StaffRegBoInterface;
import org.calminfotech.hrunit.daoInterface.StaffRegDao;
import org.calminfotech.hrunit.forms.StaffRegForm;
import org.calminfotech.hrunit.models.Hrunit;
import org.calminfotech.hrunit.models.StaffRegistration;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.AutoGenStaffCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class StaffRegBoImpl implements StaffRegBoInterface {
	
	@Autowired
	private StaffRegDao staffRegDao;

	@Autowired
	UserIdentity userIdentity;
	
	
	@Override
	public List<StaffRegistration> fetchTop50byOrganisation(int id) {
		// TODO Auto-generated method stub
		return staffRegDao.fetchTop50byOrganisation(id);
	}

	@Override
	public List<StaffRegistration> fetchAllByOrganisation(int organisationId) {
		return this.staffRegDao.fetchAllByOrganisation(organisationId);
	}

	
	@Override
	public List<StaffRegistration> fetchAllByOrganisationNotIn(int organisationId) {
		return this.staffRegDao.fetchAllByOrganisationNotIn(organisationId);
	}


	@Override
	public void delete(StaffRegistration staffRegistration) {
		this.staffRegDao.delete(staffRegistration);
		
	}

	@Override
	public void update(StaffRegistration staffRegistration) {
		
		staffRegDao.update(staffRegistration);
	}

	@Override
	public StaffRegistration getStaffById(int id) {
		return this.staffRegDao.getStaffById(id);	}

	@Override
	public List<StaffRegistration> fetchStaffByUnitId(int unitId) {
		return this.staffRegDao.fetchStaffByUnitId(unitId);
	}



	@Override
	public void save(StaffRegistration staffReg) {
		// TODO Auto-generated method stub
		 this.staffRegDao.save(staffReg);
	}
	@Override
	public StaffRegistration getStaffByUsernameId(String username) {
		return this.staffRegDao.getStaffByUsernameId(username);	}


	
	


}
