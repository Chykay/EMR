package org.calminfotech.ledger.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.ledger.boInterface.BalSheetCatBo;
import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.TotAccBo;
import org.calminfotech.ledger.forms.GenLedgerForm;
import org.calminfotech.ledger.models.BalSheetCat;
import org.calminfotech.ledger.models.GeneralLedger;
import org.calminfotech.ledger.models.TotalingAccount;
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
@RequestMapping(value = "/ledger/gen_ledger")
public class GeneralLedgerController {
	@Autowired
	private GenLedgerBo genLedgerBo;
	
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
	
	
	/* SHOW ALL */
	@RequestMapping(value = {"/view/{id}"}, method=RequestMethod.GET)
	public String show(Model model, @PathVariable int id) {
		GeneralLedger genLedger = this.genLedgerBo.getLedgerById(id);
		model.addAttribute("account", genLedger);
		return "/ledger/gen_ledger/show";
	}
	
	/* CREATE */

	@RequestMapping(value = {"/create"}, method=RequestMethod.GET)
	public String create(Model model) {		
		
		List<TotalingAccount> totalingAccounts = this.totAccBo.fetchAll();
		List<BalSheetCat> balSheetCats = this.balSheetCatBo.fetchAll();
		
		model.addAttribute("account", new GenLedgerForm());
		model.addAttribute("balSheetCats", balSheetCats);
		model.addAttribute("totalingAccounts", totalingAccounts);
		return "/ledger/gen_ledger/create";
	}
	
	
	@RequestMapping(value = {"/create"}, method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("account") GenLedgerForm genLedgerForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
				
		GeneralLedger account = this.genLedgerBo.save(genLedgerForm);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! New GeneralLedger Succesfully Added! GeneralLedger id:  "
						+ account.getId());

		model.addAttribute("account", account);
		return "redirect:/ledger/gen_ledger/view/" + account.getId();
	}
	
	
	
	/* UPDATE */
	@RequestMapping(value = {"/edit/{id}"}, method=RequestMethod.GET)
	public String update(Model model, @PathVariable int id, HttpServletRequest request) {
		GeneralLedger genLedger = this.genLedgerBo.getLedgerById(id);
		
		GenLedgerForm genLedgerForm = new GenLedgerForm();
		genLedgerForm.setName(genLedger.getName());
		genLedgerForm.setCode(genLedger.getCode());
		genLedgerForm.setAccount_no(genLedger.getAccount_no());
		if (genLedger.getIs_active()) {
			genLedgerForm.setIs_active(1);
		} else {
			genLedgerForm.setIs_active(0);
		}

		model.addAttribute("account", genLedgerForm);
		
		this.auditor.before(request, "GenLedgerForm", genLedgerForm);
		return "/ledger/gen_ledger/edit";
	}
	
	@RequestMapping(value = {"/edit/{id}"}, method=RequestMethod.POST)
	public String update(@Valid @ModelAttribute("account") GenLedgerForm genLedgerForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes, @PathVariable int id, HttpServletRequest request) {
				
		try{

		GeneralLedger genLedger = this.genLedgerBo.update(genLedgerForm, id);
		this.auditor.after(request, "GenLedgerForm", genLedgerForm,
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

		return "redirect:/ledger/gen_ledger/view/" + id;
	}
	
		
	/* DELETE */
	@RequestMapping(value={"/delete/{id}"}, method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		GeneralLedger genLedger = this.genLedgerBo.getLedgerById(id);
		
		this.genLedgerBo.delete(genLedger);
		return "redirect:/ledger/gen_ledger/index";
	}
}
