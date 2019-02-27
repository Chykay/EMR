package org.calminfotech.hrunit.daoInterface;

import java.util.List;


import org.calminfotech.hrunit.models.Hrunit;
//import org.calminfotech.system.models.HrunitCategoryOuterRecursive;
import org.calminfotech.system.models.Organisation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface HrunitDao {

public List<Hrunit> fetchAll(int id);
	

	public List<Hrunit> fetchAllByOgranisation(int organisationId);

	//public Hrunit getCategoryItemById(int itemId);

	//public List<HrunitCategoryOuterRecursive> fetchAllTypesNew();

	public void save(Hrunit Hrunit);

	public void update(Hrunit Hrunit);

	public void delete(Hrunit Hrunit);

	
	public List<Hrunit> fetchTop50byOrganisation(int id);


	public Hrunit getHrunitById(int id);

}
