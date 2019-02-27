package org.calminfotech.system.boInterface;

import java.util.List;


import org.calminfotech.system.forms.BedForm;

import org.calminfotech.system.models.Bed;



public interface BedBo {


	public List<Bed> fetchAllByOrganisation();

	public Bed getBedById(int id);

	public void save(BedForm iForm);

	public void delete(Bed gblItem);

	public void update(BedForm iForm);
		
	public List<Bed> fetchAll();

	public void save(Bed bed);

	//List<AdmissionRoomOuterRecursive> fetchAllTypesNew();

	public void update(Bed bed);

	//List<AdmissionRoomOuterRecursive> fetchAllTypes();

	public List<Bed> fetchAllByOgranisation(int organisationId);

	public List<Bed> fetchAll(Integer id);
	
	public List<Bed> fetchAllByCategory(Integer catid);

	public List<Bed> fetchTop50byOrganisation(Integer id);


}
