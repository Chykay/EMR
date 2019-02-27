package org.calminfotech.hmo.boInterface;

import java.util.List;

//import org.calminfotech.system.forms.DataTableForm;
import org.calminfotech.hmo.forms.HmoForm;
import org.calminfotech.hmo.models.Hmo;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.springframework.stereotype.Service;

public interface HmoBo {

	public List<Hmo> fetchAll(int organisationId);
	
	
	

	public Hmo getHmoById(int id);

	public Hmo save(HmoForm hmoForm);

	public void delete(Hmo hmo);

	public void update(HmoForm hmoForm);

	
	
}
