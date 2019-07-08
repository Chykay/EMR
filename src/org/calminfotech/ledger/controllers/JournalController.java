package org.calminfotech.ledger.controllers;

import java.util.List;

import javax.validation.Valid;

import org.calminfotech.ledger.boInterface.JournalBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.forms.JournalForm;
import org.calminfotech.ledger.models.JournalEntry;
import org.calminfotech.ledger.models.JournalHeader;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.utility.LedgerException;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/ledger/journal")
public class JournalController {	
	@Autowired
	private OrganisationBo organisationBo;
	
	@Autowired
	private JournalBo journalBo;

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

	@Autowired
	private LedgerAccBo ledgerAccBo;

	
	/* GET ALL JOURNAL ENTRIES*/
	@RequestMapping(value = {"/index"}, method=RequestMethod.GET)
	public String index(Model model) {		
		
		List<JournalHeader> journalHeaders = null;
		try {
			journalHeaders = this.journalBo.fetchJournalHeaders();
		} catch (LedgerException e) {
			e.printStackTrace();
		}
		
		List<Organisation> branches = this.organisationBo.fetchAll(this.userIdentity.getOrganisation().getOrgCoy().getId());
		model.addAttribute("journalHeaders", journalHeaders);
		model.addAttribute("branches", branches);
		return "/ledger/gen_ledger/journal/index";
	}
	
	/* GET JOURNAL HEADER CREATION PAGE*/
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = {"/add"}, method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("journalHeader", new JournalHeader());
		return "/ledger/gen_ledger/journal/create";
	}
	
	/* GET ALL JOURNAL ENTRIES*/
	@RequestMapping(value = {"/add"}, method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("journalHeader") JournalHeader journal) {
		try {
			System.out.println("here");
			this.journalBo.saveHeader(journal);
		} catch (LedgerException e) {
			e.printStackTrace();
		}
		
		return "redirect:/ledger/journal/index";
	}

	
	@RequestMapping(value = {"/post/{id}"}, method=RequestMethod.GET)
	public String post(Model model, @PathVariable("id") String journalID) {
		
		try {
			this.journalBo.postJournal(journalID);
			} catch (LedgerException e) {
			e.printStackTrace();
		}
		
		return "redirect:/ledger/journal/index";
	}

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = {"/edit/{id}"}, method=RequestMethod.GET)
	public String edit(Model model, @PathVariable("id") String journalID) {
		JournalForm journalForm = new JournalForm();
		JournalHeader journalHeader = null;
		List<JournalEntry> journalEntries = null;
		
		try {
			journalHeader = this.journalBo.getJournalHeader(journalID);
			journalEntries = this.journalBo.getJournalEntriesByJournalID(journalID);
		} catch (LedgerException e) {
			e.printStackTrace();
		}
		
		journalForm.setJournalHeader(journalHeader);
		if (journalEntries == null) {
			System.out.println("null");
			model.addAttribute("journalEntries", new JournalEntry());
		} else {
			System.out.println("not null" + journalEntries.get(0).getAccountNo());
			
			model.addAttribute("journalEntries", journalEntries);
		}

		Organisation org = userIdentity.getOrganisation();
		List<LedgerAccount> ledgerAccounts = this.ledgerAccBo.fetchAll(org.getId(), org.getOrgCoy().getId());
		model.addAttribute("generalLedgers", ledgerAccounts);
		model.addAttribute("journalHeader", journalHeader);
		return "/ledger/gen_ledger/journal/edit";
	}
	
	
	@RequestMapping(value = {"/edit"}, method=RequestMethod.POST, consumes = "application/json")
	public String edit(@RequestBody Object journal) {
		try {
			Boolean value = this.journalBo.manageJournal(journal);
			
			
		} catch (LedgerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("oya redirect oo");
		
		if(true)
			return "redirect:/ledger/journal/index";
		else
			return "redirect:/ledger/journal/index";
	}
	
}

