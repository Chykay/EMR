package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.hrunit.boInterface.HrunitCategoryBo;

import org.calminfotech.system.boInterface.PointBo;

import org.calminfotech.system.daoInterface.PointDao;
import org.calminfotech.system.models.Point;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class PointBoImpl implements PointBo {

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private PointDao pointDao;
	


	
	@Override
	public List<Point> fetchAllPoint() {
		return this.pointDao.fetchAllPoint();
	}


	@Override
	public Point getPointById(Integer id) {
		return this.pointDao.getPointById(id);
	}

	@Override
	public void save(Point point) {
		pointDao.save(point);
	}

	@Override
	public void update(Point point) {
		pointDao.update(point);
	}

	@Override
	public void delete(Point point) {
		pointDao.delete(point);	
	}

	


	@Override
	public List<Point> getPointByUnitId(HrunitCategory unit) {
		// TODO Auto-generated method stub
		return null;
	}


}
