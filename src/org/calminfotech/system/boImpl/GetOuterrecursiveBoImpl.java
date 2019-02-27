package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.GlobalItemCategoryOuterrecursiveBo;
import org.calminfotech.system.daoInterface.GlobalItemCategoryOuterrecursiveDao;
import org.calminfotech.system.models.GlobalItemCategoryOuterRecursive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class GetOuterrecursiveBoImpl implements GlobalItemCategoryOuterrecursiveBo {

	@Autowired
	private GlobalItemCategoryOuterrecursiveDao getOuterrecursiveDao;

	@Override
	public List<GlobalItemCategoryOuterRecursive> fetchAllCategories() {
		// TODO Auto-generated method stub
		return getOuterrecursiveDao.fetchAllCategories();
	}

}
