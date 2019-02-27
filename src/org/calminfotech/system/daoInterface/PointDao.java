package org.calminfotech.system.daoInterface;

import java.util.List;
import org.calminfotech.system.models.Point;
import org.calminfotech.hrunit.models.HrunitCategory;


public interface PointDao {
	
	public List<Point> fetchAllPoint();	
	
	public List<Point> getPointByUnitId1(HrunitCategory unit);

	public Point getPointById(Integer id);
	
	public void save(Point point);
	
	public void update(Point point);
	
	public void delete(Point point);

	public List<Point> getPointByUnitId(HrunitCategory unit);

	public List<Point> getPointByUnitId(int id);
}
