package org.calminfotech.ledger.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.ledger.boInterface.BalSheetCatBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.boInterface.TotAccBo;
import org.calminfotech.ledger.forms.LedgerAccForm;
import org.calminfotech.ledger.models.BalSheetCat;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.models.TotalingAccount;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
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
@RequestMapping(value = "/ledger/ledger_acc")
public class LedgerAccController {
	@Autowired
	private LedgerAccBo ledgerAccBo;
	
	@Autowired
	private BalSheetCatBo balSheetCatBo;

	@Autowired
	private TotAccBo totAccBo;
	
	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Auditor auditor;
	
	@RequestMapping(value = {"/index"}, method=RequestMethod.GET)
	public String indexGenLedgert(Model model) {
		Organisation org = userIdentity.getOrganisation();
		List<LedgerAccount> ledgerAccounts = this.ledgerAccBo.fetchAll(org.getId(), org.getOrgCoy().getId());
		model.addAttribute("accounts", ledgerAccounts);
		// model.addAttribute("id", id);
		return "/ledger/ledger_acc/index";
	}
	
	/* SHOW ALL */
	@RequestMapping(value = {"/view/{id}"}, method=RequestMethod.GET)
	public String show(Model model, @PathVariable int id) {
		LedgerAccount genLedger = this.ledgerAccBo.getLedgerById(id);
		model.addAttribute("account", genLedger);
		return "/ledger/ledger_acc/show";
	}
	
	/* CREATE */

	@RequestMapping(value = {"/create"}, method=RequestMethod.GET)
	public String create(Model model) {		
		
		List<TotalingAccount> totalingAccounts = this.totAccBo.fetchAll();
		List<BalSheetCat> balSheetCats = this.balSheetCatBo.fetchAll();
		
		model.addAttribute("account", new LedgerAccForm());
		model.addAttribute("balSheetCats", balSheetCats);
		model.addAttribute("totalingAccounts", totalingAccounts);
		return "/ledger/ledger_acc/create";
	}
	
	
	@RequestMapping(value = {"/create"}, method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("account") LedgerAccForm ledgerAccForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
				
		LedgerAccount account = this.ledgerAccBo.save(ledgerAccForm);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! New GeneralLedger Succesfully Added! GeneralLedger id:  "
						+ account.getId());

		model.addAttribute("account", account);
		return "redirect:/ledger/ledger_acc/index";
	}
	
	
	
	/* UPDATE */
	@RequestMapping(value = {"/edit/{id}"}, method=RequestMethod.GET)
	public String update(Model model, @PathVariable int id, HttpServletRequest request) {
		LedgerAccount genLedger = this.ledgerAccBo.getLedgerById(id);
		
		LedgerAccForm ledgerAccForm = new LedgerAccForm();
		ledgerAccForm.setName(genLedger.getName());
		ledgerAccForm.setCode(genLedger.getCode());
		ledgerAccForm.setAccount_no(genLedger.getAccount_no());
		if (genLedger.getIs_active()) {
			ledgerAccForm.setIs_active(1);
		} else {
			ledgerAccForm.setIs_active(0);
		}

		model.addAttribute("account", ledgerAccForm);
		
		this.auditor.before(request, "GenLedgerForm", ledgerAccForm);
		return "/ledger/ledger_acc/edit";
	}
	
	@RequestMapping(value = {"/edit/{id}"}, method=RequestMethod.POST)
	public String update(@Valid @ModelAttribute("account") LedgerAccForm ledgerAccForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes, @PathVariable int id, HttpServletRequest request) {
				
		try{

		LedgerAccount genLedger = this.ledgerAccBo.update(ledgerAccForm, id);
		this.auditor.after(request, "GenLedgerForm", ledgerAccForm,
				this.userIdentity.getUsername(), id);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! GeneralLedger Succesfully Edited! GeneralLedger id:  "
						+ id);

		model.addAttribute("account", genLedger);
		} catch(Exception e) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					" Failed! FAILED Editing! GeneralLedger id:  " + id + " Error: " + e.getMessage()
							);
			
		}

		return "redirect:/ledger/ledger_acc/view/" + id;
	}
	
		
	/* DELETE */
	@RequestMapping(value={"/delete/{id}"}, method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		LedgerAccount genLedger = this.ledgerAccBo.getLedgerById(id);
		
		this.ledgerAccBo.delete(genLedger);
		return "redirect:/ledger/ledger_acc/index";
	}
}
