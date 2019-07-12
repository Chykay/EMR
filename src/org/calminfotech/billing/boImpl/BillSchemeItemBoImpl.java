package org.calminfotech.billing.boImpl;

import java.util.List;
import org.calminfotech.billing.boInterface.BillSchemeItemBo;
import org.calminfotech.billing.daoInterface.BillSchemeItemDao;
import org.calminfotech.billing.models.BillSchemeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BillSchemeItemBoImpl implements BillSchemeItemBo {

	@Autowired
	private BillSchemeItemDao billSchemeItemDao;

	/*@Autowired
	private UserIdentity userIdentity;*/

	@Override
	public List<BillSchemeItem> fetchAll() {
		// TODO Auto-generated method stub
		return billSchemeItemDao.fetchAll();
	}

	@Override
	public BillSchemeItem getBillSchemeItemById(int id) {
		// TODO Auto-generated method stub
		return billSchemeItemDao.getBillSchemeItemById(id);
	}

	@Override
	public void save(BillSchemeItem billSchemeItem) {
	  this.billSchemeItemDao.save(billSchemeItem);
	

	}
	@Override
	public void delete(BillSchemeItem billSchemeItem) {
		// TODO Auto-generated method stub
		this.billSchemeItemDao.delete(billSchemeItem);
	}

	@Override
	public void update(BillSchemeItem billSchemeItem) {
		this.billSchemeItemDao.update(billSchemeItem);
      
		}
}
