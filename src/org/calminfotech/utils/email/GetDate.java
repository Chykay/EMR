/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.calminfotech.utils.email;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ajiboye
 */
public class GetDate {
	public static void main(String[] arg) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-YYYY");// ("yyyy-MM-dd HH:mm:ss.SSS");
		// Calendar cal = Calendar.getInstance();
		// System.out.println(dateFormat.format(cal.getTime()));
		//
		// Date date = new Date();
		// String strDateFormat = "HH:mm:ss a";
		// SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		// System.out.println(sdf.format(date));

		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		Date date = new Date();
		try {
			date = sdf.parse("1 Jan 2013");
			System.out.println(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
