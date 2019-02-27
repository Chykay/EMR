package org.calminfotech.system.daoInterface;

import java.util.List;


import org.calminfotech.system.models.Diseases;
//import org.calminfotech.system.models.DiseasesCategoryOuterRecursive;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.Diseases;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface DiseasesDao {

public List<Diseases> fetchAll(int id);
	

	public List<Diseases> fetchAllByOgranisation(int organisationId);

	//public Diseases getCategoryItemById(int itemId);

	//public List<DiseasesCategoryOuterRecursive> fetchAllTypesNew();

	public void save(Diseases Diseases);

	public void update(Diseases Diseases);

	public void delete(Diseases Diseases);

	
	public List<Diseases> fetchTop50byOrganisation(int id);


	public Diseases getDiseasesById(int id);

}
