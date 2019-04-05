package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.daoInterface.JournalEntryDao;
import org.calminfotech.ledger.models.JournalEntry;
import org.calminfotech.ledger.models.JournalHeader;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JournalEntryDaoImpl implements JournalEntryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;
	
	


	@SuppressWarnings("unchecked")
	@Override
	public List<JournalEntry> getJournalEntries() {
		System.out.println(this.userIdentity.getUser().getUserId() + " get journal entries dao");
		List<JournalEntry> entries = sessionFactory.getCurrentSession()
				.createQuery("FROM JournalEntry WHERE company_id = ? AND organisation_id = ? ")
				.setParameter(0, userIdentity.getOrganisation().getOrgCoy().getId())
				.setParameter(1, userIdentity.getOrganisation().getId())/*
				.setParameter(2, this.settingBo.fetchsettings("interbank-GLP", 2).getSettings_value())*/
				.list();

		return entries;
	}

	@Override
	public void saveHeader(JournalHeader journalHeader) {
		System.out.println(this.userIdentity.getUser().getUserId() + "  journal header");	
		this.sessionFactory.getCurrentSession().save(journalHeader);
	}




	@Override
	public void saveJournalEntry(JournalEntry journalEntry) {
		System.out.println(this.userIdentity.getUser().getUserId() + "  journal entry");	
		this.sessionFactory.getCurrentSession().save(journalEntry);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JournalHeader> getJournalHeaders() {

		System.out.println(this.userIdentity.getUser().getUserId() + " get journal headers");
		List<JournalHeader> entries = sessionFactory.getCurrentSession()
				.createQuery("FROM JournalHeader WHERE company_id = 2 AND organisation_id = 2 ")
				/*.setParameter(0, userIdentity.getOrganisation().getOrgCoy().getId())
				.setParameter(1, userIdentity.getOrganisation().getId())
				.setParameter(2, this.settingBo.fetchsettings("interbank-GLP", 2).getSettings_value())*/
				.list();

		return entries;
	}

	
}
