package org.calminfotech.ledger;


public class AccountType {
	
	public static String getLedger_type(int id){
		switch (id) {
		case 1:
			return "Assets";
			
		case 2:
			return "Liability";
		
		case 3:
			return "Equity";
		
		case 4:
			return "Income";

		default:
			return "Expense";
		
		}
		
	}

}
