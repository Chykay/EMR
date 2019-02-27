package org.calminfotech.utils;

import javax.servlet.http.HttpServletRequest;

import org.calminfotech.system.boInterface.SettingBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppConfig {

	@Autowired
	private SettingBo settingbo;

	// public static final String DEVELOPMENTAL_URL =
	// "http://localhost:8080/NHIS";
	public static final String DEVELOPMENTAL_URL = "../..EMR";
	public static final String APP_URL = "../EMR";
	// public static final String Mail_IP = "127.0.0.1";
	public static final String Mail_IP = "37.220.93.246";
	public static final String MAIL_PORT = "25";
	public static final String MAIL_USERNAME = "";
	public static final String MAIL_PASSWORD = "";

	public static final String ACCT_CREATION = "EMR ACCOUNT CREATION";
	public static final String FORGOT_PASSWORD = "EMR FORGOT PASSWORD ACCOUNT CREATION";
	public static final String ACCT_REACTIVATION = "EMR ACCOUNT REACTIVATION";

	public static String NO_REPLY = "gadeoye@calminfotech.com";
	public static final String DEFAULT_EMAIL_SENDER = "gadeoye@calminfotech.com";

	public static final int NUMBER_OF_MAIL_TRIAL = 5;
	public static final int NUMBER_OF_SMS_TRIAL = 5;
	public static final int NUMBER_OF_RESCHEDULE_TRAIL = 5;

	// measurement
	public static int UNIT_OF_MEASURE_UNIT = 8;

	public static final String getUrl(HttpServletRequest request) {

		return request.getRequestURL().toString();

	}

	public static final String getFUrl(HttpServletRequest request) {

		String url = AppConfig.getUrl(request).split(
				request.getContextPath() + "/")[0]
				+ request.getContextPath();
		return url;
	}

	public static final String getRemoteAdd(HttpServletRequest request) {
		return request.getRemoteAddr().toString();
	}

	public static final String getCP(HttpServletRequest request) {
		return request.getContextPath().toString().toString();
	}

	public static final String getRemoteHost(HttpServletRequest request) {
		return request.getRemoteHost().toString().toString();
	}

	public static void test(HttpServletRequest request) {
		System.out.print("Request URL " + request.getRequestURL().toString());

		System.out.print("Context " + request.getContextPath().toString());

		System.out.print("Remote Addr " + request.getRemoteAddr().toString());

		System.out.print("Remote Hostr " + request.getRemoteHost().toString());

	}

	// String g= abstract getUrl(HttpServletRequest request);

	// public static void main(String[] args, HttpServletRequest request) {
	// TODO Auto-generated method stub
	// // System.out.print(AppConfig.getUNIT_OF_MEASURE_UNIT());
	// test(request);
	// System.out.print(request.getRequestURL().toString());
	// }

}