package org.calminfotech.hrunit.boInterface;

import java.util.List;

//import org.calminfotech.system.models.Allergy;
import org.calminfotech.hrunit.forms.StaffRegForm;
import org.calminfotech.hrunit.models.StaffRegistration;

public interface StaffRegBoInterface {
	
	public List<StaffRegistration> fetchAllByOrganisation(int organisationId);
	
	public List<StaffRegistration> fetchAllByOrganisationNotIn(int organisationId);
	
	
	public StaffRegistration getStaffById(int id);

	public StaffRegistration getStaffByUsernameId(String username);
	
	
	public void save(StaffRegistration staffReg);

	public void delete(StaffRegistration staffRegistration);
	
	public List<StaffRegistration> fetchStaffByUnitId(int unitId);

	public List<StaffRegistration> fetchTop50byOrganisation (int organisationId );
	
	

	public void update(StaffRegistration staffRegistration);
	

}
