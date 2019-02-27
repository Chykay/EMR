package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.GlobalItemPointBo;
import org.calminfotech.visitqueue.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.daoInterface.GlobalItemPointDao;
import org.calminfotech.system.models.GlobalItemPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalItemPointBoImpl implements GlobalItemPointBo {
	
	@Autowired
	private GlobalItemPointDao globalItemPointDao;
	
	@Autowired
	private VisitWorkflowPointBo pointBo;

	@Override
	public List<GlobalItemPoint> fetchAll() {
		// TODO Auto-generated method stub
		return this.globalItemPointDao.fetchAll();
	}

	@Override
	public GlobalItemPoint getGlobalItemPointById(int id) {
		// TODO Auto-generated method stub
		return this.globalItemPointDao.getGlobalItemPointById(id);
	}

	@Override
	public void save(GlobalItemPoint point) {
		// TODO Auto-generated method stub
		this.globalItemPointDao.save(point);
	}

	@Override
	public void delete(GlobalItemPoint point) {
		// TODO Auto-generated method stub
		this.globalItemPointDao.delete(point);
	}

	@Override
	public void update(GlobalItemPoint point) {
		// TODO Auto-generated method stub
		this.globalItemPointDao.update(point);
	}

	@Override
	public GlobalItemPoint getByPointAndItem(Integer itemId, Integer point) {
		// TODO Auto-generated method stub
		//return globalItemPointDao.getByPointAndItem(itemId, pointBo.getWorkflowPointById(point));
	return null;
	}

}
