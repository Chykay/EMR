package org.calminfotech.system.daoInterface;

import java.util.List;


import org.calminfotech.system.models.Labtest;
//import org.calminfotech.system.models.LabtestCategoryOuterRecursive;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.Labtest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface LabtestDao {

public List<Labtest> fetchAll(int id);
	

	public List<Labtest> fetchAllByOgranisation(int organisationId);

	//public Labtest getCategoryItemById(int itemId);

	//public List<LabtestCategoryOuterRecursive> fetchAllTypesNew();

	public void save(Labtest Labtest);

	public void update(Labtest Labtest);

	public void delete(Labtest Labtest);

	
	public List<Labtest> fetchTop50byOrganisation(int id);


	public Labtest getLabtestById(int id);

}
