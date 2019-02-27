package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.GlobalUnitofMeasureBo;
import org.calminfotech.system.daoInterface.GlobalUnitofMeasureDao;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalUnitofMeasureBoImpl implements GlobalUnitofMeasureBo {
	
	@Autowired
	private GlobalUnitofMeasureDao unitofMeasureDao;

	@Override
	public List<GlobalUnitofMeasure> fetchAll() {
		// TODO Auto-generated method stub
		return unitofMeasureDao.fetchAll();
	}

	@Override
	public void save(GlobalUnitofMeasure globalUnitofMeasure) {
		unitofMeasureDao.save(globalUnitofMeasure);
		
	}

	@Override
	public void delete(GlobalUnitofMeasure globalUnitofMeasure) {
		// TODO Auto-generated method stub
		unitofMeasureDao.delete(globalUnitofMeasure);
	}

	@Override
	public void update(GlobalUnitofMeasure globalUnitofMeasure) {
		// TODO Auto-generated method stub
		unitofMeasureDao.update(globalUnitofMeasure);
	}


	@Override
	public GlobalUnitofMeasure getUnitofMeasureById(int id) {
		// TODO Auto-generated method stub
		return  unitofMeasureDao.getUnitofMeasureById(id);
	}

	
}
