package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.VwGlobalItemUnitofMeasureBo;
import org.calminfotech.system.daoInterface.VwGlobalItemUnitofMeasureDao;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VwGlobalItemUnitofMeasureBoImpl implements
		VwGlobalItemUnitofMeasureBo {
	
	@Autowired
	private VwGlobalItemUnitofMeasureDao vwGlobalItemUnitofMeasureDao;

	@Override
	public List<GlobalItemUnitofMeasureVw> fetchAll() {
		// TODO Auto-generated method stub
		return vwGlobalItemUnitofMeasureDao.fetchAll();
	}

	@Override
	public GlobalItemUnitofMeasureVw getVwGlobalItemUnitofMeasureById(Integer id) {
		// TODO Auto-generated method stub
	//	return vwGlobalItemUnitofMeasureDao.getVwGlobalItemUnitofMeasureById(id);
	return null;
	}

	@Override
	public List<GlobalItemUnitofMeasureVw> fetchAllById(int id) {
		// TODO Auto-generated method stub
		//return vwGlobalItemUnitofMeasureDao.fetchAllById(id);
		return null;
	}

}
