package org.calminfotech.system.daoInterface;
import java.util.List;

//import org.calminfotech.inventory.models.GlobalItemUnitofMeasure;
import org.calminfotech.system.models.GlobalUnitofMeasure;

public interface GlobalUnitofMeasureDao {

	List<GlobalUnitofMeasure> fetchAll();
	
	GlobalUnitofMeasure getUnitofMeasureById(int id);
	
	void save(GlobalUnitofMeasure unitofMeasure);
	
	void delete(GlobalUnitofMeasure unitofMeasure);
	
	void update(GlobalUnitofMeasure unitofMeasure);

}
