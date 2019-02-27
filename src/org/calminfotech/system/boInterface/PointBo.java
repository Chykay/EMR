package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.Point;
import org.calminfotech.hrunit.models.HrunitCategory;


public interface PointBo {
	
	public List<Point> fetchAllPoint();	
	
	public List<Point> getPointByUnitId(HrunitCategory unit);

	public Point getPointById(Integer id);
	
	public void save(Point point);
	
	public void update(Point point);
	
	public void delete(Point point);

	


}
