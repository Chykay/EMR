package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.DrugClassification;

public interface DrugClassificationDao {

	public List<DrugClassification> fetchAll();

	public DrugClassification getDrugClassificationById(int id);

	public void save(DrugClassification drugClassification);

	public void delete(DrugClassification drugClassification);

	public void update(DrugClassification drugClassification);

}
