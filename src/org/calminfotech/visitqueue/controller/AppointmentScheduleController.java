package org.calminfotech.visitqueue.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.system.boInterface.LanguageBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserProfile;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.visitqueue.boInterface.AppointmentScheduleBo;
import org.calminfotech.visitqueue.boInterface.VisitStatusBo;
import org.calminfotech.visitqueue.forms.AppointmentScheduleForm;
import org.calminfotech.visitqueue.forms.PrescribedSearchForm;
import org.calminfotech.visitqueue.models.AppointmentSchedule;
import org.calminfotech.visitqueue.models.VisitStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/appointment")
public class AppointmentScheduleController {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Alert alert;

	@Autowired
	private AppointmentScheduleBo appBo;

	@Autowired
	private LanguageBo languageBo;

	@Autowired
	private UserBo userBo;

	@Autowired
	private VisitStatusBo visitStatusBo;

	@Autowired
	private PatientBo patientBo;

	@Layout("layouts/calendar_layout")
	@RequestMapping(value = { "", "/index" })
	public String indexAction(Model model) {
		System.out.println("At the appointment list now.....yipeeeeeeeeeeeee");
		Date f = new GregorianCalendar().getTime();

		Calendar cal = GregorianCalendar.getInstance();

		// cal.add( Calendar.DAY_OF_YEAR, -30);

		Date dat1 = cal.getTime();

		// cal.add( Calendar.DAY_OF_YEAR, +60);

		Date dat2 = cal.getTime();

		List<User> userlist = this.userBo.fetchAll();

		System.out.print("dat1" + dat1);
		System.out.print("dat2" + dat2);

		// List<AppointmentSchedule> appointlist =
		// this.appBo.fetchallbydateorg(dat1, dat2,
		// userIdentity.getOrganisation().getId());

		List<AppointmentSchedule> appointlist = this.appBo
				.fetchallbytop100org(userIdentity.getOrganisation().getId());

		System.out.print("userlist" + userlist.size());
		System.out.print("appointlist" + appointlist.size());
		String msg = "";
		UserProfile p = userIdentity.getUserProfile();
		msg = "<option value='" + p.getUserId() + "'>" + p.getLastName() + " "
				+ p.getOtherNames() + "</option>";

		System.out.print("msg1" + msg);

		for (User u : userlist) {

			msg = msg + "<option value='" + u.getUserProfile().getUserId()
					+ "'>" + u.getUserProfile().getLastName() + ' '
					+ u.getUserProfile().getOtherNames() + "</option>";

			// msg += "<option value='" + u.getUserId() + "'>" +
			// u.getUserProfile() != null ? u.getUserProfile().getLastName() :
			// "" + " " + u.getUserProfile() != null ?
			// u.getUserProfile().getOtherNames() : "" + "</option>";

			// msg += "<option value='" + u.getUserId() + "'>" +
			// u.getUserProfile() != null ? u.getUserProfile().getLastName() :
			// "" + " " + u.getUserProfile() != null ?
			// u.getUserProfile().getOtherNames() : "" + "</option>";

			System.out.print("msg" + msg);

		}

		System.out.print("msg2" + msg);

		// VisitWorkflowUserConfigurationForm vform = new
		// VisitWorkflowUserConfigurationForm();

		PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();

		List<VisitStatus> statuslist = this.visitStatusBo.fetchAll();

		model.addAttribute("prescribedSearch", prescribedSearch);
		// model.addAttribute("vform", vform);

		model.addAttribute("eventlist", appointlist);
		model.addAttribute("userlist", msg);
		model.addAttribute("userlistsearch", userlist);

		AppointmentScheduleForm appform = new AppointmentScheduleForm();

		model.addAttribute("appform", appform);
		model.addAttribute("statuslist", statuslist);

		return "appointment/index";
	}

	@Layout("layouts/calendar_layout")
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String indexsearchpost(
			Model model,
			@ModelAttribute("prescribedSearch") PrescribedSearchForm prescribedsearch,
			BindingResult result) {

		System.out.println("At the appointment list now.....postingg");

		Calendar cal = GregorianCalendar.getInstance();

		// cal.add( Calendar.DAY_OF_YEAR, -30);

		// Date dat1=cal.getTime();

		// cal.add( Calendar.DAY_OF_YEAR, +60);

		// Date dat2=cal.getTime();

		List<User> userlist = this.userBo.fetchAll();

		List<AppointmentSchedule> appointlist = null;

		Date dat1, dat2;

		if (prescribedsearch.getDat1().equals("")
				|| !DateUtils.isValidDate(prescribedsearch.getDat1())) {
			dat1 = DateUtils.formatStringToDate("1900-01-01");
		}

		else {
			dat1 = DateUtils.formatStringToDate(prescribedsearch.getDat1());

		}
		if (prescribedsearch.getDat2().equals("")
				|| !DateUtils.isValidDate(prescribedsearch.getDat2())) {
			dat2 = DateUtils.formatStringToDate("2100-01-01");
		} else {
			dat2 = DateUtils.formatStringToDate(prescribedsearch.getDat2());

		}

		if (prescribedsearch.getPatientId() == null) {
			prescribedsearch.setPatientId(0);

		}

		System.out.print("patient" + prescribedsearch.getPatientId());
		System.out.print("User " + prescribedsearch.getUserId());

		if (prescribedsearch.getPatientId().intValue() == 0
				&& prescribedsearch.getUserId().intValue() == 0) {
			System.out.print("%%patient zero user zero");

			appointlist = this.appBo.fetchallbydateorg(dat1, dat2, userIdentity
					.getOrganisation().getId());
		}

		if (prescribedsearch.getPatientId().intValue() != 0
				&& prescribedsearch.getUserId().intValue() != 0)

		{
			System.out.print("%%patient not zero user not zero");

			appointlist = this.appBo.fetchallbydateuserpatientorg(dat1, dat2,
					prescribedsearch.getUserId(), prescribedsearch
							.getPatientId(), userIdentity.getOrganisation()
							.getId());
		}

		if (prescribedsearch.getPatientId().intValue() != 0
				&& prescribedsearch.getUserId().intValue() == 0)

		{
			System.out.print("%%patient not zero user  zero");
			appointlist = this.appBo.fetchallbydatepatientorg(dat1, dat2,
					prescribedsearch.getPatientId(), userIdentity
							.getOrganisation().getId());
		}

		if (prescribedsearch.getPatientId().intValue() == 0
				&& prescribedsearch.getUserId().intValue() != 0) {
			System.out.print("%%patient  zero user not zero");
			appointlist = this.appBo.fetchallbydateuserorg(dat1, dat2,
					prescribedsearch.getUserId(), userIdentity
							.getOrganisation().getId());
		}

		System.out.print("userlist" + userlist.size());
		System.out.print("appointlist" + appointlist.size());
		String msg = "";
		UserProfile p = userIdentity.getUserProfile();
		msg = "<option value='" + p.getUserId() + "'>" + p.getLastName() + " "
				+ p.getOtherNames() + "</option>";
		for (User u : userlist) {
			msg = msg + "<option value='" + u.getUserId() + "'>"
					+ u.getUserProfile().getLastName() + " "
					+ u.getUserProfile().getOtherNames() + "</option>";

		}

		// VisitWorkflowUserConfigurationForm vform = new
		// VisitWorkflowUserConfigurationForm();

		PrescribedSearchForm prescribedSearchM = new PrescribedSearchForm();

		prescribedSearchM.setDat1(prescribedsearch.getDat1());
		prescribedSearchM.setDat2(prescribedsearch.getDat2());
		prescribedSearchM.setUserId(prescribedsearch.getUserId().intValue());
		prescribedSearchM.setPatientId(prescribedsearch.getPatientId());
		prescribedSearchM.setPatientName(prescribedsearch.getPatientName());

		List<VisitStatus> statuslist = this.visitStatusBo.fetchAll();

		model.addAttribute("prescribedSearch", prescribedSearchM);
		// model.addAttribute("vform", vform);

		model.addAttribute("eventlist", appointlist);

		model.addAttribute("userlist", msg);
		model.addAttribute("userlistsearch", userlist);

		AppointmentScheduleForm appform = new AppointmentScheduleForm();

		model.addAttribute("appform", appform);
		model.addAttribute("statuslist", statuslist);

		return "appointment/index";
	}

	@Layout("layouts/calendar_layout")
	@RequestMapping(value = { "/calendar" })
	public String indexActionsearch(Model model) {

		System.out.println("At the appointment list now.....yipeeeeeeeeeeeee");
		Date f = new GregorianCalendar().getTime();

		Calendar cal = GregorianCalendar.getInstance();

		// cal.add( Calendar.DAY_OF_YEAR, -30);

		// Date dat1=cal.getTime();

		// cal.add( Calendar.DAY_OF_YEAR, +60);

		// Date dat2=cal.getTime();

		List<User> userlist = this.userBo.fetchAll();

		// System.out.print("dat1" + dat1);
		// System.out.print("dat2" + dat2);

		// List<AppointmentSchedule> appointlist =
		// this.appBo.fetchallbydateorg(dat1, dat2,
		// userIdentity.getOrganisation().getId());

		List<AppointmentSchedule> appointlist = this.appBo
				.fetchallbytop100org(userIdentity.getOrganisation().getId());

		System.out.print("userlist" + userlist.size());
		System.out.print("appointlist" + appointlist.size());
		String msg = "";
		UserProfile p = userIdentity.getUserProfile();
		msg = "<option value='" + p.getUserId() + "'>" + p.getLastName() + " "
				+ p.getOtherNames() + "</option>";

		System.out.print("msg1" + msg);

		for (User u : userlist) {

			msg = msg + "<option value='" + u.getUserProfile().getUserId()
					+ "'>" + u.getUserProfile().getLastName() + ' '
					+ u.getUserProfile().getOtherNames() + "</option>";

			// msg += "<option value='" + u.getUserId() + "'>" +
			// u.getUserProfile() != null ? u.getUserProfile().getLastName() :
			// "" + " " + u.getUserProfile() != null ?
			// u.getUserProfile().getOtherNames() : "" + "</option>";

			// msg += "<option value='" + u.getUserId() + "'>" +
			// u.getUserProfile() != null ? u.getUserProfile().getLastName() :
			// "" + " " + u.getUserProfile() != null ?
			// u.getUserProfile().getOtherNames() : "" + "</option>";

			System.out.print("msg" + msg);

		}

		System.out.print("msg2" + msg);

		// JSON for calendar
		Gson gsonObj = new Gson();

		List<Map<Object, Object>> eventlist = new ArrayList<Map<Object, Object>>();

		// for pie chart 2

		for (AppointmentSchedule g : appointlist) {

			Map<Object, Object> appmap = new HashMap<Object, Object>();

			appmap.put("eventid", g.getEventId());
			appmap.put(
					"title",
					"Doctor: " + g.getUser().getUserProfile().getLastName()
							+ " "
							+ g.getUser().getUserProfile().getOtherNames()
							+ " Patient: " + g.getPatient().getSurname() + " "
							+ g.getPatient().getFirstName() + "Details: "
							+ g.getEventTitle());
			appmap.put("start", g.getStartDay());
			appmap.put("end", g.getEndDay());
			appmap.put("allDay", g.getAllday());
			// appmap.put("allDay",false);
			eventlist.add(appmap);

		}

		String dataPoints = (String) gsonObj.toJson(eventlist);

		// VisitWorkflowUserConfigurationForm vform = new
		// VisitWorkflowUserConfigurationForm();

		PrescribedSearchForm prescribedSearch = new PrescribedSearchForm();

		List<VisitStatus> statuslist = this.visitStatusBo.fetchAll();

		model.addAttribute("prescribedSearch", prescribedSearch);
		// model.addAttribute("vform", vform);

		model.addAttribute("eventlist", dataPoints.toString());
		model.addAttribute("userlist", msg);
		model.addAttribute("userlistsearch", userlist);

		AppointmentScheduleForm appform = new AppointmentScheduleForm();

		model.addAttribute("appform", appform);
		model.addAttribute("statuslist", statuslist);

		return "appointment/calendar";
	}

	@Layout("layouts/calendar_layout")
	@RequestMapping(value = "/calendar", method = RequestMethod.POST)
	public String indexActionsearchpost(
			Model model,
			@ModelAttribute("prescribedSearch") PrescribedSearchForm prescribedsearch,
			BindingResult result) {

		System.out.println("At the appointment lisat now.....postingg");

		Calendar cal = GregorianCalendar.getInstance();

		// cal.add( Calendar.DAY_OF_YEAR, -30);

		// Date dat1=cal.getTime();

		// cal.add( Calendar.DAY_OF_YEAR, +60);

		// Date dat2=cal.getTime();

		List<User> userlist = this.userBo.fetchAll();

		List<AppointmentSchedule> appointlist = null;

		Date dat1 = DateUtils.formatStringToDate(prescribedsearch.getDat1());

		Date dat2 = DateUtils.formatStringToDate(prescribedsearch.getDat2());

		if (prescribedsearch.getDat1().equals("")
				|| !DateUtils.isValidDate(prescribedsearch.getDat1())) {
			dat1 = DateUtils.formatStringToDate("1900-01-01");
		} else {
			dat1 = DateUtils.formatStringToDate(prescribedsearch.getDat1());

		}

		if (prescribedsearch.getDat2().equals("")
				|| !DateUtils.isValidDate(prescribedsearch.getDat2())) {
			dat2 = DateUtils.formatStringToDate("2100-01-01");
		} else {
			dat2 = DateUtils.formatStringToDate(prescribedsearch.getDat2());

		}

		if (prescribedsearch.getPatientId() == null) {
			prescribedsearch.setPatientId(0);

		}

		System.out.print("patient" + prescribedsearch.getPatientId());
		System.out.print("User " + prescribedsearch.getUserId());

		if (prescribedsearch.getPatientId().intValue() == 0
				&& prescribedsearch.getUserId().intValue() == 0) {
			appointlist = this.appBo.fetchallbydateorg(dat1, dat2, userIdentity
					.getOrganisation().getId());
		}

		if (prescribedsearch.getPatientId().intValue() != 0
				&& prescribedsearch.getUserId().intValue() != 0)

		{
			appointlist = this.appBo.fetchallbydateuserpatientorg(dat1, dat2,
					prescribedsearch.getUserId(), prescribedsearch
							.getPatientId(), userIdentity.getOrganisation()
							.getId());
		}

		if (prescribedsearch.getPatientId().intValue() != 0
				&& prescribedsearch.getUserId().intValue() == 0)

		{
			appointlist = this.appBo.fetchallbydatepatientorg(dat1, dat2,
					prescribedsearch.getPatientId(), userIdentity
							.getOrganisation().getId());
		}

		if (prescribedsearch.getPatientId().intValue() == 0
				&& prescribedsearch.getUserId().intValue() != 0) {
			appointlist = this.appBo.fetchallbydateuserorg(dat1, dat2,
					prescribedsearch.getUserId(), userIdentity
							.getOrganisation().getId());
		}

		System.out.print("userlist" + userlist.size());
		System.out.print("appointlist" + appointlist.size());
		String msg = "";
		UserProfile p = userIdentity.getUserProfile();
		msg = "<option value='" + p.getUserId() + "'>" + p.getLastName() + " "
				+ p.getOtherNames() + "</option>";
		for (User u : userlist) {
			msg += "<option value='" + u.getUserId() + "'>"
					+ u.getUserProfile().getLastName() + " "
					+ u.getUserProfile().getOtherNames() + "</option>";

		}

		// JSON for calendar
		Gson gsonObj = new Gson();

		List<Map<Object, Object>> eventlist = new ArrayList<Map<Object, Object>>();

		// for pie chart 2

		for (AppointmentSchedule g : appointlist) {

			Map<Object, Object> appmap = new HashMap<Object, Object>();

			appmap.put("eventid", g.getEventId());
			appmap.put(
					"title",
					"Doctor: " + g.getUser().getUserProfile().getLastName()
							+ " "
							+ g.getUser().getUserProfile().getOtherNames()
							+ " Patient: " + g.getPatient().getSurname() + " "
							+ g.getPatient().getFirstName() + " Details: "
							+ g.getEventTitle());
			appmap.put("start", g.getStartDay());
			appmap.put("end", g.getEndDay());
			appmap.put("allDay", g.getAllday());

			eventlist.add(appmap);

		}

		String dataPoints = (String) gsonObj.toJson(eventlist);

		// VisitWorkflowUserConfigurationForm vform = new
		// VisitWorkflowUserConfigurationForm();

		PrescribedSearchForm prescribedSearchM = new PrescribedSearchForm();

		prescribedSearchM.setDat1(prescribedsearch.getDat1());
		prescribedSearchM.setDat2(prescribedsearch.getDat2());
		prescribedSearchM.setUserId(prescribedsearch.getUserId().intValue());
		prescribedSearchM.setPatientId(prescribedsearch.getPatientId());
		prescribedSearchM.setPatientName(prescribedsearch.getPatientName());

		List<VisitStatus> statuslist = this.visitStatusBo.fetchAll();

		model.addAttribute("prescribedSearch", prescribedSearchM);
		// model.addAttribute("vform", vform);

		model.addAttribute("eventlist", dataPoints.toString());
		model.addAttribute("userlist", msg);
		model.addAttribute("userlistsearch", userlist);

		AppointmentScheduleForm appform = new AppointmentScheduleForm();

		model.addAttribute("appform", appform);
		model.addAttribute("statuslist", statuslist);

		return "appointment/calendar";
	}

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/add")
	public String addAction(Model model) {

		model.addAttribute("appForm", new AppointmentScheduleForm());

		return "appointment/schedule/add";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveappform(
			@Valid @ModelAttribute("appform") AppointmentScheduleForm appform,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws IOException {

		AppointmentSchedule app = new AppointmentSchedule();

		app.setEventTitle(appform.getTitle());
		app.setStartDay(DateUtils.formatStringToDate(appform.getStart_date2(),
				"yyyy-MM-dd hh:mm"));

		app.setEndDay(DateUtils.formatStringToDate(appform.getEnd_date2(),
				"yyyy-MM-dd hh:mm"));

		app.setUser(this.userIdentity.getUserProfile().getUser());

		app.setClassName("label-success");

		app.setPatient(this.patientBo.getPatientById(appform.getPatientId2()));

		app.setOrganisation(this.userIdentity.getOrganisation());
		app.setCreateDate(new Date(System.currentTimeMillis()));

		if (appform.getStart_date2().equals(appform.getEnd_date2()))

			app.setAllday(true);

		else
			app.setAllday(false);

		app.setCreatedBy(this.userIdentity.getUsername());
		app.setIsDeleted(0);

		// Used for saving
		this.appBo.save(app);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Appointment Succesfully Scheduled");

		return "redirect:/appointment/calendar";

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteappform(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("appform") AppointmentScheduleForm appform,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws IOException {

		System.out.print("oyee" + appform.getId());

		AppointmentSchedule app = this.appBo.getAppointmentScheduleById(id);

		app.setModifiedBy(this.userIdentity.getUsername());
		app.setModifiedDate(new Date(System.currentTimeMillis()));

		app.setIsDeleted(1);

		// Used for saving
		this.appBo.update(app);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Appointment Deleted Scheduled");

		return "redirect:/appointment/calendar";

	}

}
