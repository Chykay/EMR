package org.calminfotech.inventory.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service

public class CodeGenerator {
	
	
	
	public String generateSupplyBatchCode(){
		
		
		  StringBuilder batchCode = new StringBuilder();
		  batchCode.append("BAT-");
          String build = null;
          Date dte = new Date();

          Calendar c = Calendar.getInstance();
          c. setTime(dte);
          batchCode.append(String.valueOf(c.getTimeInMillis()));
          
          batchCode.append("-");
          
          char[] letter = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
          int max = 25, indx = 0;
          int min = 0;

          
          for (int i = 0; i <2; i++) {
              indx = min + (int) (Math.random() * max);
              batchCode.append(String.valueOf(letter[indx]));
          }
         
          return batchCode.toString();
	}

	public String generatePointRequestCode() {
		// TODO Auto-generated method stub
		 StringBuilder batchCode = new StringBuilder();
		  batchCode.append("REQ-");
         String build = null;
         Date dte = new Date();

         Calendar c = Calendar.getInstance();
         c. setTime(dte);
         batchCode.append(String.valueOf(c.getTimeInMillis()));
         
         batchCode.append("-");
         
         char[] letter = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
         int max = 25, indx = 0;
         int min = 0;

         
         for (int i = 0; i <2; i++) {
             indx = min + (int) (Math.random() * max);
             batchCode.append(String.valueOf(letter[indx]));
         }
        
         return batchCode.toString();
	}

	public String generateOrderCode() {
		// TODO Auto-generated method stub
		 StringBuilder batchCode = new StringBuilder();
		  batchCode.append("ORD-");
        String build = null;
        Date dte = new Date();

        Calendar c = Calendar.getInstance();
        c. setTime(dte);
        batchCode.append(String.valueOf(c.getTimeInMillis()));
        
        batchCode.append("-");
        
        char[] letter = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        int max = 25, indx = 0;
        int min = 0;

        
        for (int i = 0; i <2; i++) {
            indx = min + (int) (Math.random() * max);
            batchCode.append(String.valueOf(letter[indx]));
        }
       
        return batchCode.toString();
	}

}
