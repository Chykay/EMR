package org.calminfotech.utils;

import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.dao.NotificationEmailDao;
import org.calminfotech.utils.dao.NotificationSMSDao;
import org.calminfotech.utils.models.NotificationEmail;
import org.calminfotech.utils.models.NotificationSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotificationCentre {

	@Autowired
	public NotificationEmailDao emailDao;
	

	@Autowired
	public NotificationSMSDao smsDao;
		
	@Autowired
	private UserIdentity userIdentity;

	// Put email
	@Transactional(noRollbackFor = RuntimeException.class)
	public void saveactivationEmail(String recipients, String subject, String message) {
		
		NotificationEmail email = new NotificationEmail();
		email.setRecipients(recipients);
		email.setSubject(subject);
		email.setMessage(message);
		email.setOrganisation(this.userIdentity.getOrganisation());
		email.setCreatedBy(this.userIdentity.getUsername());
		
		emailDao.save(email);
		
		//send email here 
	}

	// Put the sms in the db
	public void saveactivationSMS(String recipientsNumber, String message) {
		
		NotificationSMS sms = new NotificationSMS();
		sms.setRecipients(recipientsNumber);
		sms.setMessage(message);
		
		sms.setOrganisation(this.userIdentity.getOrganisation());
		sms.setCreatedBy(this.userIdentity.getUsername());
		
		this.smsDao.save(sms);
	}
}
