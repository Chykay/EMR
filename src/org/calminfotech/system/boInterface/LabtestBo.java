package org.calminfotech.system.boInterface;

import java.util.List;


import org.calminfotech.system.forms.LabtestForm;

import org.calminfotech.system.models.Labtest;



public interface LabtestBo {


	public List<Labtest> fetchAllByOrganisation();

	public Labtest getLabtestById(int id);

	public void save(LabtestForm iForm);

	public void delete(Labtest gblItem);

	public void update(LabtestForm iForm);
		
	public List<Labtest> fetchAll();

	public void save(Labtest labtest);

	//List<LabtestCategoryOuterRecursive> fetchAllTypesNew();

	public void update(Labtest labtest);

	//List<LabtestCategoryOuterRecursive> fetchAllTypes();

	public List<Labtest> fetchAllByOgranisation(int organisationId);

	public List<Labtest> fetchAll(Integer id);

	public List<Labtest> fetchTop50byOrganisation(Integer id);


}
