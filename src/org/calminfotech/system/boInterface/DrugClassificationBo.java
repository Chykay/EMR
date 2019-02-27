package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.DrugClassificationForm;
import org.calminfotech.system.models.DrugClassification;

public interface DrugClassificationBo {

	public List<DrugClassification> fetchAll();

	public DrugClassification getDrugClassificationById(int id);

	public DrugClassification save(DrugClassificationForm drugClassificationForm);

	public void delete(DrugClassification drugClassification);

	public void update(DrugClassificationForm drugClassificationForm);

}
