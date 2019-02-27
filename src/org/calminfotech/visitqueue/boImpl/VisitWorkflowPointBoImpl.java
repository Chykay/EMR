package org.calminfotech.visitqueue.boImpl;

import java.util.List;

import org.calminfotech.hrunit.boInterface.HrunitCategoryBo;
import org.calminfotech.hrunit.daoInterface.HrunitCategoryDao;
import org.calminfotech.hrunit.models.HrunitCategory;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.visitqueue.boInterface.VisitWorkflowPointBo;
import org.calminfotech.visitqueue.forms.VisitWorkflowPointForm;
import org.calminfotech.visitqueue.daoInterface.VisitWorkflowPointDao;
import org.calminfotech.visitqueue.models.VisitWorkflowPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VisitWorkflowPointBoImpl implements VisitWorkflowPointBo {

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private HrunitCategoryBo unitBo;

	@Autowired
	private HrunitCategoryDao unitDao;
	
	@Autowired
	private VisitWorkflowPointDao WorkflowPointDao;

	@Override
	public List<VisitWorkflowPoint> fetchAllByOrganisation(int id) {
		// TODO Auto-generated method stub

		return this.WorkflowPointDao.fetchAllByOrganisation(this.userIdentity.getOrganisation().getId());
	}
	
	
	
	

	@Override
	public VisitWorkflowPoint getWorkflowPointById(int id) {
		// TODO Auto-generated method stub
		return this.WorkflowPointDao.getWorkflowPointById(id);
	}

	@Override
	public VisitWorkflowPoint save(VisitWorkflowPointForm form) {
		// TODO Auto-generated method stub
		VisitWorkflowPoint point = new VisitWorkflowPoint();
		HrunitCategory unit = this.unitBo.getHrunitCategoryById(form.getUnitId());
		//point.setUnit(unit);
		point.setName(form.getPointName());
	
		
		point.setOrganisation(this.userIdentity.getOrganisation());
		point.setCreatedBy(this.userIdentity.getUsername()); 
		point.setActive(true);
		this.WorkflowPointDao.save(point);

		return point;
	}

	@Override
	public void delete(VisitWorkflowPoint workflowPoint) {
		// TODO Auto-generated method stub
		this.WorkflowPointDao.delete(workflowPoint);
	}

	@Override
	public void update(VisitWorkflowPointForm form) {
		// TODO Auto-generated method stub
		VisitWorkflowPoint point = this.WorkflowPointDao
				.getWorkflowPointById(form.getId());
		point.setName(form.getPointName());

		this.WorkflowPointDao.update(point);
	}

	@Override
	public VisitWorkflowPoint getPointByName(String string) {
		// TODO Auto-generated method stub
		return this.WorkflowPointDao.getPointByName(string);
	}

	@Override
	public List<VisitWorkflowPoint> fetchFontDeskPoint(int section) {
		// TODO Auto-generated method stub
		int frontDeskId = 8; 
		return this.WorkflowPointDao.fetchFontDeskPoint(frontDeskId, section);
	}

	@Override
	public List<VisitWorkflowPoint> getvWorkflowPointByUnitId(int unit_id) {
		HrunitCategory unit = unitBo.getHrunitCategoryById(unit_id);
		return WorkflowPointDao.getvWorkflowPointByUnitId(unit);
	}

	@Override
	public VisitWorkflowPoint getWorkflowStartPoint(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public List<VisitWorkflowPoint> fetchAll() {
		// TODO Auto-generated method stub
		return this.WorkflowPointDao.fetchAll();
	}


}
