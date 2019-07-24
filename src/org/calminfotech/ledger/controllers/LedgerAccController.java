package org.calminfotech.ledger.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.boInterface.LedgerCatBo;
import org.calminfotech.ledger.boInterface.LedgerTotallingBo;
import org.calminfotech.ledger.forms.LedgerAccForm;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.models.LedgerCategory;
import org.calminfotech.ledger.models.TotalingCode;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
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
@RequestMapping(value = "/ledger/ledger_acc")
public class LedgerAccController {
	@Autowired
	private LedgerAccBo ledgerAccBo;
	
	@Autowired
	private LedgerCatBo ledgerCatBo;

	@Autowired
	private LedgerTotallingBo ledgerTotallingBo;
	
	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Auditor auditor;
	
	@RequestMapping(value = {"/index"}, method=RequestMethod.GET)
	public String indexGenLedgert(Model model) {
		Organisation org = userIdentity.getOrganisation();
		System.out.println(org.getId() + " : " + org.getOrgCoy().getId());
		
		if (this.ledgerAccBo.fetchAll(org.getOrgCoy().getId()) != null) {
			List<LedgerAccount> ledgerAccounts = this.ledgerAccBo.fetchAll(org.getOrgCoy().getId());
			
			for (LedgerAccount ledgerAccount : ledgerAccounts) {
				ledgerAccount.setEditable(!this.ledgerAccBo.isUsed(ledgerAccount.getAccountNo()));
			}
			model.addAttribute("accounts", ledgerAccounts);
		} 
		
		return "/ledger/ledger_acc/index";
	}
	
	/* SHOW ALL 
	@RequestMapping(value = {"/view/{id}"}, method=RequestMethod.GET)
	public String show(Model model, @PathVariable int id) {
		LedgerAccount genLedger = this.ledgerAccBo.getLedgerById(id);
		model.addAttribute("account", genLedger);
		return "/ledger/ledger_acc/show";
	}
	*/
	/* CREATE */
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = {"/create"}, method=RequestMethod.GET)
	public String create(Model model) {		
		
		List<TotalingCode> totalingCodes = this.ledgerTotallingBo.fetchAllActive();
		List<LedgerCategory> ledgerCats = this.ledgerCatBo.fetchAll();
		
		model.addAttribute("ledgerAccForm", new LedgerAccForm());
		model.addAttribute("ledgerCats", ledgerCats);
		model.addAttribute("totalingCodes", totalingCodes);
		return "/ledger/ledger_acc/create";
	}
	
	
	@RequestMapping(value = {"/create"}, method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("ledgerAccForm") LedgerAccForm ledgerAccForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		LedgerAccount account = new LedgerAccount();
		try {
			account = this.ledgerAccBo.save(ledgerAccForm);
		} catch (Exception e) {
			
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Failure! Account Number '" + ledgerAccForm.getAccountNo() + "' Already Exists for this company  ");
			return "redirect:/ledger/ledger_acc/index";
		}
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! New GeneralLedger Succesfully Added! GeneralLedger id:  "
						+ account.getId());

		model.addAttribute("account", account);
		return "redirect:/ledger/ledger_acc/index";
	}
	
	
	
	/* UPDATE */
	@RequestMapping(value = {"/edit/{id}"}, method=RequestMethod.GET)
	public String update(Model model, @PathVariable int id, HttpServletRequest request) {
		if (this.ledgerAccBo.isUsed(this.ledgerAccBo.getLedgerById(id).getAccountNo())) {
			return "redirect:/ledger/ledger_acc/index";
		}
		
		LedgerAccount genLedger = this.ledgerAccBo.getLedgerById(id);
		List<TotalingCode> totalingCodes = this.ledgerTotallingBo.fetchAllActive();
		List<LedgerCategory> ledgerCats = this.ledgerCatBo.fetchAll();
		
		LedgerAccForm ledgerAccForm = new LedgerAccForm();
		ledgerAccForm.setTotalingCode(genLedger.getTotalingCode());
		ledgerAccForm.setName(genLedger.getName());
		ledgerAccForm.setCode(genLedger.getCode());
		ledgerAccForm.setAccountNo(genLedger.getAccountNo());
		if (genLedger.getIsActive()) {
			ledgerAccForm.setIsActive(1);
		} else {
			ledgerAccForm.setIsActive(0);
		}

		ledgerAccForm.setLedgerCatID(genLedger.getLedgerCatID());

		model.addAttribute("ledgerCats", ledgerCats);
		model.addAttribute("ledgerAccForm", ledgerAccForm);
		model.addAttribute("totalingCodes", totalingCodes);
		this.auditor.before(request, "GenLedgerForm", ledgerAccForm);
		return "/ledger/ledger_acc/edit";
	}
	
	@RequestMapping(value = {"/edit/{id}"}, method=RequestMethod.POST)
	public String update(@Valid @ModelAttribute("ledgerAccForm") LedgerAccForm ledgerAccForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes, @PathVariable int id, HttpServletRequest request) {

		System.out.println(ledgerAccForm.getTotalingCode() + "kolade");
		System.out.println(ledgerAccForm.getAccountNo() + "kolade");
		try{
		LedgerAccount genLedger = this.ledgerAccBo.update(ledgerAccForm, id);
		this.auditor.after(request, "GenLedgerForm", ledgerAccForm,
				this.userIdentity.getUsername(), id);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! GeneralLedger Succesfully Edited! GeneralLedger id:  "
						+ id);

		model.addAttribute("account", genLedger);
		} catch(Exception e) {
			e.printStackTrace();
			alert.setAlert(redirectAttributes, Alert.DANGER,
					" Failed! FAILED Editing! GeneralLedger id:  " + id + " Error: " + e.getMessage()
							);
			
		}

		return "redirect:/ledger/ledger_acc/index";
	}
	
	@RequestMapping(value = {"/status/{id}"}, method=RequestMethod.GET)
	public String updateStatus(Model model, @PathVariable int id, HttpServletRequest request) {
		
		LedgerAccount ledgerAccount = this.ledgerAccBo.getLedgerById(id);
		this.auditor.before(request, "LedgerAccount", ledgerAccount);

		this.ledgerAccBo.updateStatus(ledgerAccount);
		
		this.auditor.after(request, "LedgerAccount", ledgerAccount,
				this.userIdentity.getUsername(), id);
		return "redirect:/ledger/ledger_acc/index";
	}
	
	
}
