package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.daoInterface.JournalDao;
import org.calminfotech.ledger.models.JournalEntry;
import org.calminfotech.ledger.models.JournalHeader;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JournalDaoImpl implements JournalDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;
	
	


	@SuppressWarnings("unchecked")
	@Override
	public List<JournalEntry> getJournalEntries() {
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
		this.sessionFactory.getCurrentSession().saveOrUpdate(journalHeader);
	}
	
	@Override
	public void updateHeader(JournalHeader journalHeader) {
		System.out.println("updating journal header");	
		this.sessionFactory.getCurrentSession().update(journalHeader);
		this.sessionFactory.getCurrentSession().flush();
	}




	@Override
	public void saveJournalEntry(JournalEntry journalEntry) {
		System.out.println(this.userIdentity.getUser().getUserId() + "  journal entry");	
		this.sessionFactory.getCurrentSession().save(journalEntry);
		this.sessionFactory.getCurrentSession().flush();
	}

	@SuppressWarnings("unchecked")
	public List<JournalHeader> fetchJournalHeaders() {

		System.out.println(this.userIdentity.getUser().getUserId() + " get journal headers");
		List<JournalHeader> entries = sessionFactory.getCurrentSession()
				.createQuery("FROM JournalHeader WHERE company_id = 2 AND organisation_id = 2 ")
				/*.setParameter(0, userIdentity.getOrganisation().getOrgCoy().getId())
				.setParameter(1, userIdentity.getOrganisation().getId())
				.setParameter(2, this.settingBo.fetchsettings("interbank-GLP", 2).getSettings_value())*/
				.list();

		return entries;
	}

	@SuppressWarnings("unchecked")
	public JournalHeader getJournalHeader(String journalID) {
		List<JournalHeader> list = sessionFactory.getCurrentSession()
				.createQuery("FROM JournalHeader WHERE company_id = ? AND organisation_id = ? AND journal_id = ?")
				.setParameter(0, userIdentity.getOrganisation().getOrgCoy().getId())
				.setParameter(1, userIdentity.getOrganisation().getId())
				.setParameter(2, journalID)/*
				.setParameter(2, this.settingBo.fetchsettings("interbank-GLP", 2).getSettings_value())*/
				.list();
		
		if (list.size() > 0) 
			return list.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JournalEntry> getJournalEntriesByJournalID(String journalID) {
		
			List<JournalEntry> entries = sessionFactory.getCurrentSession()
					.createQuery("FROM JournalEntry WHERE company_id = ? AND organisation_id = ? AND journal_id = ?")
					.setParameter(0, userIdentity.getOrganisation().getOrgCoy().getId())
					.setParameter(1, userIdentity.getOrganisation().getId())
					.setParameter(2, journalID)/*
					.setParameter(2, this.settingBo.fetchsettings("interbank-GLP", 2).getSettings_value())*/
					.list();

			if (entries.size() > 0 ) 
				return entries;
			
		return null;			
	}

	@Override
	public void removeEntries(String journalID) {

		this.sessionFactory.getCurrentSession().flush();
		this.sessionFactory.getCurrentSession().clear();

		Query query = this.sessionFactory.getCurrentSession().createQuery("DELETE JournalEntry WHERE journal_id = ?");
		//this.sessionFactory.getCurrentSession().createSQLQuery()
		query.setParameter(0, journalID).executeUpdate();
	}

	
}
