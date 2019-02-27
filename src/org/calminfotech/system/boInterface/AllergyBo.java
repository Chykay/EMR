package org.calminfotech.system.boInterface;

import java.util.List;


import org.calminfotech.system.forms.AllergyForm;

import org.calminfotech.system.models.Allergy;



public interface AllergyBo {


	public List<Allergy> fetchAllByOrganisation();

	public Allergy getAllergyById(int id);

	public void save(AllergyForm iForm);

	public void delete(Allergy gblItem);

	public void update(AllergyForm iForm);
		
	public List<Allergy> fetchAll();

	public void save(Allergy allergy);

	//List<AllergyCategoryOuterRecursive> fetchAllTypesNew();

	public void update(Allergy allergy);

	//List<AllergyCategoryOuterRecursive> fetchAllTypes();

	public List<Allergy> fetchAllByOgranisation(int organisationId);

	public List<Allergy> fetchAll(Integer id);

	public List<Allergy> fetchTop50byOrganisation(Integer id);


}
