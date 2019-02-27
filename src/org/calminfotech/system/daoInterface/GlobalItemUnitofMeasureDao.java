package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.GlobalItemRanking;
import org.calminfotech.system.models.GlobalItemUnitofMeasure;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;

public interface GlobalItemUnitofMeasureDao {

	public List<GlobalItemUnitofMeasure> fetchAll();

	public List<GlobalItemUnitofMeasure> fetchAllByItemId(int id);

	public List<GlobalItemUnitofMeasureVw> fetchAllByItemIdvw(int id);

	public GlobalItemUnitofMeasure getGlobalItemUnitofMeasureById(int id);

	public GlobalItemUnitofMeasureVw getGlobalItemUnitofMeasureByIdvw(int id);

	public void save(GlobalItemUnitofMeasure globalItemUnitofMeasure);

	public void delete(GlobalItemUnitofMeasure globalItemUnitofMeasure);

	public void update(GlobalItemUnitofMeasure globalItemUnitofMeasure);

	public List<GlobalItemRanking> fetchAllRankingByItemId(int id);

	public GlobalItemRanking getGlobalItemRankingById(int id);

	public void save(GlobalItemRanking globalItemRanking);

	public void delete(GlobalItemRanking globalItemRanking);

	public void update(GlobalItemRanking globalItemRanking);

	List<GlobalItemUnitofMeasure> fetchAllByItemIdprescribe(int id);

	List<GlobalItemUnitofMeasureVw> fetchAllByItemIdvwprescribe(int id);

	Boolean IsExist(int itemid, int uomid);
}
