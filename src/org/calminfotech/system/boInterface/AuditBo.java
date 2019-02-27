package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.Audit;


public interface AuditBo {

	void save(Audit audit);

	void delete(Audit audit);

	void update(Audit audit);

	Audit findByAuditId(int auditId);

	List<Audit> fetchAll();

}
