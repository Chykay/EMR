package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Audit;


public interface AuditDao {

	List<Audit> fetchAll();

	void save(Audit audit);

	void update(Audit audit);

	void delete(Audit audit);

	Audit getAuditById(int auditId);
}
