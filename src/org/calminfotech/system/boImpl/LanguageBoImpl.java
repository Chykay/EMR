package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.LanguageBo;
import org.calminfotech.system.daoInterface.LanguageDao;
import org.calminfotech.system.models.Title;
import org.calminfotech.user.forms.LanguageForm;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LanguageBoImpl implements LanguageBo {

	@Autowired
	private LanguageDao languageDao;

	@Autowired
	private UserIdentity userIdentity;

	public List<Language> fetchAll() {
		return languageDao.fetchAll();

	}

	public Language getLanguageById(int id) {

		return languageDao.getLanguageById(id);

	}

	public void save(LanguageForm languageForm) {
		
		Language language = new Language();
		language.setName(languageForm.getName());
	//	language.setOrganisation(userIdentity.getOrganisation());
		languageDao.save(language);
		
		

	}

	public void delete(Language Language) {

		languageDao.delete(Language);
	}

	public void update(LanguageForm languageForm) {

		
		Language language = languageDao.getLanguageById(languageForm.getId());
		language.setName(languageForm.getName());
	
		languageDao.update(language);
	
		
		
	}

	public List<Language> fetchAllByOrganisation() {
		return this.languageDao.fetchAllByOrganisation(userIdentity
				.getOrganisation());
	}

}
