package org.calminfotech.ledger.boInterface;

import java.util.List;

import org.calminfotech.ledger.forms.TotalingForm;
import org.calminfotech.ledger.models.TotalingCode;

public interface LedgerTotallingBo {
	public List<TotalingCode> fetchAll();

	public List<TotalingCode> fetchAllActive();
	
	public TotalingCode getLedgerById(int id);
	
	public TotalingCode save(TotalingForm totalingForm);
	
	public void delete(TotalingCode totalingCode);
	
	public TotalingCode update(TotalingForm totalingForm, int id);

	public boolean isUsed(TotalingCode totalingCode);

	public TotalingCode updateStatus(TotalingCode totalingCode);

}
