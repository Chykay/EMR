package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.DrugClassificationBo;
import org.calminfotech.system.daoInterface.DrugClassificationDao;
import org.calminfotech.system.forms.DrugClassificationForm;
import org.calminfotech.system.models.DrugClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DrugClassificationBoImpl implements DrugClassificationBo {

	@Autowired
	private DrugClassificationDao drugClassificationDao;

	@Override
	public List<DrugClassification> fetchAll() {
		// TODO Auto-generated method stub
		return drugClassificationDao.fetchAll();
	}

	@Override
	public DrugClassification getDrugClassificationById(int id) {
		// TODO Auto-generated method stub
		return this.drugClassificationDao.getDrugClassificationById(id);
	}

	@Override
	public DrugClassification save(DrugClassificationForm drugClassificationForm) {
		// TODO Auto-generated method stub

		DrugClassification drugClassification = new DrugClassification();
		drugClassification.setName(drugClassificationForm.getName());
		drugClassification.setDescription(drugClassificationForm
				.getDescription());
		this.drugClassificationDao.save(drugClassification);

		return drugClassification;
	}

	@Override
	public void delete(DrugClassification drugClassification) {
		// TODO Auto-generated method stub
		this.drugClassificationDao.delete(drugClassification);
	}

	@Override
	public void update(DrugClassificationForm drugClassificationForm) {
		// TODO Auto-generated method stub
		DrugClassification drugClassification = this.drugClassificationDao
				.getDrugClassificationById(drugClassificationForm.getId());
		
		drugClassification.setName(drugClassificationForm.getName());
		drugClassification.setDescription(drugClassificationForm
				.getDescription());

		this.drugClassificationDao.update(drugClassification);
	}

}
