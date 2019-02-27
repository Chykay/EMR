package org.calminfotech.system.daoInterface;

import java.util.List;


import org.calminfotech.system.models.Bed;
//import org.calminfotech.system.models.AdmissionRoomOuterRecursive;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.Bed;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface BedDao {

public List<Bed> fetchAll(int id);
public List<Bed> fetchAllByCategory(int catid);

	public List<Bed> fetchAllByOgranisation(int organisationId);

	//public Bed getCategoryItemById(int itemId);

	//public List<AdmissionRoomOuterRecursive> fetchAllTypesNew();

	public void save(Bed Bed);

	public void update(Bed Bed);

	public void delete(Bed Bed);

	
	public List<Bed> fetchTop50byOrganisation(int id);


	public Bed getBedById(int id);

}
