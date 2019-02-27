package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.GlobalItemCategoryOuterRecursive;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface GlobalItemCategoryOuterrecursiveDao {

	List<GlobalItemCategoryOuterRecursive> fetchAllCategories();
}
