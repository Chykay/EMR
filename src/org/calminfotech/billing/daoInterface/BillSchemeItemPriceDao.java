package org.calminfotech.billing.daoInterface;

	import java.util.List;

import org.calminfotech.billing.models.BillSchemeItemPrice;


	public interface BillSchemeItemPriceDao {

		public List<BillSchemeItemPrice> fetchAll();

		public BillSchemeItemPrice getBillItemPriceById(int id);
		
		public void save(BillSchemeItemPrice billSchemeItemPrice);
		
		public void delete(BillSchemeItemPrice billSchemeItemPrice);

		public void update(BillSchemeItemPrice billSchemeItemPrice);
		
}
