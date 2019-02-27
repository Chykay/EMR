package org.calminfotech.user.boImpl;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.calminfotech.hrunit.boInterface.StaffRegBoInterface;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.boInterface.TitleBo;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.boInterface.RoleBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.daoInterface.UserDao;
import org.calminfotech.user.forms.UserForm;
import org.calminfotech.user.models.Role;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserActivation;
import org.calminfotech.user.models.UserProfile;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.AppConfig;
import org.calminfotech.utils.Encryptor;
import org.calminfotech.utils.NotificationCentre;
import org.calminfotech.utils.UserTypesList;
import org.calminfotech.utils.dao.InternetConnection;
import org.calminfotech.utils.email.Emailer;
import org.calminfotech.utils.exception.MailSendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
@Transactional
public class UserBoImpl implements UserBo {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserTypesList usertypeBo;

	@Autowired
	private RoleBo roleBo;

	@Autowired
	private Encryptor encryptor;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private TitleBo titleBo;

	@Autowired
	private Alert alert;

	@Autowired
	private OrganisationBo organisationBo;

	@Autowired
	private StaffRegBoInterface staffreg;

	@Autowired
	private Emailer emailSender;

	@Autowired
	private NotificationCentre notificationCentre;

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		userDao.delete(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public List<User> fetchAll() {
		// TODO Auto-generated method stub
		List<User> list = userDao.fetchAll();
		return list;
	}

	@Override
	public User getUserByEmail(String email, Integer orgid) {
		return userDao.getUserByEmail(email, orgid);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		User user = userDao.getUserById(userId);
		return user;
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password,
			Integer orgid) {
		// TODO Auto-generated method stub
		User user = userDao.getUserByEmailAndPassword(email, password, orgid);
		return user;
	}

	@Override
	public List<User> checkUserCredentialsByEmailAndPassword(String email,
			String password, Integer orgid) {
		return userDao.checkUserCredentialsByEmailAndPassword(email, password,
				orgid);
	}

	@Override
	public List<User> checkUserCredentialsByEmailAndPasswordWebAccess(
			String email, String password, Integer orgid) {
		return userDao.checkUserCredentialsByEmailAndPasswordWebAccess(email,
				password, orgid);
	}

	@Override
	public void createUser(RedirectAttributes redirectAttributes,
			UserForm userForm, HttpServletRequest httpServletRequest) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUsername(this.staffreg.getStaffById(userForm.getEmailId())
				.getEmail());

		// fetch role by role bean

		if (userForm.getRole_id().intValue() != 0) {
			Role role = roleBo.getRoleById(userForm.getRole_id());
			user.setRole(role);
		} else {
			user.setRole(null);
		}
		// user.setOrganisation(userIdentity.getOrganisation());
		// user.setOrganisation(this.organisationBo.getOrganisationById(userForm.getOrganisation_id()));
		user.setLock(true);
		// user.setOrganisation(this.staffreg.getStaffByUsernameId(userForm.get).getOrganisation());
		user.setStaff(staffreg.getStaffById(userForm.getUserId()));

		user.setOrganisation(this.staffreg.getStaffById(userForm.getEmailId())
				.getOrganisation());

		user.setCreatedBy(userIdentity.getUsername());
		user.setUserType(this.usertypeBo.getUserTypeById(userForm
				.getUser_type_id()));
		user.setLevelsecurity(Boolean.valueOf(userForm.getLevelsecurity()));

		// Users first name and othernames
		UserProfile userProfile = new UserProfile();
		userProfile.setLastName(userForm.getLastName());
		userProfile.setOtherNames(userForm.getOtherNames());

		// Title title = this.titleBo.getTitleById(userForm.getTitleId());
		// userProfile.setTitle(title);

		user.setUserProfile(userProfile);

		userProfile.setUser(user);

		// Insert the activation to the user

		String code = encryptor.createActivationCode(this.staffreg
				.getStaffById(userForm.getEmailId()).getEmail());

		UserActivation userActivation = new UserActivation();
		userActivation.setActivationCode(code);
		// update user save activation code tru user
		Set<UserActivation> userActivations = user.getUserActivation();
		userActivations.add(userActivation);
		user.setUserActivation(userActivations);
		userActivation.setUser(user);
		// save user*******
		userDao.save(user);
		// save and send mail

		String url = AppConfig.getFUrl(httpServletRequest)
				+ "/user/setup/password/" + code;

		System.out.print("my url" + url);

		// http://apps.calminfotech.com:9980/HMS/user/setup/password/7dba14030a9e63517a3229443443be0e
		// http://localhost:8080/HMS/user/setup/password/7dba14030a9e63517a3229443443be0e
		String mailContent = "<h1>New Account Creation.</h1><div>Dear "
				+ userForm.getLastName() + "<br></br><br></br>" + "<a href='"
				+ url + "'>Click here to activate your account</a>"
				+ " <br></br><br></br>"
				+ userIdentity.getOrganisation().getName() + " EMR Team</div>";
		// user.getUsername() to replace staticgadeoye

		// this.notificationCentre.saveactivationEmail(user.getUsername(),
		// "Activate Account", mailContent);

		// check if internet is available
		InternetConnection internetConnection = new InternetConnection();
		try {
			if (!internetConnection.checkConnection()) {
				String msg = "User Created. Mail not yet sent. Clikc on reactivate to send activation mail";
				// alert.setAlert(redirectAttributes, Alert.INFO, msg);
				// return "redirect:/user/session/login";

			}

			else {

				try {
					System.out.print("Sending mail to new account.."
							+ user.getUsername());
					emailSender.send(user.getUsername(),
							AppConfig.ACCT_CREATION, mailContent, userIdentity
									.getOrganisation().getId());
				} catch (MailSendingException e) {
					alert.setAlert(redirectAttributes, Alert.INFO,
							"User Created. " + e.getExceptionMsg());
					// return "redirect:/system/users";
				}

			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		// Send email
		// EmailSend emailSender = new EmailSend();

		// Emailer emailSender = new Emailer();
		// *************

		System.out.println(mailContent);
		// return null;
	}

	@Override
	public List<User> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return this.userDao.fetchAllByOrganisation(userIdentity
				.getOrganisation());
	}

	@Override
	public List<Organisation> getOrganisationByEmail(String email) {
		// TODO Auto-generated method stub
		return this.userDao.getOrganisationByEmail(email);
	}

}
