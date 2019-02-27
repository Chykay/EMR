package org.calminfotech.utils;

import org.springframework.stereotype.Service;

@Service
public class EmailDomain {
	/**
	 * Used to get the domain of every email used to register each organisation
	 * **/
	public static String fetchAt(String mail) {
		String strMail = new String(mail);
		int index = strMail.indexOf("@", 0);
		int length = mail.length();
		String domain = strMail.substring(index, length);
		return domain;
	}

	/**
	 * compare both mail and company's domain
	 * **/
	public static Boolean verifyMail(String mail, String domain) {
		boolean status = false;
		String strMail = new String(mail);
		int index = strMail.indexOf("@", 0);
		int length = mail.length();
		String strDomain = strMail.substring(index, length);
		if (strDomain.equals(domain)) {
			status = true;
		}
		return status;
	}
	
	public static Boolean verification(String organisation, String admin) {
		boolean status = false;
		String strOrg = new String(organisation);
		String strAdm = new String(admin);
		int indexO = strOrg.indexOf("@", 0);
		int indexA = strAdm.indexOf("@", 0);
		int lengthO = strOrg.length();
		int lengthA = strAdm.length();
		String StrOrganisation = strOrg.substring(indexO, lengthO);
		String StrAdmin = strAdm.substring(indexA, lengthA);
		if(StrOrganisation.equals(StrAdmin)){
			status = true;
		}
		return status;
	}

}
