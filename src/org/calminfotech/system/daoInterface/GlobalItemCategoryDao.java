package org.calminfotech.system.daoInterface;

import java.util.HashSet;
import java.util.List;

//import org.calminfotech.system.models.GlobalCategory;
import org.calminfotech.system.models.GlobalItemCategory;
import org.calminfotech.system.models.GlobalItemCategoryList;
import org.calminfotech.system.models.GlobalItemCategoryOuterRecursive;
import org.calminfotech.system.models.GlobalItemKind;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface GlobalItemCategoryDao {

	public List<GlobalItemCategory> fetchAll(int organisationid);

	public GlobalItemCategory getCategoryById(int categoryId);

	public List<GlobalItemCategoryOuterRecursive> fetchAllTypes();

	public void save(GlobalItemCategory category);

	public void update(GlobalItemCategory globalItemCategory);
	
	public List<GlobalItemCategory> fetchAllByOrganisationByCategoryType(Integer cattypeid);
	
	public List<GlobalItemKind> fetchAllKindByOrganisationByCategoryType(Integer cattypeid);
	
	void delete(GlobalItemCategory category);

	public List<GlobalItemCategory> fetchAll();
	
	
	public List<GlobalItemCategory> fetchTop50byOrganisation(int id);

	public List<GlobalItemCategory> fetchAllCatlistByOrganisation(
			int organisationid);

}
