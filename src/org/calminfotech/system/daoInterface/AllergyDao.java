package org.calminfotech.system.daoInterface;

import java.util.List;


import org.calminfotech.system.models.Allergy;
//import org.calminfotech.system.models.AllergyCategoryOuterRecursive;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.Allergy;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface AllergyDao {

public List<Allergy> fetchAll(int id);
	

	public List<Allergy> fetchAllByOgranisation(int organisationId);

	//public Allergy getCategoryItemById(int itemId);

	//public List<AllergyCategoryOuterRecursive> fetchAllTypesNew();

	public void save(Allergy Allergy);

	public void update(Allergy Allergy);

	public void delete(Allergy Allergy);

	
	public List<Allergy> fetchTop50byOrganisation(int id);


	public Allergy getAllergyById(int id);

}
