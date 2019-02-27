package org.calminfotech.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * DateUtils Object for transforming date to String and back
 * 
 * @author Newton
 *
 */

public class DateUtils {
	
	public static boolean isValidDate(String dateString) {
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	        df.parse(dateString);
	        return true;
	    } catch (ParseException e) {
	        return false;
	    }
	}
	
	

	public static Date formatStringToDate(String dateString) {
		try {
			SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdFormatter.parse(dateString);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}



	
	
	public static Date formatStringToDate(String dateString, String pattern) {
		try {
			SimpleDateFormat sdFormatter = new SimpleDateFormat(pattern);
			Date date = sdFormatter.parse(dateString);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatDateToString(Date date) {
		// Create an instance of SimpleDateFormat used for formatting
		// the string representation of date (month/day/year)
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		// Using DateFormat format method we can create a string
		// representation of a date with the defined format.
		return df.format(date);
	}

	public static String formatDateToString(Date date, String pattern) {
		// Create an instance of SimpleDateFormat used for formatting
		// the string representation of date (month/day/year)
		DateFormat df = new SimpleDateFormat(pattern);

		// Using DateFormat format method we can create a string
		// representation of a date with the defined format.
		return df.format(date);
	}
}
