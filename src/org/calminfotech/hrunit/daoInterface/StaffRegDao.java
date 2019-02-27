package org.calminfotech.hrunit.daoInterface;

import java.util.List;

import org.calminfotech.hrunit.models.StaffRegistration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface StaffRegDao {
	
public StaffRegistration getStaffById(int id);

public StaffRegistration getStaffByUsernameId(String usernme);
	
	public List<StaffRegistration> fetchAllByOrganisation(int organisationId);
	
	public List<StaffRegistration> fetchAllByOrganisationNotIn(int organisationId);
	
	public List<StaffRegistration> fetchTop50byOrganisation (int organisationId );
	
	
	public List<StaffRegistration> fetchStaffByUnitId(int unitId);

	public void save(StaffRegistration staffRegistration);

	public void delete(StaffRegistration staffRegistration);

	public void update(StaffRegistration staffRegistration);

}
