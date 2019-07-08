package org.calminfotech.ledger.boInterface;

import org.calminfotech.ledger.forms.LedgerAccForm;
import org.calminfotech.ledger.models.LedgerAccount;

public interface GLSetupBo {

	public LedgerAccount getReserveGL();
	
	public LedgerAccount reserveGL(LedgerAccForm ledgerAccForm);
}
