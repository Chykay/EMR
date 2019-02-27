package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.GlobalItemPoint;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;

public interface GlobalItemPointDao {

	public List<GlobalItemPoint> fetchAll();
	
	public GlobalItemPoint getGlobalItemPointById(int id);
	
	public void save(GlobalItemPoint point);
	
	public void delete(GlobalItemPoint point);
	
	public void update(GlobalItemPoint point);
	
	public GlobalItemPoint getByPointAndItem(Integer itemId, VisitWorkflowPoint point); 
}
