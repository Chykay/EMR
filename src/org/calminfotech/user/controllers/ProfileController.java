package org.calminfotech.user.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.calminfotech.system.boInterface.TitleBo;
import org.calminfotech.system.models.Gender;
import org.calminfotech.system.models.Title;
import org.calminfotech.user.boInterface.SecurityQuestionBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.boInterface.UserProfileBo;
import org.calminfotech.user.boInterface.UserSecurityQuestionsBo;
import org.calminfotech.user.forms.PasswordForm;
import org.calminfotech.user.forms.ProfileForm;
import org.calminfotech.user.forms.UserImageForm;
import org.calminfotech.user.forms.UserQuestionsForm;
import org.calminfotech.user.models.SecurityQuestion;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserProfile;
import org.calminfotech.user.models.UserSecurityQuestions;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Encryptor;
import org.calminfotech.utils.GenderList;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/user/profile")
public class ProfileController {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private UserBo userBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Encryptor  encryptor;
	
	@Autowired
	private UserProfileBo userProfileBo;

	@Autowired
	private GenderList genderList;

	@Autowired
	private TitleBo titleBo;

	@Autowired
	private SecurityQuestionBo securityQuestionBo;

	@Autowired
	private UserSecurityQuestionsBo userSecurityQuestionsBo;

	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
		User user = userBo.getUserById(userIdentity.getUserId());
		model.addAttribute("user", user);
		model.addAttribute("imageForm", new UserImageForm());
		
		return "user/profile/index";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model) {
		User user = userBo.getUserById(userIdentity.getUserId());

		ProfileForm pForm = new ProfileForm();
		pForm.setUserId(user.getUserId());
		pForm.setLastName(user.getUserProfile().getLastName());
		pForm.setOtherNames(user.getUserProfile().getOtherNames());
	//	pForm.setTitleId(user.getUserProfile().getTitle().getId());

		if (user.getUserProfile().getGender() != null)
			pForm.setGenderId(user.getUserProfile().getGender().getId());

		pForm.setAddress(user.getUserProfile().getAddress());

		model.addAttribute("user", user);
		model.addAttribute("profileForm", pForm);
		model.addAttribute("genders", genderList.fetchAll());
	//	model.addAttribute("titles", titleBo.fetchAllByOrganisation());

		return "user/profile/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(
			@Valid @ModelAttribute("profileForm") ProfileForm profileForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		// Get the user's id
		User user = userBo.getUserById(userIdentity.getUserId());

		if (result.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("genders", genderList.fetchAll());
			//model.addAttribute("titles", titleBo.fetchAllByOrganisation());
			return "user/profile/edit";
		}


		UserProfile userProfile = user.getUserProfile();
		userProfile.setLastName(profileForm.getLastName());
		userProfile.setOtherNames(profileForm.getOtherNames());
		userProfile.setAddress(profileForm.getAddress());

		//Title title = this.titleBo.getTitleById(profileForm.getTitleId());
	//	userProfile.setTitle(title);

		Gender gender = genderList.getGenderById(profileForm.getGenderId());
		userProfile.setGender(gender);
		userProfile.setModifiedDate(new Date(System.currentTimeMillis()));

		user.setUserProfile(userProfile);
		userBo.update(user);

		alert.setAlert(redirectAttributes, Alert.SUCCESS, "Profile Updated!");

		return "redirect:/user/profile";
	}

	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String setPassword(Model model) {

		User user = userBo.getUserById(userIdentity.getUserId());

		PasswordForm pForm = new PasswordForm();
		model.addAttribute("passwordForm", pForm);
		return "user/profile/password";
	}

	@RequestMapping(value = "/processpassword", method = RequestMethod.POST)
	public String processPassword(
			@Valid @ModelAttribute("passwordForm") PasswordForm passwordForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		if (result.hasErrors()) {
			return "user/profile/password";
		}

		if (!passwordForm.getPassword().equals(
				passwordForm.getConfirmPassword())) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Please ensure the new password and confirmation password are the same.");
			return "redirect:/user/profile/password";
		}

		User user = userBo.getUserById(userIdentity.getUserId());

		if (!user.getPassword().equals(encryptor.encrypt(passwordForm.getFormerPassword()))) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid Former password. Please put the correct password");
			return "redirect:/user/profile/password";
		}

		user.setPassword(encryptor.encrypt(passwordForm.getPassword()));
		user.setModifiedDate(new Date(System.currentTimeMillis()));
		userBo.update(user);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Password changed successfully");

		return "redirect:/user/profile";
	}

	@RequestMapping(value = "/questions", method = RequestMethod.GET)
	public String setSecurityQuestion(Model model) {

		User user = userBo.getUserById(userIdentity.getUserId());
		UserSecurityQuestions userSecurityQuestions = userSecurityQuestionsBo
				.getUserSecurityQuestionsByUserId(user.getUserId());
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

		return "/user/profile/questions";
	}

	@RequestMapping(value = "/processquestions", method = RequestMethod.POST)
	public String processSecurityQuestions(
			@Valid @ModelAttribute("questionsForm") UserQuestionsForm userQuestionsForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("questions", securityQuestionBo.fetchAll());
			return "user/profile/questions";
		}

		User user = userBo.getUserById(userIdentity.getUserId());
		UserSecurityQuestions userSecurityQuestions = userSecurityQuestionsBo
				.getUserSecurityQuestionsByUserId(user.getUserId());

		SecurityQuestion securityQuestion = securityQuestionBo
				.getQuestionById(userQuestionsForm.getQuestionOne());

		userSecurityQuestions.setQuestionOne(securityQuestion);
		userSecurityQuestions.setAnswerOne(encryptor.encrypt(userQuestionsForm.getAnswerOne()));

		// Set Question and Answer two
		securityQuestion = securityQuestionBo.getQuestionById(userQuestionsForm
				.getQuestionTwo());
		userSecurityQuestions.setQuestionTwo(securityQuestion);
		userSecurityQuestions.setAnswerTwo(encryptor.encrypt(userQuestionsForm.getAnswerTwo()));

		// Set Question and Answer Three
		securityQuestion = securityQuestionBo.getQuestionById(userQuestionsForm
				.getQuestionThree());
		userSecurityQuestions.setQuestionThree(securityQuestion);
		userSecurityQuestions
				.setAnswerThree(encryptor.encrypt(userQuestionsForm.getAnswerThree()));

		// Set Question and Answer Four
		securityQuestion = securityQuestionBo.getQuestionById(userQuestionsForm
				.getQuestionFour());
		userSecurityQuestions.setQuestionFour(securityQuestion);
		userSecurityQuestions.setAnswerFour(encryptor.encrypt(userQuestionsForm.getAnswerFour()));

		
			userSecurityQuestionsBo.update(userSecurityQuestions); // Update for
		
		// Unlock the user account
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Security questions and answers set..");
		// At this point, Login the user

		
		
		
		
		
		
		return "redirect:/user/profile";
	}

	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public String processImage(
			@ModelAttribute("imageForm") UserImageForm imageForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		
		
		if(imageForm.getImageFile().getSize() > 150000)
			
		{

alert.setAlert(redirectAttributes, Alert.SUCCESS,
			"Image size cannot be more than 150 KB");
	
return "redirect:/user/profile";
	
}


if(!imageForm.getImageFile().getContentType().toString().toLowerCase().equals("image/jpeg")
	
	&& !imageForm.getImageFile().getContentType().toString().toLowerCase().equals("image/png"))
{

	alert.setAlert(redirectAttributes, Alert.SUCCESS,
			"Image type should be jpg or png");
	
	return "redirect:/user/profile";
	
}

		UserProfile userProfile = userProfileBo
				.getUserProfileByUserId(userIdentity.getUserId());

		if (null == userProfile) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Could not upload image");
			return "redirect:/user/profile";
		}

		try {
			@SuppressWarnings("deprecation")
			Blob blob = Hibernate.createBlob(imageForm.getImageFile().getInputStream());
			userProfile.setProfileImage(blob);

			String contentType = imageForm.getImageFile().getContentType();
			userProfile.setImageContentType(contentType);
			userProfile.setModifiedDate(new Date(System.currentTimeMillis()));

			userProfileBo.update(userProfile);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Image Uploaded successfully");

		} catch (IOException e) {
			e.printStackTrace();
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Image Upload failed");
		}

		return "redirect:/user/profile";
	}

	@ResponseBody
	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public String viewImage(HttpServletResponse response) {
		UserProfile userProfile = userProfileBo
				.getUserProfileByUserId(userIdentity.getUserId());
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
}
