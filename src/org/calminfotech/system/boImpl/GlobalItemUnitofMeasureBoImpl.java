package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.GlobalItemUnitofMeasureBo;
import org.calminfotech.system.daoInterface.GlobalItemUnitofMeasureDao;
import org.calminfotech.system.models.GlobalItemRanking;
import org.calminfotech.system.models.GlobalItemUnitofMeasure;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalItemUnitofMeasureBoImpl implements GlobalItemUnitofMeasureBo {

	@Autowired
	private GlobalItemUnitofMeasureDao itemunitofMeasureDao;

	@Override
	public List<GlobalItemUnitofMeasure> fetchAll() {
		// TODO Auto-generated method stub
		return itemunitofMeasureDao.fetchAll();
	}

	@Override
	public List<GlobalItemUnitofMeasure> fetchAllByItemId(int id) {
		// TODO Auto-generated method stub
		return itemunitofMeasureDao.fetchAllByItemId(id);
	}

	@Override
	public List<GlobalItemUnitofMeasureVw> fetchAllByItemIdvw(int id) {
		// TODO Auto-generated method stub
		return itemunitofMeasureDao.fetchAllByItemIdvw(id);
	}

	@Override
	public List<GlobalItemUnitofMeasure> fetchAllByItemIdprescribe(int id) {
		// TODO Auto-generated method stub
		return itemunitofMeasureDao.fetchAllByItemIdprescribe(id);
	}

	@Override
	public List<GlobalItemUnitofMeasureVw> fetchAllByItemIdvwprescribe(int id) {
		// TODO Auto-generated method stub
		return itemunitofMeasureDao.fetchAllByItemIdvwprescribe(id);
	}

	@Override
	public GlobalItemUnitofMeasure getGlobalItemUnitofMeasureById(int id) {
		// TODO Auto-generated method stub
		return itemunitofMeasureDao.getGlobalItemUnitofMeasureById(id);
	}

	@Override
	public GlobalItemUnitofMeasureVw getGlobalItemUnitofMeasureByIdvw(int id) {
		// TODO Auto-generated method stub
		return itemunitofMeasureDao.getGlobalItemUnitofMeasureByIdvw(id);
	}

	@Override
	public void save(GlobalItemUnitofMeasure globalItemUnitofMeasure) {
		itemunitofMeasureDao.save(globalItemUnitofMeasure);

	}

	@Override
	public void delete(GlobalItemUnitofMeasure globalItemUnitofMeasure) {
		// TODO Auto-generated method stub
		itemunitofMeasureDao.delete(globalItemUnitofMeasure);
	}

	@Override
	public void update(GlobalItemUnitofMeasure globalItemUnitofMeasure) {
		// TODO Auto-generated method stub
		itemunitofMeasureDao.update(globalItemUnitofMeasure);
	}

	@Override
	public List<GlobalItemRanking> fetchAllRankingByItemId(int id) {
		// TODO Auto-generated method stub
		return itemunitofMeasureDao.fetchAllRankingByItemId(id);
	}

	@Override
	public GlobalItemRanking getGlobalItemRankingById(int id) {
		// TODO Auto-generated method stub
		return itemunitofMeasureDao.getGlobalItemRankingById(id);
	}

	@Override
	public void save(GlobalItemRanking globalItemRanking) {
		// TODO Auto-generated method stub
		itemunitofMeasureDao.save(globalItemRanking);
	}

	@Override
	public void delete(GlobalItemRanking globalItemRanking) {
		// TODO Auto-generated method stub
		itemunitofMeasureDao.delete(globalItemRanking);
	}

	@Override
	public void update(GlobalItemRanking globalItemRanking) {
		// TODO Auto-generated method stub
		itemunitofMeasureDao.update(globalItemRanking);
	}

	@Override
	public Boolean IsExist(int itemid, int uomid) {
		return itemunitofMeasureDao.IsExist(itemid, uomid);
	}
}
