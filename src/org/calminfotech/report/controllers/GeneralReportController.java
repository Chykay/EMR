package org.calminfotech.report.controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;
/*
import org.calminfotech.patient.forms.PatientreportsearchForm;*/
import org.calminfotech.report.models.ConsultationCount;
import org.calminfotech.report.models.PrescribedDrugReport;
import org.calminfotech.report.utils.ConsultationCountDao;
import org.calminfotech.report.utils.TopPrescribedDrug;
import org.calminfotech.user.utils.Authorize;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/generalreport")
public class GeneralReportController {

	@Autowired
	private Authorize authorize;

	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Auditor auditor;

	@Autowired
	private TopPrescribedDrug prescribedDrug;

	@Autowired
	private ConsultationCountDao consultationCountDao;

	@Layout("layouts/reportblank")
	@RequestMapping(value = "/prescribedlist", method = RequestMethod.GET)
	public String listAll(Model model) {

		List<PrescribedDrugReport> PRESdrug = this.prescribedDrug
				.fetchAllPrescribedDrugReport(userIdentity.getOrganisation()
						.getId());

		model.addAttribute("drug", PRESdrug);
		return "report/general/prescribeddrugs";
	}

	// this is to show invoice / bill list by due date form
	@SuppressWarnings({ "unused", "null" })
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/searchcount")
	public String searchcountteform(Model model,
			RedirectAttributes redirectAttributes) {
/*
		PatientreportsearchForm ctform = new PatientreportsearchForm();*/

		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -30);

		cal.add(Calendar.DAY_OF_YEAR, +30);
/*
		model.addAttribute("ctform", ctform);*/

		return "report/general/consultioncount";
	}

	// this is to show patient list by registration date result in table form
	@Layout(value = "layouts/reportblank")
	@RequestMapping(value = "/allcountprint/{datefrom}/{dateto}", method = RequestMethod.GET)
	public String printcount(@PathVariable("datefrom") String datefrom,
			@PathVariable("dateto") String dateto, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {

		/*
		 * System.out.println("My first date " +datefrom);
		 * System.out.println("My second  date " +dateto);
		 */
		List<ConsultationCount> cnsultationCount = this.consultationCountDao
				.fetchAllconsultationcount(DateUtils.formatStringToDate(
						datefrom, "yyyy-MM-dd hh:mm"), DateUtils
						.formatStringToDate(dateto, "yyyy-MM-dd hh:mm"));

		model.addAttribute("counttable", cnsultationCount);

		// for the header sake

		model.addAttribute("datefrom", datefrom);
		model.addAttribute("dateto", dateto);
		/*
		 * int r; if (r == 0) { }
		 */
		return "report/general/consultioncounprint";

	}

}
