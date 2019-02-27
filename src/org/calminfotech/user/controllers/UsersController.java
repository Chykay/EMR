package org.calminfotech.user.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.calminfotech.hrunit.boInterface.StaffRegBoInterface;
import org.calminfotech.hrunit.models.StaffRegistration;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.boInterface.TitleBo;
import org.calminfotech.system.models.Title;
import org.calminfotech.user.boInterface.RoleBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.boInterface.UserLoginSessionBo;
import org.calminfotech.user.forms.TitleForm;
import org.calminfotech.user.forms.UserForm;
import org.calminfotech.user.models.Role;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserActivation;
import org.calminfotech.user.models.UserProfile;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.AppConfig;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.BedList;
import org.calminfotech.utils.Encryptor;
import org.calminfotech.utils.NotificationCentre;
import org.calminfotech.utils.UserTypesList;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.dao.InternetConnection;
import org.calminfotech.utils.email.Emailer;
import org.calminfotech.utils.exception.MailSendingException;
import org.calminfotech.visitqueue.forms.VisitWorkflowPointUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
// @Transactional
@RequestMapping(value = "/system/umgt/users")
public class UsersController {

	@Autowired
	private BedList blist;

	@Autowired
	private StaffRegBoInterface staffreg;

	@Autowired
	private Emailer emailSender;

	@Autowired
	private UserTypesList usertypeBo;

	@Autowired
	private UserBo userBo;

	@Autowired
	private UserLoginSessionBo userLoginSession;

	@Autowired
	private RoleBo roleBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Encryptor encryptor;

	@Autowired
	private TitleBo titleBo;

	@Autowired
	private UserTypesList UsertypeBo;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private StaffRegBoInterface staffregBo;

	@Autowired
	private OrganisationBo organisationBo;

	@Autowired
	private NotificationCentre notificationCentre;

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	public String indexAction(Model model) {

		// Index the users to the front
		model.addAttribute("users", userBo.fetchAll());

		return "system/umgt/users/index";
	}

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addAction(Model model, RedirectAttributes redirectAttributes) {
		if (true) {

			// System.out.print("Oya now" + blist.mystr);
			List<StaffRegistration> staffemails = this.staffregBo
					.fetchAllByOrganisationNotIn(userIdentity.getOrganisation()
							.getId());

			model.addAttribute("staffemails", staffemails);
			model.addAttribute("userForm", new UserForm());
			model.addAttribute("roles", roleBo
					.fetchAllRoleByOrganisation(userIdentity.getOrganisation()));
			model.addAttribute("usertype", UsertypeBo.fetchAll());

			model.addAttribute("coy", this.organisationBo.fetchAll(0));

			return "system/umgt/users/add";

		} else {
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";
		}
	}

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String entryAction(
			@Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest httpServletRequest) {

		if (true) {

			/*
			 * if (result.hasErrors()) { // model.addAttribute("roles",
			 * roleBo.fetchAllRoleByOrganisation
			 * (userIdentity.getOrganisation())); model.addAttribute("usertype",
			 * UsertypeBo.fetchAll()); List<StaffRegistration> staffemails =
			 * this
			 * .staffregBo.fetchAllByOrganisationNotIn(userIdentity.getOrganisation
			 * ().getId()); model.addAttribute("staffemails",staffemails);
			 * return "system/umgt/users/add"; }
			 */
			try {

				userBo.createUser(redirectAttributes, userForm,
						httpServletRequest);
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						"Success!, User created!");

			} catch (Exception e) {
				alert.setAlert(redirectAttributes, Alert.DANGER, e.getMessage());

			}
			return "redirect:/system/umgt/users";

		}

		else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String viewAction(@PathVariable("id") int userId, Model model,
			RedirectAttributes redirectAttributes) {
		if (true) {

			User user = userBo.getUserById(userId);
			model.addAttribute("user", user);
			model.addAttribute("loginSessionList",
					userLoginSession.fetchAllByUsername(user.getUsername()));

			int i = 0;

			return "system/umgt/users/view";
		} else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String EditviewAction(@PathVariable("id") int id, Model model,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpRequest) {

		if (true) {

			User user = userBo.getUserById(id);
			/*
			 * if
			 * (user.getUsername().toString().equals(userIdentity.getUsername(
			 * ).toString()))
			 * 
			 * { alert.setAlert(redirectAttributes, Alert.SUCCESS,
			 * "You cannot Edit Your own Info!");
			 * 
			 * return "redirect:/system/umgt/users";
			 * 
			 * }
			 */

			UserForm userform = new UserForm();

			userform.setLastName(user.getUserProfile().getLastName());

			userform.setOtherNames(user.getUserProfile().getOtherNames());

			userform.setEmail(user.getUsername());
			if (user.getRole() != null) {
				userform.setRole_id(user.getRole().getRoleId());
			}

			userform.setUser_type_id(user.getUserType().getId());

			userform.setLevelsecurity(user.getLevelsecurity());

			userform.setOrganisation_id(user.getOrganisation().getId());
			System.out.print("#######");
			System.out.print(user.getOrganisation().getId());
			System.out.print(user.getOrganisation().getName());

			System.out.print("Sizing"
					+ roleBo.fetchAllRoleByOrganisation(user.getOrganisation())
							.size());
			System.out.print("#######");

			model.addAttribute("userForm", userform);
			model.addAttribute("roles",
					roleBo.fetchAllRoleByOrganisation(user.getOrganisation()));
			model.addAttribute("usertype", UsertypeBo.fetchAll());

			if (userIdentity.getUserProfile().getUser().getOrganisation()
					.getId() == 1) {
				model.addAttribute("coy", this.organisationBo.fetchAll(0));
			} else {
				model.addAttribute(
						"coy",
						this.organisationBo.getOrganisationById(userIdentity
								.getUserProfile().getUser().getOrganisation()
								.getId()));

			}

			// model.addAttribute("loginSessionList",
			// userLoginSession.fetchAllByUsername(user.getUsername()));

			// int i = 0;

			auditor.before(httpRequest, "Users", userform);
			return "system/umgt/users/edit";
		}

		else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			@PathVariable("id") int id, Model model,
			HttpServletRequest httpRequest) {
		// Get the user's id
		if (true) {
			User user = userBo.getUserById(id);

			/*
			 * if
			 * (user.getUsername().toString().equals(userIdentity.getUsername(
			 * ).toString())) { alert.setAlert(redirectAttributes,
			 * Alert.SUCCESS, "You cannot Edit Your own Info!");
			 * 
			 * return "redirect:/system/umgt/users";
			 * 
			 * }
			 */
			/*
			 * if (result.hasErrors()) { model.addAttribute("user", user);
			 * 
			 * model.addAttribute("roles",
			 * roleBo.fetchAllRoleByOrganisation(userIdentity
			 * .getOrganisation()));
			 * 
			 * model.addAttribute("usertype", UsertypeBo.fetchAll());
			 * 
			 * return "system/umgt/users/edit"; }
			 */
			UserProfile userProfile = user.getUserProfile();
			userProfile.setLastName(userForm.getLastName());
			userProfile.setOtherNames(userForm.getOtherNames());

			if (userForm.getRole_id().intValue() != 0) {
				user.setRole(this.roleBo.getRoleById(userForm.getRole_id()));
			} else {
				user.setRole(null);
			}
			user.setUserType(this.usertypeBo.getUserTypeById(userForm
					.getUser_type_id()));
			user.setLevelsecurity(Boolean.valueOf(userForm.getLevelsecurity()));
			user.setModifiedDate(new GregorianCalendar().getTime());
			user.setModified_by(userIdentity.getUsername());

			userProfile.setModifiedDate(new GregorianCalendar().getTime());
			userProfile.setModified_by(userIdentity.getUsername());

			userProfile.setUser(user);
			user.setUserProfile(userProfile);

			// user.setOrganisation(this.userBo.getUserByEmail(userForm.getEmail()).getOrganisation());

			userBo.update(user);
			auditor.after(httpRequest, "Users", userForm,
					userIdentity.getUsername(), id);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Profile Updated!");

			return "redirect:/system/umgt/users";
		}

		else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@RequestMapping(value = "/lock/{id}", method = RequestMethod.GET)
	public String lockAction(@PathVariable("id") int userId,
			RedirectAttributes redirectAttributes, Model model) {

		if (true) {
			UserForm userForm = new UserForm();
			userForm.setUserId(userId);
			User user = userBo.getUserById(userId);
			model.addAttribute("userForm", userForm);
			model.addAttribute("user", user);
			return "system/umgt/users/lock";
		} else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@RequestMapping(value = "/lock/toggle", method = RequestMethod.POST)
	public String lockToggle(
			@Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest httpServletRequest) {
		if (true) {
			User user = userBo.getUserById(userForm.getUserId());

			if (null == user) {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Error! Invalid resource");
				return "system/umgt/users";
			}
			if (user.getLock()) {
				user.setLock(false);
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						"Success! User unlocked");
			} else {
				user.setLock(true);
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						"Success! User locked");
			}
			user.setModifiedDate(new Date(System.currentTimeMillis()));
			userBo.update(user);
			return "redirect:/system/umgt/users";
		} else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@RequestMapping(value = "/webaccess/{id}", method = RequestMethod.GET)
	public String webaccessAction(@PathVariable("id") int userId,
			RedirectAttributes redirectAttributes, Model model) {

		if (true) {
			UserForm userForm = new UserForm();
			userForm.setUserId(userId);
			User user = userBo.getUserById(userId);
			model.addAttribute("userForm", userForm);
			model.addAttribute("user", user);
			return "system/umgt/users/webaccess";
		} else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@RequestMapping(value = "/webaccess/toggle", method = RequestMethod.POST)
	public String webaccessToggle(
			@Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest httpServletRequest) {
		if (true) {
			User user = userBo.getUserById(userForm.getUserId());

			if (null == user) {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Error! Invalid resource");
				return "system/umgt/users";
			}
			if (user.getWebaccess()) {
				user.setWebaccess(false);
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						"Success! Web Acces Revoked");
			} else {
				user.setWebaccess(true);
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						"Success! Web Acces Granted");
			}
			user.setModifiedDate(new Date(System.currentTimeMillis()));
			userBo.update(user);
			return "redirect:/system/umgt/users";
		} else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@RequestMapping(value = "/reactivate/{id}", method = RequestMethod.GET)
	public String reactivateAction(@PathVariable("id") int userId,
			RedirectAttributes redirectAttributes, Model model) {
		if (true) {

			User user = userBo.getUserById(userId);

			if (null == user) {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Error! Invalid resource");
				return "system/users";
			}

			UserForm userForm = new UserForm();
			userForm.setUserId(user.getUserId());
			userForm.setEmail(user.getUsername());

			model.addAttribute("userForm", userForm);
			model.addAttribute("user", user);
			return "system/umgt/users/reactivate";
		} else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@RequestMapping(value = "/reactivated", method = RequestMethod.POST)
	public String reactivatedAction(
			@Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest httpServletRequest) {

		AppConfig.test(httpServletRequest);

		if (true) {
			User user = userBo.getUserById(userForm.getUserId());

			if (null == user) {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Error! Invalid resource");
				return "system/umgt/users";
			}

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
			String code = encryptor.createActivationCode(userForm.getEmail());

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

			String mailContent = "<h1>Reactivate your account</h1><div>Dear "
					+ user.getUserProfile().getLastName()
					+ " <br></br><br></br>" + "<a href='" + url
					+ "'> Click here to re-activate your " + "account</a>"
					+ " <br></br><br></br>"
					+ userIdentity.getOrganisation().getName()
					+ " EMR Team</div>";
			// Send email
			// this.notificationCentre.saveactivationEmail(user.getUsername(),
			// "Reactivation for EMR Account", mailContent);

			// check if internet is available
			InternetConnection internetConnection = new InternetConnection();
			try {
				if (!internetConnection.checkConnection()) {
					String msg = "Mail can not be send now, network connection error or no Internet";
					alert.setAlert(redirectAttributes, Alert.INFO, msg);
					return "redirect:/system/umgt/users";

				}

				else {
					System.out.print("Am Sending right now...for reactivation");
					try {
						emailSender.send(user.getUsername(),
								AppConfig.ACCT_REACTIVATION, mailContent,
								userIdentity.getOrganisation().getId());
					} catch (MailSendingException e) {
						alert.setAlert(redirectAttributes, Alert.INFO,
								e.getExceptionMsg());
						return "redirect:/system/umgt/users";
					}
				}

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block

				System.out
						.println("Error checking Internet connection..Unkownhost "
								+ e.getMessage());

				e.printStackTrace();
				alert.setAlert(redirectAttributes, Alert.INFO, e.getMessage());
				return "redirect:/system/umgt/users";
			} catch (IOException e) {
				// TODO Auto-generated catch block

				System.out
						.println("Error checking Internet connection..IOEXception "
								+ e.getMessage());

				e.printStackTrace();
				alert.setAlert(redirectAttributes, Alert.INFO, e.getMessage());
				return "redirect:/system/umgt/users";

			}
			// Send email
			// Emailer emailSender = new Emailer();

			System.out.print(user.getUsername());
			System.out.print(AppConfig.ACCT_REACTIVATION);
			System.out.print(mailContent);

			alert.setAlert(
					redirectAttributes,
					Alert.SUCCESS,
					"Success! Reactivation code sent to..."
							+ user.getUsername());
			return "redirect:/system/umgt/users";
		} else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String viewImage(HttpServletResponse response,
			@PathVariable("id") Integer id) {
		User user = userBo.getUserById(id);
		UserProfile userProfile = user.getUserProfile();

		if (null != userProfile.getProfileImage()) {
			try {
				response.setContentType(userProfile.getImageContentType());
				response.setHeader("Content-Disposition", "inline;filename=\""
						+ userProfile.getLastName() + "\"");
				OutputStream outputStream = response.getOutputStream();
				IOUtils.copy(userProfile.getProfileImage().getBinaryStream(),
						outputStream);
				outputStream.flush();
				outputStream.close();

			} catch (IOException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/workflowpoint/update", method = RequestMethod.POST)
	public String updateWorkflowPoints(
			@Valid @ModelAttribute("pointsForm") VisitWorkflowPointUserForm form,
			BindingResult result) {
		String response = "";
		boolean status = true;
		String payload = null;

		if (result.hasErrors()) {
			status = false;
			response = "{ \"status\":" + status + ", \"payload\": " + payload
					+ "}";
			return response;
		}

		User user = this.userBo.getUserById(form.getUserId());
		// user.getWorkflowPoints().clear();

		// for all the selected checkbox, loop through the all points and attach
		// selected ones only
		// for (int i = 0; i < form.getPointsId().length; i++) {
		// for (WorkflowPoint point : this.WorkflowPointBo
		// .fetchAllByOrganisation()) {
		// if (point.getId() == form.getPointsId()[i]) {
		// user.getWorkflowPoints().add(point);
		// }
		// }
		// }

		this.userBo.update(user);

		// if (user.getWorkflowPoints().size() > 0) {
		payload = "";
		// for (VisitWorkflowPoint point : user.getWorkflowPoints()) {
		// payload += "<tr><td>" + point.getName() + "</td></tr>";
		// }
		// } else {
		// payload = "<tr><td>User not associated with any workflow</td></tr>";
		// }

		response = "{ \"status\":" + status + ", \"payload\": \"" + payload
				+ "\"}";
		return response;
	}

	/**
	 * 
	 * Titles
	 * 
	 */
	@Layout("layouts/datatable")
	@RequestMapping(value = { "/titles" }, method = RequestMethod.GET)
	public String titlesAction(Model model) {

		model.addAttribute("titles", titleBo.fetchAllByOrganisation());
		model.addAttribute("titleForm", new TitleForm());
		return "system/umgt/users/titles/index";
	}

	@RequestMapping(value = { "/titles" }, method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("titleForm") TitleForm titleForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("titles", titleBo.fetchAll());
			return "system/umgt/users/titles/index";
		}

		titleBo.save(titleForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Title Created!");

		return "redirect:/system/umgt/users/titles";
	}

	@RequestMapping(value = "/titles/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		Title title = titleBo.getTitleById(id);
		if (null == title) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource.");
			return "redirect:/system/umgt/users/titles";
		}
		TitleForm titleForm = new TitleForm();
		titleForm.setId(title.getId());
		titleForm.setTitle(title.getName());
		titleForm.setAcronym(title.getAcronym());

		model.addAttribute("titleForm", titleForm);

		auditor.before(request, "Title Form", titleForm);
		return "system/umgt/users/titles/edit";
	}

	@RequestMapping(value = "/titles/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("titleForm") TitleForm titleForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "system/users/titles/edit";
		}

		titleBo.update(titleForm);
		auditor.after(request, "Title Form", titleForm,
				userIdentity.getUsername(), titleForm.getId());
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Title updated");
		return "redirect:/system/umgt/users/titles";
	}

	@RequestMapping(value = "/titles/delete/{id}")
	public String deleteTitle(@PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes) {
		Title title = this.titleBo.getTitleById(id);
		if (null == title) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "reidrect:/system/umgt/users/titles";
		}

		this.titleBo.delete(title);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Title deleted.");
		return "redirect:/system/umgt/users/titles";
	}

	@RequestMapping(value = "/testmail")
	public String testmail() {

		// Boolean p =
		// emailSender.send("gadeoye@calminfotech.com","Good Subject","welcome");

		// Boolean e =
		// emailSender.send("gadeoye@calminfotech.com","Good Subject","<html> <body> <div><a href='www.gmail.com'>Click here to activate your account</a></div></body></html>");

		// Boolean f =
		// emailSender.send("adeoyegodwin@gmail.com","Good Subject","welcome");

		// Boolean g =
		// emailSender.send("adeoyegodwin@gmail.com","Good Subject","<html> <body> <div><a href='www.gmail.com'>Click here to activate your account</a></div></body></html>");

		return "redirect:/";
	}

	@RequestMapping(value = "/fetchstaffnames/{id}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String ajaxfetchcategory1(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		String Str = "";
		// model.addAttribute("ExaminationType",ExaminationTypeBo.fetchAllByOrganisation());

		// model.addAttribute("categories",
		// ExaminationCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));

		// StaffRegistration staff =
		// this.staffregBo.getStaffByUsernameId(email);

		StaffRegistration staff = this.staffregBo.getStaffById(id);
		System.out.print("*********");

		System.out.print(staff.getFirstName());

		System.out.print(staff.getLastName());

		System.out.print("*********");

		// try
		// {
		if (staff != null) {
			Str = staff.getLastName() + "##" + staff.getFirstName();
		}

		// catch(Exception e)
		// {

		// System.out.print(e.getMessage());

		// }
		System.out.print("&&&");
		// System.out.print (staff.getOrganisation());
		System.out.print("&&&");
		String sr = "<option value=''>Select..</option>";
		List<Role> staffrole = roleBo.fetchAllRoleByOrganisation(staff
				.getOrganisation());
		System.out.print("*********ajax");

		System.out.print(staffrole.size());
		System.out.print("*********ajax");

		if (staffrole != null)

			for (Role sgr : staffrole) {
				sr += "<option value='" + sgr.getRoleId() + "'>"
						+ sgr.getRoleName() + "</option>";
			}

		return Str + "##" + sr;
	}

}