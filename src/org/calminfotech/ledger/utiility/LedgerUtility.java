package org.calminfotech.ledger.utiility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class LedgerUtility {
	
	//split account no
	//generate batch no
	public static String getBatchNo() {
		SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
		return ft.format(new Date());
	}

}
