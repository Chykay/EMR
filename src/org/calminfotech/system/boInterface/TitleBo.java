package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.Title;
import org.calminfotech.user.forms.TitleForm;

public interface TitleBo {

	public List<Title> fetchAll();

	public Title getTitleById(int id);

	public void save(TitleForm titleForm);

	public void delete(Title title);

	public void update(TitleForm titleForm);

	public List<Title> fetchAllByOrganisation();
}
