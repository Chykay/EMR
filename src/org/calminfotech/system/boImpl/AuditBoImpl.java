package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.AuditBo;
import org.calminfotech.system.daoInterface.AuditDao;
import org.calminfotech.system.models.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
@Transactional
public class AuditBoImpl implements AuditBo {

	@Autowired
	AuditDao auditDao;

	public void save(Audit audit) {
		// TODO Auto-generated method stub
		auditDao.save(audit);
	}

	public void delete(Audit audit) {
		// TODO Auto-generated method stub
		auditDao.delete(audit);
	}

	public void update(Audit audit) {
		// TODO Auto-generated method stub
		auditDao.update(audit);
	}

	public Audit findByAuditId(int auditId) {
		// TODO Auto-generated method stub
		return auditDao.getAuditById(auditId);
	}

	public List<Audit> fetchAll() {
		return auditDao.fetchAll();
	}

}
