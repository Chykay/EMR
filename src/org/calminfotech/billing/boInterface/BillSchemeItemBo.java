package org.calminfotech.billing.boInterface;

import java.util.List;

import org.calminfotech.billing.models.BillSchemeItem;

public interface BillSchemeItemBo {

	public Integer g = 0;

	public List<BillSchemeItem> fetchAll();

	public BillSchemeItem getBillSchemeItemById(int id);

	public void save(BillSchemeItem billSchemeItem);

	public void delete(BillSchemeItem billSchemeItem);

	public void update(BillSchemeItem billSchemeItem);

}