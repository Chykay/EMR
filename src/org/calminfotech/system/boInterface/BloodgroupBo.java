package org.calminfotech.system.boInterface;

import java.util.List;


import org.calminfotech.system.forms.DiseasesForm;

import org.calminfotech.system.models.Diseases;



public interface BloodgroupBo {


	public List<Diseases> fetchAllByOrganisation();

	public Diseases getDiseasesById(int id);

	public void save(DiseasesForm iForm);

	public void delete(Diseases gblItem);

	public void update(DiseasesForm iForm);
		
	public List<Diseases> fetchAll();

	public void save(Diseases diseases);

	//List<DiseasesCategoryOuterRecursive> fetchAllTypesNew();

	public void update(Diseases diseases);

	//List<DiseasesCategoryOuterRecursive> fetchAllTypes();

	public List<Diseases> fetchAllByOgranisation(int organisationId);

	public List<Diseases> fetchAll(Integer id);

	public List<Diseases> fetchTop50byOrganisation(Integer id);


}
