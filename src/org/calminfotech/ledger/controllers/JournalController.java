package org.calminfotech.ledger.controllers;

import java.util.List;

import javax.validation.Valid;

import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.forms.JournalForm;
import org.calminfotech.ledger.models.JournalEntry;
import org.calminfotech.ledger.utiility.LedgerException;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/ledger/gen_ledger")
public class JournalController {	
	@Autowired
	private OrganisationBo organisationBo;
	
	@Autowired
	private GenLedgerBo genLedgerBo;

	/*	
	
	@Autowired
	private PostCodeDaoImpl postCodeDaoImpl;

	@Autowired
	private Alert alert;

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private BalSheetCatBo balSheetCatBo;

	@Autowired
	private TotAccBo totAccBo;
	*/

	@Autowired
	private UserIdentity userIdentity;

	
	/* GET ALL JOURNAL ENTRIES*/
	@RequestMapping(value = {"/journal/index"}, method=RequestMethod.GET)
	public String index(Model model) {		
		
		List<JournalEntry> journalEntries = null;
		try {
			journalEntries = this.genLedgerBo.getJournalEntries();
		} catch (LedgerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Organisation> branches = this.organisationBo.fetchAll(this.userIdentity.getOrganisation().getOrgCoy().getId());
		model.addAttribute("journalEntries", journalEntries);
		model.addAttribute("journal", new JournalForm());
		model.addAttribute("branches", branches);
		return "/ledger/gen_ledger/journal/index";
	}
	
	/* GET ALL JOURNAL ENTRIES*/

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = {"/journal/add"}, method=RequestMethod.GET)
	public String add(Model model) {		
		return "/ledger/gen_ledger/journal/create";
	}
	
	/* GET ALL JOURNAL ENTRIES*/
	@RequestMapping(value = {"/journal/add"}, method=RequestMethod.POST)
	public String add(Model model, @Valid @ModelAttribute("journal") JournalForm journalForm) {		
		
		System.out.println(journalForm.getAccount_no() + " : " + journalForm.getPost_code());
		return "redirect:/ledger/gen_ledger/journal/index";
	}
	

}

