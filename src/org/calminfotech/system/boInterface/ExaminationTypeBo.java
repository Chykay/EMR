package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.ExaminationTypeForm;
import org.calminfotech.system.models.ExaminationType;

public interface ExaminationTypeBo {
	public ExaminationType getExaminationTypeById(int id);
	
	public void save(ExaminationType ExaminationType);
	
	public void saveForm(ExaminationTypeForm dForm);

	public void delete(ExaminationType ExaminationType);

	public void update(ExaminationType ExaminationType);
		
	public List<ExaminationType> fetchAll();

	public List<ExaminationType> fetchAllByOrganisation();
}
