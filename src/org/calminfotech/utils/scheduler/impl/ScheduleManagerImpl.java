package org.calminfotech.utils.scheduler.impl;

import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.AppConfig;
import org.calminfotech.utils.dao.NotificationEmailDao;
import org.calminfotech.utils.dao.NotificationSMSDao;
import org.calminfotech.utils.email.Emailer;
import org.calminfotech.utils.exception.MailSendingException;
import org.calminfotech.utils.models.NotificationEmail;
import org.calminfotech.utils.scheduler.ScheduleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class ScheduleManagerImpl implements ScheduleManager {

	@Autowired
	private NotificationEmailDao notificationEmailDao;
	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private NotificationSMSDao notificationSMSDao;

	@Autowired
	private Emailer emailer;

	@Override
	// @Scheduled(initialDelay = 1000, fixedRate = (60000))
	public void sendMailSchedule(RedirectAttributes redirectAttributes) {
		// TODO Auto-generated method stub
		System.out.println("Number of unsent mails: "
				+ this.notificationEmailDao.fetchAllUnSent().size());

		// Loop through all unsent mails for all organisations and send them
		for (NotificationEmail email : this.notificationEmailDao
				.fetchAllUnSent()) {

			// Just for fail safe
			if (!email.isFailed()) {
				try {
					boolean sent = emailer.send(email.getRecipients(),
							email.getSubject(), email.getMessage(),
							userIdentity.getOrganisation().getId());

					if (sent) {
						// if email was sent, flag as sent
						email.setSent(true);
					} else {
						// If not sent, increase the trial number
						if (email.getTrials() < AppConfig.NUMBER_OF_MAIL_TRIAL) {
							email.setTrials(email.getTrials() + 1);
						} else if (email.getTrials() == AppConfig.NUMBER_OF_MAIL_TRIAL) {
							// Else sending mail failed
							email.setFailed(true);
						}
					}

				} catch (MailSendingException e) {
					alert.setAlert(redirectAttributes, Alert.INFO,
							e.getExceptionMsg());
					// return "redirect:/system/users";
					// return "redirect:/system/users";
				}

				//

				// Save the mail new status back
				this.notificationEmailDao.update(email);
			}
		}
	}

	@Override
	// @Scheduled(initialDelay = 10000, fixedDelay = (60000))
	public void sendSMSSchedule() {
		// TODO Auto-generated method stub
		System.out.println("Number of unsent sms: "
				+ this.notificationSMSDao.fetchAllUnSent().size());

	}

	@Override
	public void sendMailSchedule() {
		// TODO Auto-generated method stub

	}

}
