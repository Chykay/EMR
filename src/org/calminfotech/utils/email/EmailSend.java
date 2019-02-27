/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.calminfotech.utils.email;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.AppConfig;
import org.calminfotech.utils.dao.NotificationEmailDao;
import org.calminfotech.utils.models.NotificationEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ajiboye Date : 14-April-2014 Remodel of Original mail API
 */

@Service
public class EmailSend {
	// Indicate mail status 1=sent, othes=not sent
	int status = 1;
	String time = displayTime();
	
	@Autowired
	private UserBo userBo;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	public NotificationEmailDao emailDao;

	public EmailSend() {
	}

	// Use constructor to initialize the status value
	public EmailSend(int status) {
		this.status = 0;
		this.status = status;
	}

	public static String displayTime() {
		Date date = new Date();
		String strDateFormat = "HH:mm:ss a";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		return sdf.format(date);
	}

	@Transactional(noRollbackFor = RuntimeException.class)
	public boolean processMail(
			String to, String header, String msg) {
		// declaration of host and port id
		// final String host = "smtp.gmail.com";//"134.213.17.169";
		final String host = AppConfig.Mail_IP;
		// final String host = "127.0.0.1";
		// final String port ="587";//25
		final String port = "587";

		// Object initialization for properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "false");// false
		// props.put("mail.smtp.starttls.enable", "true");//127.0.0.1
		// server//134.213.17.169
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		// Session
		Session session;
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// Used to authenticate the username and password
				return new PasswordAuthentication(AppConfig.MAIL_USERNAME, AppConfig.MAIL_PASSWORD);
			}
		});
		try {
			/**
			 * call to ValidateEmail() to validate the receiver mail validation
			 * status
			 **/
			boolean validationStatus = EmailValidator.validateEmail(to);
			if (validationStatus) {// mail validator
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(AppConfig.MAIL_USERNAME));// From : email
																// Id
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(to));// To : email id
				message.setSubject(header);// set subject of an email
				// Set message content
				message.setContent(
						"<h:body style=background-color:Blue; font-family:verdana; color:#0066cc;>"
								+ msg + "<br/><br/>" + "</body>",
						"text/html; charset = utf-8");

				// Send the entire message content
				Transport.send(message);

				String stat = Integer.toString(status);
				System.out.println("I am here o shade");
				// Save the mail to mail notification
			//	User user = userBo.getUserByEmail(username);
				
				
				NotificationEmail email = new NotificationEmail();
				email.setRecipients(to);
				email.setSubject(header);
				email.setMessage(msg);
				email.setOrganisation(userIdentity.getOrganisation());
				email.setCreatedBy(userIdentity.getUsername());

//				System.out.println("username kunle :"+username);
				//System.out.println("Organisation kunle :"+user.getOrganisation());
				// Hibernate Save
				emailDao.save(email);

			} else {
				status = 0;
			}
		} catch (MessagingException me) {
			System.err.println("Mailing Error: " + me);
			System.err.println("Mailing Error Cause: " + me.getCause());
			//me.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// boolean condition
		if (status == 1) {
			return true;
		} else {
			// System.out.println("Failure Delivery");
			return false;
		}// boolean condition ends

	}// processMail() ends
}
