package org.calminfotech.ledger.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.ledger.boInterface.TotAccBo;
import org.calminfotech.ledger.daoImpl.LedgerTypesImpl;
import org.calminfotech.ledger.forms.TotalingForm;
import org.calminfotech.ledger.models.LedgerType;
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
@RequestMapping(value = "/ledger/totaling")
public class TotAccController {
	
	@Autowired
	private TotAccBo totAccBo;
	
	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Auditor auditor;
	
	@Autowired
	LedgerTypesImpl ledgerTypesImpl;
	
	/* SHOW ALL */
	@RequestMapping(value = {"/view/{id}"}, method=RequestMethod.GET)
	public String show(Model model, @PathVariable int id) {
		TotalingAccount totalingAccount = this.totAccBo.getLedgerById(id);
		model.addAttribute("account", totalingAccount);
		return "/ledger/totaling/show";
	}
	
	/* CREATE */

	@RequestMapping(value = {"/create"}, method=RequestMethod.GET)
	public String create(Model model) {		
		model.addAttribute("account", new TotalingForm());
		
		List<LedgerType> ledger_types = this.ledgerTypesImpl.fetchAll();
		model.addAttribute("ledger_types", ledger_types);
		return "/ledger/totaling/create";
	}
	
	
	@RequestMapping(value = {"/create"}, method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("account") TotalingForm totalingForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
				
		TotalingAccount account = this.totAccBo.save(totalingForm);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! New TotalingAccount Succesfully Added! TotalingAccount id:  "
						+ account.getId());

		model.addAttribute("account", account);
		return "redirect:/ledger/totaling/view/" + account.getId();
	}
	
	
	
	/* UPDATE */
	@RequestMapping(value = {"/edit/{id}"}, method=RequestMethod.GET)
	public String update(Model model, @PathVariable int id, HttpServletRequest request) {
		TotalingAccount totalingAccount = this.totAccBo.getLedgerById(id);
		
		TotalingForm totalingForm = new TotalingForm();
		
		totalingForm.setCode(totalingAccount.getCode());
		/*totalingForm.setLedger_type(totalingAccount.getLedger_type().getId());*/
		totalingForm.setLedger_type("2");
		totalingForm.setName(totalingAccount.getName());
		if (totalingAccount.getIs_active()) {
			totalingForm.setIs_active(1);
		} else {
			totalingForm.setIs_active(0);
		}
		

		List<LedgerType> ledger_types = this.ledgerTypesImpl.fetchAll();
		
		
		model.addAttribute("ledger_types", ledger_types);
		model.addAttribute("account", totalingForm);
		
		this.auditor.before(request, "TotalingForm", totalingForm);
		return "/ledger/totaling/edit";
	}
	
	@RequestMapping(value = {"/edit/{id}"}, method=RequestMethod.POST)
	public String update(@Valid @ModelAttribute("account") TotalingForm totalingForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes, @PathVariable int id, HttpServletRequest request) {
				
		try{

		TotalingAccount totalingAccount = this.totAccBo.update(totalingForm, id);
		this.auditor.after(request, "TotalingForm", totalingForm,
				this.userIdentity.getUsername(), id);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! TotalingAccount Succesfully Edited! TotalingAccount id:  "
						+ id);

		model.addAttribute("account", totalingAccount);
		} catch(Exception e) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					" Failed! FAILED Editing! TotalingAccount id:  " + id + " Error: " + e.getMessage()
							);
			
		}

		return "redirect:/ledger/totaling/view/" + id;
	}
	
		
	/* DELETE */
	@RequestMapping(value={"/delete/{id}"}, method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		TotalingAccount totalingAccount = this.totAccBo.getLedgerById(id);
		
		this.totAccBo.delete(totalingAccount);
		return "redirect:/ledger/totaling/index";
	}
}