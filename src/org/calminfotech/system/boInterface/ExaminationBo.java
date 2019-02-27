package org.calminfotech.system.boInterface;

import java.util.List;


import org.calminfotech.system.forms.ExaminationForm;

import org.calminfotech.system.models.Examination;



public interface ExaminationBo {


	public List<Examination> fetchAllByOrganisation();

	public Examination getExaminationById(int id);

	public void save(ExaminationForm iForm);

	public void delete(Examination gblItem);

	public void update(ExaminationForm iForm);
		
	public List<Examination> fetchAll();

	public void save(Examination examination);

	//List<ExaminationCategoryOuterRecursive> fetchAllTypesNew();

	public void update(Examination examination);

	//List<ExaminationCategoryOuterRecursive> fetchAllTypes();

	public List<Examination> fetchAllByOgranisation(int organisationId);

	public List<Examination> fetchAll(Integer id);

	public List<Examination> fetchTop50byOrganisation(Integer id);


}
