package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.TitleBo;
import org.calminfotech.system.daoInterface.TitleDao;
import org.calminfotech.system.models.Title;
import org.calminfotech.user.forms.TitleForm;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TitleBoImpl implements TitleBo {

	@Autowired
	private TitleDao titleDao;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<Title> fetchAll() {
		// TODO Auto-generated method stub
		return titleDao.fetchAll();
	}

	@Override
	public Title getTitleById(int id) {
		// TODO Auto-generated method stub
		return titleDao.getTitleById(id);
	}

	@Override
	public void save(TitleForm titleForm) {
		// TODO Auto-generated method stub
		Title title = new Title();
		title.setName(titleForm.getTitle());
		title.setAcronym(titleForm.getAcronym());
		title.setOrganisation(userIdentity.getOrganisation());
		titleDao.save(title);
	}

	@Override
	public void delete(Title title) {
		// TODO Auto-generated method stub
		titleDao.delete(title);
	}

	@Override
	public void update(TitleForm titleForm) {
		// TODO Auto-generated method stub
		Title title = titleDao.getTitleById(titleForm.getId());
		title.setName(titleForm.getTitle());
		title.setAcronym(titleForm.getAcronym());

		titleDao.update(title);
	}

	@Override
	public List<Title> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return this.titleDao.fetchAllByOrganisation(userIdentity
				.getOrganisation());
	}

}
