package org.calminfotech.ledger.reports.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class BalanceSheet {
	@Id
	private Integer id;
	
	
}
