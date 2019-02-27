package org.calminfotech.user.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.calminfotech.user.boInterface.SecurityQuestionBo;
import org.calminfotech.user.boInterface.UserActivationBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.boInterface.UserSecurityQuestionsBo;
import org.calminfotech.user.forms.PasswordSetupForm;
import org.calminfotech.user.forms.UserQuestionsForm;
import org.calminfotech.user.models.SecurityQuestion;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserActivation;
import org.calminfotech.user.models.UserSecurityQuestions;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/user/setup")
@Layout(value = "layouts/login")
public class SetupController {

	@Autowired
	private Alert alert;

	@Autowired
	private UserBo userBo;

	@Autowired
	private UserActivationBo userActivationBo;

	@Autowired
	private SecurityQuestionBo securityQuestionBo;

	@Autowired
	private UserSecurityQuestionsBo userSecurityQuestionsBo;

	@RequestMapping(value = "/password/{code}", method = RequestMethod.GET)
	public String setPassword(Model model, @PathVariable("code") String code,
			RedirectAttributes redirectAttributes) {
		if (code.isEmpty())
			return "user/setup/error";

		// check if the user activation is available.
		UserActivation userActivation = userActivationBo
				.getUserActivation(code);
		if (userActivation == null)
			return "user/setup/error";
		//get current time
		Date currentTime = new Date(System.currentTimeMillis());
		// (current_time)-(Activation_time)
		long timeDifference = currentTime.getTime()
				- userActivation.getCreatedDate().getTime();

		long twentyFourHourInMilliseconds = 86400000;

		if (twentyFourHourInMilliseconds < timeDifference) {
			alert.setAlert(
					redirectAttributes,
					Alert.WARNING,
					"Your activation link has expired! Please contact the admin for a new activation link");
			return "redirect:/";
		}

		if (userActivation.isUsed() == true) {
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"Your account is active! try login in");
			return "redirect:/";
		}

		model.addAttribute("passwordForm", new PasswordSetupForm());
		model.addAttribute("codeId", code);
		return "user/setup/password";
	}

	@RequestMapping(value = "/processpassword/{code}", method = RequestMethod.POST)
	public String processPassword(
			@Valid @ModelAttribute("passwordForm") PasswordSetupForm passwordSetupForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, @PathVariable("code") String code) {

		/*
		 * Standard checks starts
		 */

		if (code.isEmpty())
			return "user/setup/error";

		// check if the user activation is available.
		UserActivation userActivation = userActivationBo
				.getUserActivation(code);

		if (userActivation == null)
			return "user/setup/error";

		if (userActivation.isUsed() && !userActivation.isExpired()) {
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"Your account is active! Try login in");
			return "redirect:/";
		}

		if (result.hasErrors()) {
			model.addAttribute("codeId", code);
			return "user/setup/password";
		}

		/*
		 * Standard checks end
		 */

		if (!passwordSetupForm.getPassword().equals(
				passwordSetupForm.getConfirmPassword())) {
			System.out.println("Password checked false");
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"passwords field not identical");
			return "redirect:/user/setup/password/" + code;
		}

		Date timeStamp = new Date(System.currentTimeMillis());

		User user = userActivation.getUser();
		user.setPassword(passwordSetupForm.getPassword());
		user.setModifiedDate(timeStamp);
		user.setStatus(true);

		// userActivation.setUsed(true);
		userActivation.setModifiedDate(timeStamp);
		System.out.println("User updated");
		userBo.update(user);
		userActivationBo.update(userActivation);

		// TODO: Insert Password here

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Password Set. Enter questions");

		return "redirect:/user/setup/securityquestions/" + code;
	}

	@RequestMapping(value = "/securityquestions/{code}", method = RequestMethod.GET)
	public String setSecurityQuestion(@PathVariable("code") String code,
			Model model, RedirectAttributes redirectAttributes) {

		if (code.isEmpty())
			return "user/setup/error";

		// check if the user activation is available.
		UserActivation userActivation = userActivationBo
				.getUserActivation(code);
		if (userActivation == null)
			return "user/setup/error";

		if (userActivation.isUsed() == true) {
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"Your account is active! try login in");
			return "redirect:/";
		}

		model.addAttribute("codeId", code);
		model.addAttribute("questionsForm", new UserQuestionsForm());

		List<SecurityQuestion> list = securityQuestionBo.fetchAll();
		List<SecurityQuestion> question1 = new ArrayList<SecurityQuestion>();
		List<SecurityQuestion> question2 = new ArrayList<SecurityQuestion>();
		List<SecurityQuestion> question3 = new ArrayList<SecurityQuestion>();
		List<SecurityQuestion> question4 = new ArrayList<SecurityQuestion>();
		int i = 1;
		for (SecurityQuestion s : list) {
			if (i <= 10) {
				question1.add(s);
			}

			if (i <= 20 && i > 10) {
				question2.add(s);
			}

			//
			if (i <= 30 && i > 20) {
				question3.add(s);
			}
			if (i <= 40 && i > 30) {
				question4.add(s);
			}

			i++;
		}
		model.addAttribute("question1", question1);
		model.addAttribute("question2", question2);
		model.addAttribute("question3", question3);
		model.addAttribute("question4", question4);

		return "user/setup/questions";
	}

	@RequestMapping(value = "/processquestions/{code}", method = RequestMethod.POST)
	public String processSecurityQuestions(
			@Valid @ModelAttribute("questionsForm") UserQuestionsForm userQuestionsForm,
			BindingResult result, @PathVariable("code") String code,
			RedirectAttributes redirectAttributes, Model model) {

		if (code.isEmpty())
			return "user/setup/error";

		// check if the user activation is available.
		UserActivation userActivation = userActivationBo
				.getUserActivation(code);
		if (userActivation == null)
			return "user/setup/error";

		if (result.hasErrors()) {
			model.addAttribute("codeId", code);
			model.addAttribute("securityQuestions",
					securityQuestionBo.fetchAll());
			return "user/setup/questions";
		}

		User user = userActivation.getUser();
		UserSecurityQuestions userSecurityQuestions = userSecurityQuestionsBo
				.getUserSecurityQuestionsByUserId(user.getUserId());

		// Tester for save or update
		boolean tester = false;
		// Incase it is null;
		if (userSecurityQuestions == null) {
			userSecurityQuestions = new UserSecurityQuestions();
			userSecurityQuestions.setUserId(user.getUserId());
			tester = true;
		}

		System.out.println(userQuestionsForm.getQuestionOne());

		SecurityQuestion securityQuestion = securityQuestionBo
				.getQuestionById(userQuestionsForm.getQuestionOne());

		userSecurityQuestions.setQuestionOne(securityQuestion);
		userSecurityQuestions.setAnswerOne(userQuestionsForm.getAnswerOne());

		// Set Question and Answer two
		securityQuestion = securityQuestionBo.getQuestionById(userQuestionsForm
				.getQuestionTwo());
		userSecurityQuestions.setQuestionTwo(securityQuestion);
		userSecurityQuestions.setAnswerTwo(userQuestionsForm.getAnswerTwo());

		// Set Question and Answer Three
		securityQuestion = securityQuestionBo.getQuestionById(userQuestionsForm
				.getQuestionThree());
		userSecurityQuestions.setQuestionThree(securityQuestion);
		userSecurityQuestions
				.setAnswerThree(userQuestionsForm.getAnswerThree());

		// Set Question and Answer Four
		securityQuestion = securityQuestionBo.getQuestionById(userQuestionsForm
				.getQuestionFour());
		userSecurityQuestions.setQuestionFour(securityQuestion);
		userSecurityQuestions.setAnswerFour(userQuestionsForm.getAnswerFour());

		// Should save or update row?
		if (tester)
			userSecurityQuestionsBo.save(userSecurityQuestions); // Add for new
																	// user
		else
			userSecurityQuestionsBo.update(userSecurityQuestions); // Update for
																	// old user

		// Unlock the user account
		user.setLock(false);
		userBo.update(user);
		userActivation.setUsed(true);
		userActivationBo.update(userActivation);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Security questions and answers set..");
		// At this point, Login the user
		return "redirect:/user/session/login";
	}
}
