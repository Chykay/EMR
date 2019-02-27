package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.Title;

public interface TitleDao {

	public List<Title> fetchAll();

	public Title getTitleById(int id);

	public void save(Title title);

	public void delete(Title title);

	public void update(Title title);

	public List<Title> fetchAllByOrganisation(Organisation organisation);

}
