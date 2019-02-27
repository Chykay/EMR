package org.calminfotech.system.boInterface;

import java.util.List;


import org.calminfotech.system.forms.SurgeryForm;

import org.calminfotech.system.models.Surgery;



public interface SurgeryBo {


	public List<Surgery> fetchAllByOrganisation();

	public Surgery getSurgeryById(int id);

	public void save(SurgeryForm iForm);

	public void delete(Surgery gblItem);

	public void update(SurgeryForm iForm);
		
	public List<Surgery> fetchAll();

	public void save(Surgery surgery);

	//List<SurgeryCategoryOuterRecursive> fetchAllTypesNew();

	public void update(Surgery surgery);

	//List<SurgeryCategoryOuterRecursive> fetchAllTypes();

	public List<Surgery> fetchAllByOgranisation(int organisationId);

	public List<Surgery> fetchAll(Integer id);

	public List<Surgery> fetchTop50byOrganisation(Integer id);


}
