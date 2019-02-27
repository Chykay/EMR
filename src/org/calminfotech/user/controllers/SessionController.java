package org.calminfotech.user.controllers;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.calminfotech.hrunit.boInterface.ClockinBo;
import org.calminfotech.hrunit.boInterface.StaffRegBoInterface;
import org.calminfotech.hrunit.models.ClockAssignment;
import org.calminfotech.hrunit.models.ClockinLog;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.boInterface.UserLoginSessionBo;
import org.calminfotech.user.boInterface.UserSecurityQuestionsBo;
import org.calminfotech.user.forms.ForgotPasswordForm;
import org.calminfotech.user.forms.LoginForm;
import org.calminfotech.user.forms.UserQuestionForm;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserActivation;
import org.calminfotech.user.models.UserLoginSession;
import org.calminfotech.user.models.UserSecurityQuestions;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.AppConfig;
import org.calminfotech.utils.Encryptor;
import org.calminfotech.utils.NotificationCentre;
import org.calminfotech.utils.OrgInstance;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.dao.InternetConnection;
import org.calminfotech.utils.email.Emailer;
import org.calminfotech.utils.exception.MailSendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Layout(value = "layouts/login")
@RequestMapping(value = "/user/session")
public class SessionController {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Alert alert;

	@Autowired
	private OrgInstance orgIns;

	@Autowired
	private UserBo userBo;

	@Autowired
	private StaffRegBoInterface stafregBo;

	/*
	 * @Autowired private RoleBo roleBo;
	 */

	@Autowired
	private ClockinBo clockinBo;

	@Autowired
	private UserSecurityQuestionsBo userSecurityQuestionsBo;

	@Autowired
	private UserLoginSessionBo userLoginSessionBo;

	@Autowired
	private Encryptor encryptor;

	@Autowired
	private Emailer emailSender;

	@Autowired
	private NotificationCentre notificationCentre;

	private Boolean orgassign = false;

	// Display Form
	@RequestMapping(value = { "/login", "/" }, method = RequestMethod.GET)
	public String login1(Model model) {
		orgassign = false;
		if (orgIns.getOrgid().intValue() == 0) {

			return "redirect:/user/session/loginm";

		}

		else {
			return "redirect:/user/session/loginf";

		}
	}

	// Display Form
	@RequestMapping(value = { "/loginf" }, method = RequestMethod.GET)
	public String login(Model model) {
		orgassign = false;
		if (orgIns.getOrgid().intValue() == 0) {
			return "redirect:/user/session/login";
		}
		System.out.print("orgins" + orgIns.getOrgid().toString());
		LoginForm loginform = new LoginForm();
		loginform.setOrgId(orgIns.getOrgid().toString());
		model.addAttribute("loginForm", loginform);
		ForgotPasswordForm forgotform = new ForgotPasswordForm();
		forgotform.setOrgId(orgIns.getOrgid().toString());
		model.addAttribute("forgotPassword", forgotform);

		return "/user/session/loginf";
	}

	// Display Form
	@RequestMapping(value = { "/loginm" }, method = RequestMethod.GET)
	public String loginc(Model model) {

		if (orgIns.getOrgid().intValue() != 0) {
			return "redirect:/user/session/login";
		}
		model.addAttribute("loginForm", new LoginForm());
		model.addAttribute("forgotPassword", new ForgotPasswordForm());

		return "/user/session/loginm";
	}

	// Process the login data
	@RequestMapping(value = "/authenticatem", method = RequestMethod.POST)
	public String authenticate(Model model,
			@Valid @ModelAttribute("loginForm") LoginForm loginform,
			BindingResult result, final RedirectAttributes redirectAttributes,
			HttpServletRequest httpRequest) {

		// If Form contains
		// if (result.hasErrors()) {

		// return "redirect:/user/session/login";
		// }

		// Check if user exist.
		System.out.print("Injected" + orgIns.getOrgid());
		List<User> list = null;
		User user = null;
		try {
			user = userBo.getUserByEmail(loginform.getEmail());

		} catch (Exception e) {
			System.out.print("Login Null");
		}

		if (user == null) {
			// report incorrect user name
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid username or password. Try again !!!");
			return "redirect:/user/session/loginm";
		}

		// User user = list.get(0);

		LoginForm loginForm = new LoginForm();
		loginForm.setEmail(loginform.getEmail());
		loginForm.setOrgId(loginform.getOrgId());

		model.addAttribute("loginForm", loginForm);
		model.addAttribute("forgotPassword", new ForgotPasswordForm());
		model.addAttribute("orglist",
				userBo.getOrganisationByEmail(loginform.getEmail()));
		return "/user/session/loginc";

	}

	// Process the login data
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String authenticatec(Model model,
			@Valid @ModelAttribute("loginForm") LoginForm loginform,
			BindingResult result, final RedirectAttributes redirectAttributes,
			HttpServletRequest httpRequest) {

		// If Form contains
		// if (result.hasErrors()) {

		// return "redirect:/user/session/login";
		// }

		// Check if user exist.
		System.out.print("Injected" + orgIns.getOrgid());
		List<User> list = null;

		System.out.print("orgid1"
				+ Integer.parseInt(loginform.getOrgId().toString()));
		System.out.print("email1" + (loginform.getEmail()));
		System.out.print("password1" + (loginform.getPassword()));

		try {
			list = userBo.checkUserCredentialsByEmailAndPassword(
					loginform.getEmail(),
					encryptor.encrypt(loginform.getPassword()),

					Integer.parseInt(loginform.getOrgId().toString()));
		} catch (Exception e) {
			System.out.print("Login Null");
		}

		if (list == null || list.size() < 1) {
			// report incorrect user name
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid username or password. Try again !!!");

			if (orgIns.getOrgid().intValue() == 0) {

				return "redirect:/user/session/loginm";

			}

			else {
				return "redirect:/user/session/loginf";

			}

		}

		User user = list.get(0);
		if (user.getStatus() && !user.getLock()) {

			HttpSession session = httpRequest.getSession();
			// Set user Id in session for question
			session.setAttribute("who", list.get(0).getUserId());

			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! So We know you. But not completely. So...");

			return "redirect:/user/session/question";
		} else {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Your account is locked. Please contact the admin");
			if (orgIns.getOrgid().intValue() == 0) {

				return "redirect:/user/session/loginm";

			}

			else {
				return "redirect:/user/session/loginf";

			}
		}
	}

	// Display security question
	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public String question(Model model, HttpServletRequest httpRequest,
			final RedirectAttributes redirectAttributes) {

		// Get the userId from the session
		HttpSession session = httpRequest.getSession();

		if (session.getAttribute("who") == null
				|| session.getAttribute("who") == "") {
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"Warning, Are you trying to hack us? Stop that...");
			return "redirect:/user/session/login";
		}

		int userSessionId = (Integer) session.getAttribute("who");
		Random rand = new Random();
		UserSecurityQuestions userSecurityQuestions = userSecurityQuestionsBo
				.getUserSecurityQuestionsByUserId(userSessionId);

		// If Security Question are missing due to incomplete registration
		// Think of a way to send user an email link to set security questions
		if (userSecurityQuestions == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Application Error. Can't find your question sets. Please contact Admin...");
			return "redirect:/user/session/login";
		}

		int randomNumber = rand.nextInt(3) + 1;
		UserQuestionForm userQuestionForm = new UserQuestionForm();
		userQuestionForm.setQuestionNum(randomNumber);

		switch (randomNumber) {
		case 1:
			userQuestionForm.setQuestion(userSecurityQuestions.getQuestionOne()
					.getQuestionText());
			break;
		case 2:
			userQuestionForm.setQuestion(userSecurityQuestions.getQuestionTwo()
					.getQuestionText());
			break;
		case 3:
			userQuestionForm.setQuestion(userSecurityQuestions
					.getQuestionThree().getQuestionText());
			break;
		case 4:
			userQuestionForm.setQuestion(userSecurityQuestions
					.getQuestionFour().getQuestionText());
			break;
		}

		model.addAttribute("userQuestionForm", userQuestionForm);
		return "/user/session/question";
	}

	// Process security answer
	@RequestMapping(value = "/answer", method = RequestMethod.POST)
	public String answer(
			@Valid @ModelAttribute("userQuestionForm") UserQuestionForm userQuestionForm,
			BindingResult result, final RedirectAttributes redirectAttributes,
			HttpServletRequest httpRequest) {

		if (result.hasErrors()) {
			return "redirect:/user/session/question";
		}

		System.out.print("Am logging In");
		// Get the userId from the session
		HttpSession session = httpRequest.getSession();
		if (session.getAttribute("who") == null) {
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"Warning, Are you trying to hack us? Stop that...");
			return "redirect:/user/session/login";
		}

		int userSessionId = (Integer) session.getAttribute("who");
		UserSecurityQuestions userSecurityQuestions = userSecurityQuestionsBo
				.getUserSecurityQuestionsByUserId(userSessionId);

		// If Security Question are missing at this point. Hacker is detected
		if (userSecurityQuestions == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Nice Hack!... But sorry...");
			if (orgIns.getOrgid().intValue() == 0) {

				return "redirect:/user/session/loginm";

			}

			else {
				return "redirect:/user/session/loginf";

			}
		}

		int randomNumber = userQuestionForm.getQuestionNum();
		boolean correct = this.compare(userSecurityQuestions, userQuestionForm,
				randomNumber, userSessionId);
		if (correct) {
			// alert.setAlert(redirectAttributes, Alert.SUCCESS,
			// "Success. You are now logged in.");
			String callbackUrl = (String) session.getAttribute("callbackUrl");
			session.setAttribute("login", true);
			// Get and store user items here
			User user = userBo.getUserById(userSessionId);
/*			userIdentity.setStaffregistration(stafregBo.getStaffByUsernameId(
					user.getUsername(), user.getOrganisation().getOrgCoy()
							.getId()));
*/			System.out.print("Whala login");
			System.out.print(this.userIdentity.getStaffregistration());

			userIdentity.setUserId(user.getUserId());
			userIdentity.setUsername(user.getUsername());
			userIdentity.setUserProfile(user.getUserProfile());
			userIdentity.setCurrentPointId(1);
			userIdentity.setUser(user);

			// userIdentity.setCurrentName("FrontDesk");

			userIdentity.setIdentity(true);
			userIdentity.setRole(user.getRole());
			userIdentity.setOrganisation(user.getOrganisation());
			// set permission to list
			// userIdentity.setuList(user.getUserPermission());

			// userIdentity.setpList(userIdentity.getRole() != null ?
			// userIdentity.getRole().getPermission() : null );

			// print out list value
			System.out.println("Organisation :"
					+ userIdentity.getOrganisation().getDescription());
			System.out.println("Username :" + userIdentity.getUsername());
			System.out.println("User Id :" + userIdentity.getUserId());
			// System.out.println("User Role Id :"+userIdentity.getUser().getRole().getRoleName());

			// Set<Permission> upList = userIdentity.getUserPermission();

			// Set<Permission> pList = userIdentity.getRole().getPermission();

			// for(Permission p : pList ){
			// System.out.println("Kunle check Permission for "+p.getPermissionId()+" is = "+userIdentity.renderLink(p.getPermissionId()));
			// }

			System.out.println("Am  Checking Internet Connection... ");
			/*
			 * InternetConnection internetConnection = new InternetConnection();
			 * try { if(!internetConnection.checkConnection()){ String msg =
			 * "Mail can not be send now, network connection error";
			 * alert.setAlert(redirectAttributes, Alert.INFO, msg); return
			 * "redirect:/system/umgt/users";
			 * 
			 * }
			 * 
			 * else {
			 * System.out.println("Am sending mail right now for log... ");
			 * 
			 * 
			 * try { emailSender.send(user.getUsername(), "LOG IN INFORMATION",
			 * "You logged in to EMR Sofware " + new
			 * Date(System.currentTimeMillis()).getTime() ); } catch
			 * (MailSendingException e) { alert.setAlert(redirectAttributes,
			 * Alert.INFO, e.getExceptionMsg()); //return
			 * "redirect:/system/users";
			 * 
			 * }
			 * 
			 * } catch (UnknownHostException e) { // TODO Auto-generated catch
			 * block
			 * 
			 * System.out.println("Error checking Internet connection..Unkownhost "
			 * + e.getMessage());
			 * 
			 * e.printStackTrace(); } catch (IOException e) { // TODO
			 * Auto-generated catch block
			 * 
			 * System.out.println("Error checking Internet connection..IOEXception "
			 * + e.getMessage());
			 * 
			 * e.printStackTrace(); }
			 */
			// System.out.println("User level Permission :"+upList.getPermissionId()+" | "+upList.getDescription());
			// System.out.println("Kunle check User Permission for "+upList.getPermissionId()+" is = "+userIdentity.renderLink(upList.getPermissionId()));
			// Log user login session

			UserLoginSession userLoginSession = new UserLoginSession();
			userLoginSession.setUsername(user.getUsername());
			userLoginSession.setActionType(UserLoginSession.pActionType.Login
					.toString());
			// Actual save
			System.out.print(userIdentity.getUserId());
			userLoginSessionBo.save(userLoginSession);
			// Check if profile has setup
			if (user.getUserProfile().getGender() == null) {
				// String msg =
				// "Your profile has not been setup. Click on the profile icon at the top right corner of the screen to view and edit your profile details.";
				// alert.setAlert(redirectAttributes, Alert.INFO, msg);
			}

			System.out.print("Am ok logging In");

			if (null == callbackUrl) {
				return "redirect:/";
			} else {
				// Take the user to the url he was on before the security
				// mechanism kicked in
				System.out.print(userIdentity.getUserId());
				return "redirect:" + callbackUrl;
			}

		}

		alert.setAlert(redirectAttributes, Alert.DANGER,
				"Sorry. Invalid Information. Try again");
		return "redirect:/user/session/login";
	}

	// Log user out
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session,
			RedirectAttributes redirectAttributes) {

		// clocking out
		try {
			List<ClockAssignment> clockedlist = clockinBo
					.fetchAllByUserId(userIdentity.getUserId());

			System.out.print("Am clocking off logging out"
					+ userIdentity.getUserId());

			for (ClockAssignment clockassign : clockedlist) {
				ClockinLog clocklog = new ClockinLog();
				clocklog.setUsername(userIdentity.getUsername());
				clocklog.setClockTime(new Date(System.currentTimeMillis()));
				clocklog.setClockingType("ClockOutbyLogOut");
				if (clockassign.getHrunit() != null) {

					clocklog.setClockingUnit((clockassign.getHrunit()
							.getCategoryId().intValue()));
				}
//				clocklog.setOrganisation(userIdentity.getOrganisation());
				clocklog.setUserId(userIdentity.getUserId());
				this.clockinBo.save(clocklog);
			}
			// end cloxking out

			this.clockinBo.deleteAllCheckedValues(userIdentity.getUserId());

			String username = userIdentity.getUsername();

			userIdentity.setIdentity(false);
			userIdentity.setUsername(null);
			userIdentity.setUserProfile(null);
			userIdentity.setUserId(0);

			// Log user login session
			UserLoginSession userLoginSession = new UserLoginSession();
			userLoginSession.setUsername(username);
			userLoginSession.setActionType(UserLoginSession.pActionType.Logout
					.toString());
			// Actual save
			userLoginSessionBo.save(userLoginSession);

			session.removeAttribute("login"); // Remove the login session
			session.invalidate(); // Destroy session Object

			System.out.print("Am ok clocking off logging out"
					+ userIdentity.getUserId());

		} catch (Exception e) {
			System.out.print("Error logging out" + e.getMessage());
		}

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"You have been logged out!");

		if (orgIns.getOrgid().intValue() == 0) {

			return "redirect:/user/session/loginm";

		}

		else {
			return "redirect:/user/session/loginf";

		}

	}

	public boolean compare(UserSecurityQuestions userSecurityQuestions,
			UserQuestionForm userQuestionForm, int randomNumber,
			int userSessionId) {

		System.out.println("Form Answer: " + userQuestionForm.getAnswer());
		System.out.println("Random Number: " + randomNumber);
		switch (randomNumber) {
		case 1:
			if (encryptor.encrypt(userQuestionForm.getAnswer())
					.equalsIgnoreCase(userSecurityQuestions.getAnswerOne())) {
				System.out.println("Answer One: "
						+ userSecurityQuestions.getAnswerOne());
				return true;
			}
			break;
		case 2:
			if (encryptor.encrypt(userQuestionForm.getAnswer())
					.equalsIgnoreCase(userSecurityQuestions.getAnswerTwo())) {
				System.out.println("Answer Two: "
						+ userSecurityQuestions.getAnswerTwo());
				return true;
			}
			break;
		case 3:
			if (encryptor.encrypt(userQuestionForm.getAnswer())
					.equalsIgnoreCase(userSecurityQuestions.getAnswerThree())) {
				System.out.println("Answer Three: "
						+ userSecurityQuestions.getAnswerThree());
				return true;
			}
			break;
		case 4:
			if (encryptor.encrypt(userQuestionForm.getAnswer())
					.equalsIgnoreCase(userSecurityQuestions.getAnswerFour())) {
				System.out.println("Answer Four: "
						+ userSecurityQuestions.getAnswerFour());
				return true;
			}
			break;
		default:
			return false;
		}
		return false;
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String forgotPassword(
			@Valid @ModelAttribute("forgotPassword") ForgotPasswordForm forgotPasswordForm,
			BindingResult result, final RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest httpServletRequest)
			throws UnknownHostException, IOException {

		if (forgotPasswordForm.getEmail().isEmpty()) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please enter your email in the password recovery form!");
		} else {

			User user = userBo.getUserByEmail(forgotPasswordForm.getEmail(),
					Integer.parseInt(forgotPasswordForm.getOrgId().toString()));

			if (null != user) {

				// Expire existing Reactivation code....
				Set<UserActivation> userActivations = user.getUserActivation();
				for (UserActivation userActivation : userActivations) {
					if (!userActivation.isExpired()) {
						userActivation.setExpired(true);
						userActivation.setModifiedDate(new Date(System
								.currentTimeMillis()));
					}
				}
				// Add new activation code here

				// Create the encrypted code
				String code = encryptor.createActivationCode(forgotPasswordForm
						.getEmail());

				UserActivation userActivation = new UserActivation();
				userActivation.setUser(user);
				userActivation.setActivationCode(code);
				userActivation.setUsed(false);
				userActivations.add(userActivation); // Add new code here

				user.setUserActivation(userActivations);
				user.setLock(true);
				user.setModifiedDate(new Date(System.currentTimeMillis()));
				userBo.update(user);

				String url = AppConfig.getFUrl(httpServletRequest)
						+ "/user/setup/password/" + code;

				System.out.print("my url" + url);

				String mailContent = "<h1>Forgot Password? Reactivate your account</h1><div>Dear "
						+ user.getUserProfile().getLastName()
						+ "<br></br><br></br>"
						+ "<a href='"
						+ url
						+ "'> Click here to re-activate your "
						+ "account</a>"
						+ " <br></br><br></br>" + " EMR Team</div>";
				// check if internet is available
				InternetConnection internetConnection = new InternetConnection();

				if (!internetConnection.checkConnection()) {
					String msg = "Mail can not be send now, network connection error";
					alert.setAlert(redirectAttributes, Alert.INFO, msg);

					if (orgIns.getOrgid().intValue() == 0) {

						return "redirect:/user/session/loginm";

					}

					else {
						return "redirect:/user/session/loginf";

					}
				} else {
					System.out.print("dd");

					try

					{
						emailSender
								.send(user.getUsername(),
										AppConfig.FORGOT_PASSWORD, mailContent,
										Integer.parseInt(forgotPasswordForm
												.getOrgId()));
					} catch (MailSendingException e) {
						alert.setAlert(redirectAttributes, Alert.INFO,
								e.getExceptionMsg());
						// return "redirect:/system/users";
						// return "redirect:/system/users";
					}
					// Send email
					// EmailSend emailSender = new EmailSend();

					alert.setAlert(redirectAttributes, Alert.SUCCESS,
							"Success! Email Sent to your inbox or Spam box for reactivation");

				}

			} else {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Sorry! Email does not exist our records");

			}
		}

		if (orgIns.getOrgid().intValue() == 0) {

			return "redirect:/user/session/loginm";

		}

		else {
			return "redirect:/user/session/loginf";

		}
	}

	@RequestMapping(value = "/forgotm", method = RequestMethod.POST)
	public String forgotPasswordm(
			@Valid @ModelAttribute("forgotPassword") ForgotPasswordForm forgotPasswordForm,
			BindingResult result, final RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest httpServletRequest)
			throws UnknownHostException, IOException {

		if (forgotPasswordForm.getEmail().isEmpty()) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please enter your email in the password recovery form!");
		} else {

			User user = null;
			try {
				user = userBo.getUserByEmail(forgotPasswordForm.getEmail());

			} catch (Exception e) {
				System.out.print("Login Null");
			}

			if (user == null) {
				// report incorrect user name
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Invalid username or password. Try again !!!");
				return "redirect:/user/session/loginm";
			}

			// User user = list.get(0);

			LoginForm loginForm = new LoginForm();
			loginForm.setEmail(forgotPasswordForm.getEmail());
			// loginForm1.setOrgId(orgId)

			model.addAttribute("loginForm", loginForm);
			model.addAttribute("forgotPassword", new ForgotPasswordForm());
			model.addAttribute("orglist", userBo
					.getOrganisationByEmail(forgotPasswordForm.getEmail()));

		}
		return "/user/session/forgotc";

	}

}
