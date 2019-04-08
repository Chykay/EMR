package org.calminfotech.ledger.controllers;

import java.util.List;

import org.calminfotech.ledger.boInterface.JournalEntryBo;
import org.calminfotech.ledger.models.JournalHeader;
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
	private JournalEntryBo journalEntryBo;

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
		
		List<JournalHeader> journalHeaders = null;
		try {
			journalHeaders = this.journalEntryBo.getJournalHeaders();
		} catch (LedgerException e) {
			e.printStackTrace();
		}
		
		List<Organisation> branches = this.organisationBo.fetchAll(this.userIdentity.getOrganisation().getOrgCoy().getId());
		model.addAttribute("journalHeaders", journalHeaders);
		model.addAttribute("branches", branches);
		return "/ledger/gen_ledger/journal/index";
	}
	
	/* GET ALL JOURNAL ENTRIES*/

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = {"/journal/add"}, method=RequestMethod.GET)
	public String add(Model model) {
		
		model.addAttribute("journalHeader", new JournalHeader());
		return "/ledger/gen_ledger/journal/create";
	}
	
	/* GET ALL JOURNAL ENTRIES*/
	@RequestMapping(value = {"/journal/add"}, method=RequestMethod.POST, consumes = "application/json")
	public String add(@ModelAttribute("journalHeader") JournalHeader journal) {
		try {
			this.journalEntryBo.saveHeader(journal);
		} catch (LedgerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/ledger/gen_ledger/journal/index";
	}
	

}

