package org.calminfotech.billing.boImpl;

import java.util.List;
import org.calminfotech.billing.boInterface.BillSchemeItemPriceBo;
import org.calminfotech.billing.daoInterface.BillSchemeItemPriceDao;
import org.calminfotech.billing.models.BillSchemeItemPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BillSchemeItemPriceBoImpl implements BillSchemeItemPriceBo {

	@Autowired
	private BillSchemeItemPriceDao billSchemeItemPriceDao;

	/*@Autowired
	private UserIdentity userIdentity;*/

	@Override
	public List<BillSchemeItemPrice> fetchAll() {
		// TODO Auto-generated method stub
		return billSchemeItemPriceDao.fetchAll();
	}

	@Override
	public BillSchemeItemPrice getBillItemPriceById(int id) {
		// TODO Auto-generated method stub
		return billSchemeItemPriceDao.getBillItemPriceById(id);
	}

	@Override
	public void save(BillSchemeItemPrice billSchemeItemPrice) {
	  this.billSchemeItemPriceDao.save(billSchemeItemPrice);
	

	}
	@Override
	public void delete(BillSchemeItemPrice billSchemeItemPrice) {
		// TODO Auto-generated method stub
		this.billSchemeItemPriceDao.delete(billSchemeItemPrice);
	}

	@Override
	public void update(BillSchemeItemPrice billSchemeItemPrice) {
		this.billSchemeItemPriceDao.update(billSchemeItemPrice);
      
		}
}
