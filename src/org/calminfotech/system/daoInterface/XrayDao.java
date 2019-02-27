package org.calminfotech.system.daoInterface;

import java.util.List;


import org.calminfotech.system.models.Xray;
//import org.calminfotech.system.models.XrayCategoryOuterRecursive;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.Xray;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface XrayDao {

public List<Xray> fetchAll(int id);
	

	public List<Xray> fetchAllByOgranisation(int organisationId);

	//public Xray getCategoryItemById(int itemId);

	//public List<XrayCategoryOuterRecursive> fetchAllTypesNew();

	public void save(Xray Xray);

	public void update(Xray Xray);

	public void delete(Xray Xray);

	
	public List<Xray> fetchTop50byOrganisation(int id);


	public Xray getXrayById(int id);

}
