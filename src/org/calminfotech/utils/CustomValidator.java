package org.calminfotech.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomValidator {

	//validate date
		public boolean validateDate(String date, String format) {

	        String[] details = null;
	        int mth = 0;
	        String yr = null;
	        int dy = 0;
	        String delim = null;

	        if (date == null) {
	            return false;
	        }
	        String pattern = null;
	        SimpleDateFormat formatter = null;
	        switch (getFormat(format)) {
	            case 1:
	                pattern = "dd/MM/yyyy";
	                formatter = new SimpleDateFormat(pattern);

	                details = date.split("/");
	                try {
	                    formatter.parse(date);

	                    mth = Integer.parseInt(details[1]);
	                    yr = details[2];
	                    Integer.parseInt(yr);
	                    dy = Integer.parseInt(details[0]);
	                } catch (NumberFormatException nfe) {
	                    return false;
	                } catch (ParseException e) {
	                    return false;
	                }
	                break;

	            case 2:
	                pattern = "yyyy-mm-dd";
	                formatter = new SimpleDateFormat(pattern);

	                details = date.split("-");
	                try {
	                    formatter.parse(date);
	                    mth = Integer.parseInt(details[1]);
	                    yr = details[0];
	                    Integer.parseInt(yr);
	                    dy = Integer.parseInt(details[2]);
	                } catch (NumberFormatException nfe) {
	                    return false;
	                } catch (ParseException e) {
	                    return false;
	                }
	                break;
	        }
	        if (yr.length() != 4) {
	            return false;
	        }
	        if (mth < 1 || mth > 12) {
	            return false;
	        }

	        if (!isValidDayValue(yr, mth, dy)) {
	            return false;
	        }
	        return true;
	    }

	    private int getFormat(String format) {

	        if (format.equals("dd/mm/yyyy")) {
	            return 1;
	        } else if (format.equals("yyyy-mm-dd")) {
	            return 2;
	        }
	        return 0;
	    }
	    
	    private boolean isValidDayValue(String yr, int mth, int dy) {
	        // throw new UnsupportedOperationException("Not yet implemented");
	        int minDayValue = 1;
	        int maxDayValue = 0;

	        switch (mth) {

	            case 2: //case february
	                maxDayValue = 28;
	                int rem = Integer.parseInt(yr) % 4;
	                if (rem == 0) {
	                    maxDayValue = 29;
	                }
	                break;

	            case 4:
	            case 6:
	            case 9:
	            case 11://case april,september,november,june

	                maxDayValue = 30;

	                break;

	            default:
	                maxDayValue = 31;
	                break;

	        }
	        if (dy >= minDayValue && dy <= maxDayValue) {
	            return true;
	        }
	        return false;

	    }
}
