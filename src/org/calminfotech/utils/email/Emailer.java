package org.calminfotech.utils.email;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.system.models.SettingsAssignment;
import org.calminfotech.utils.AppConfig;
import org.calminfotech.utils.OrgInstance;
import org.calminfotech.utils.exception.MailSendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
//import org.apache.log4j.Logger;
//import org.apache.velocity.exception.ResourceNotFoundException;
//import org.apache.velocity.exception.ResourceNotFoundException;
//import org.springframework.ui.velocity.VelocityEngineUtils;

@Service
public class Emailer {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private SettingBo settingbo;

	@Autowired
	private OrgInstance orgIns;

	protected final Log logger = LogFactory.getLog(getClass());
	// private static final Logger log = Logger.getLogger(Emailer.class);

	private static String host = AppConfig.Mail_IP;
	private static String port = AppConfig.MAIL_PORT;

	// private static String from = AppConfig.DEFAULT_EMAIL_SENDER;

	public boolean send(final String to, final String subject,
			final String body, Integer orgId) throws MailSendingException {

		// HmoBoImpl hmimpl = new HmoBoImpl();
		// final String fa=null;

		final SettingsAssignment sa = this.settingbo.fetchsettings(
				"FROM_EMAIL", orgId);

		final String fa;
		if (sa == null) {
			fa = "noreply@noreply.com";

		} else {
			if (sa.getSettings_value() == null
					|| sa.getSettings_value().equals("")) {
				fa = "noreply@noreply.com";
			}

			else {
				fa = sa.getSettings_value();
			}

		}

		MimeMessagePreparator preparator = new MimeMessagePreparator()

		{
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

				message.setFrom(fa); // could be parameterized...

				message.setTo(to);
				message.setSubject(subject);
				message.setText(body, true);

			}
		};

		try {
			this.mailSender.send(preparator);
			return true;

		} catch (MailPreparationException e) {
			logger.info(e.getMessage());
			logger.error(e.getMessage());

			throw new MailSendingException(e.getMessage());

		} catch (MailAuthenticationException e) {
			logger.info(e.getMessage());
			logger.error(e.getMessage());
			throw new MailSendingException(e.getMessage());

		} catch (MailSendException e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
			logger.info(e.getMessage());
			throw new MailSendingException(e.getMessage());
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
			logger.info(e.getMessage());
			throw new MailSendingException(e.getMessage());
		}

	}

}
