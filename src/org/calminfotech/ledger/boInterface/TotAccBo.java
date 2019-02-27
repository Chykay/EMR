package org.calminfotech.ledger.boInterface;

import java.util.List;

import org.calminfotech.ledger.forms.TotalingForm;
import org.calminfotech.ledger.models.TotalingAccount;

public interface TotAccBo {
	public List<TotalingAccount> fetchAll();
	
	public TotalingAccount getLedgerById(int id);
	
	public TotalingAccount save(TotalingForm totalingForm);
	
	public void delete(TotalingAccount totalingAccount);
	
	public TotalingAccount update(TotalingForm totalingForm, int id);

}
