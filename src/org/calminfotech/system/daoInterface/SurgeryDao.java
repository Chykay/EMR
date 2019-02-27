package org.calminfotech.system.daoInterface;

import java.util.List;


import org.calminfotech.system.models.Surgery;
//import org.calminfotech.system.models.SurgeryCategoryOuterRecursive;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.Surgery;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface SurgeryDao {

public List<Surgery> fetchAll(int id);
	

	public List<Surgery> fetchAllByOgranisation(int organisationId);

	//public Surgery getCategoryItemById(int itemId);

	//public List<SurgeryCategoryOuterRecursive> fetchAllTypesNew();

	public void save(Surgery Surgery);

	public void update(Surgery Surgery);

	public void delete(Surgery Surgery);

	
	public List<Surgery> fetchTop50byOrganisation(int id);


	public Surgery getSurgeryById(int id);

}
