package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.VwItemBo;
import org.calminfotech.system.daoInterface.VwItemDao;
import org.calminfotech.system.models.ItemVw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VwItemBoImpl implements VwItemBo {
	
	@Autowired
	private VwItemDao wvItemDao;

	@Override
	public List<ItemVw> fetchAllByPoint(Integer pointId) {
		// TODO Auto-generated method stub
		return this.wvItemDao.fetchAllByPoint(pointId);
	}

	@Override
	public ItemVw getVwItemById(Integer id) {
		// TODO Auto-generated method stub
		return this.wvItemDao.getVwItemById(id);
	}

}
