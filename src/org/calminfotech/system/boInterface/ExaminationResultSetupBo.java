package org.calminfotech.system.boInterface;

import java.util.List;


import org.calminfotech.system.forms.ExaminationResultSetupForm;

import org.calminfotech.system.models.ExaminationResultSetup;



public interface ExaminationResultSetupBo {


	public List<ExaminationResultSetup> fetchAllByOrganisation();

	public ExaminationResultSetup getExaminationResultSetupById(int id);

	public void save(ExaminationResultSetupForm iForm);

	public void delete(ExaminationResultSetup gblItem);

	public void update(ExaminationResultSetupForm iForm);
		
	public List<ExaminationResultSetup> fetchAll();

	public void save(ExaminationResultSetup examinationResultSetup);

	//List<ExaminationResultSetupCategoryOuterRecursive> fetchAllTypesNew();

	public void update(ExaminationResultSetup examinationResultSetup);

	//List<ExaminationResultSetupCategoryOuterRecursive> fetchAllTypes();

	public List<ExaminationResultSetup> fetchAllByOgranisation(int organisationId);

	public List<ExaminationResultSetup> fetchAll(Integer id);

	public List<ExaminationResultSetup> fetchTop50byOrganisation(Integer id);


}
