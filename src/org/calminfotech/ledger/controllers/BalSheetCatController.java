package org.calminfotech.ledger.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.ledger.boInterface.BalSheetCatBo;
import org.calminfotech.ledger.daoImpl.LedgerTypesImpl;
import org.calminfotech.ledger.forms.BalSheetForm;
import org.calminfotech.ledger.models.BalSheetCat;
import org.calminfotech.ledger.models.LedgerType;
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
@RequestMapping(value = "/ledger/bal_sheet_cat")
public class BalSheetCatController {
	@Autowired
	private BalSheetCatBo balSheetCatBo;
	
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
		BalSheetCat balSheetCat = this.balSheetCatBo.getLedgerById(id);
		model.addAttribute("account", balSheetCat);
		return "/ledger/bal_sheet_cat/show";
	}
	
	/* CREATE */

	@RequestMapping(value = {"/create"}, method=RequestMethod.GET)
	public String create(Model model) {		
		
		List<BalSheetCat> balSheetCats = this.balSheetCatBo.fetchAll();
		
		List<LedgerType> ledger_types = this.ledgerTypesImpl.fetchAll();
		model.addAttribute("p_accounts", balSheetCats);
		model.addAttribute("account", new BalSheetForm());
		return "/ledger/bal_sheet_cat/create";
	}
	
	
	@RequestMapping(value = {"/create"}, method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("account") BalSheetForm balSheetForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
				
		BalSheetCat account = this.balSheetCatBo.save(balSheetForm);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! New BalSheetCat Succesfully Added! BalSheetCat id:  "
						+ account.getId());

		model.addAttribute("account", account);
		return "redirect:/ledger/bal_sheet_cat/view/" + account.getId();
	}
	
	
	
	/* UPDATE */
	@RequestMapping(value = {"/edit/{id}"}, method=RequestMethod.GET)
	public String update(Model model, @PathVariable int id, HttpServletRequest request) {
		BalSheetCat balSheetCat = this.balSheetCatBo.getLedgerById(id);
		
		BalSheetForm balSheetForm = new BalSheetForm();
		balSheetForm.setName(balSheetCat.getName());
		if (balSheetCat.getIs_active()) {
			balSheetForm.setIs_active(1);
		} else {
			balSheetForm.setIs_active(0);
		}
		

		List<BalSheetCat> balSheetCats = this.balSheetCatBo.fetchAll();
		List<LedgerType> ledger_types = this.ledgerTypesImpl.fetchAll();
		

		model.addAttribute("p_accounts", balSheetCats);
		model.addAttribute("ledger_types", ledger_types);
		model.addAttribute("account", balSheetForm);
		
		this.auditor.before(request, "BalSheetForm", balSheetForm);
		return "/ledger/bal_sheet_cat/edit";
	}
	
	@RequestMapping(value = {"/edit/{id}"}, method=RequestMethod.POST)
	public String update(@Valid @ModelAttribute("account") BalSheetForm balSheetForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes, @PathVariable int id, HttpServletRequest request) {
				
		try{

		BalSheetCat balSheetCat = this.balSheetCatBo.update(balSheetForm, id);
		this.auditor.after(request, "BalSheetForm", balSheetForm,
				this.userIdentity.getUsername(), id);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! BalSheetCat Succesfully Edited! BalSheetCat id:  "
						+ id);

		model.addAttribute("account", balSheetCat);
		} catch(Exception e) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					" Failed! FAILED Editing! BalSheetCat id:  " + id + " Error: " + e.getMessage()
							);
			
		}

		return "redirect:/ledger/bal_sheet_cat/view/" + id;
	}
	
		
	/* DELETE */
	@RequestMapping(value={"/delete/{id}"}, method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		BalSheetCat balSheetCat = this.balSheetCatBo.getLedgerById(id);
		
		this.balSheetCatBo.delete(balSheetCat);
		return "redirect:/ledger/bal_sheet_cat/index";
	}
}
