package org.calminfotech.utils.controllers;

import java.util.List;

import org.calminfotech.patient.utils.PatientCodeGenerator;
import org.calminfotech.user.boInterface.RoleBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.boInterface.UserLoginSessionBo;
import org.calminfotech.user.models.User;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/api")
public class Api {

	@Autowired
	private UserBo userBo;

	@Autowired
	private UserLoginSessionBo userLoginSession;
	@Autowired
	private PatientCodeGenerator patientCodeGenerator;

	@Autowired
	private RoleBo roleBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Encryptor encryptor;

	@RequestMapping(value = "/checkaccess/{orgid}/{usr}/{pwd}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String checkaccess(@PathVariable("orgid") Integer orgid,
			@PathVariable("usr") String usr, @PathVariable("pwd") String pwd,
			Model model, RedirectAttributes redirectAttributes) {
		String str;

		System.out.print("apiusername" + usr);

		List<User> list = userBo
				.checkUserCredentialsByEmailAndPasswordWebAccess(usr,
						encryptor.encrypt(pwd), orgid);
		System.out.print("apilist" + list.size());
		// If the list is greater or less than 1. Should throw error.
		if (list.size() == 1) {
			str = "{\"status\":true}";

		}

		else {
			str = "{\"status\":false}";

		}

		return str;

	}

	@RequestMapping(value = "/generate/{orgid}/{dob}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String checkaccess(@PathVariable("orgid") Integer orgid,
			@PathVariable("dob") String dob, Model model,
			RedirectAttributes redirectAttributes) {
		String str;
		try {

			if (DateUtils.isValidDate(dob)) {

				str = patientCodeGenerator.processNumber(dob);
				if (str != null && str != "") {
					str = "{\"status\":true,\"code\":\"" + str + "\"}";

				}

				else {

					str = "{\"status\":false,\"code\":null}";

				}
			} else {
				str = "{\"status\":false,\"code\":null}";

			}

		} catch (Exception e) {
			str = "{\"status\":false,\"code\":null}";

		}

		return str;

	}

}
