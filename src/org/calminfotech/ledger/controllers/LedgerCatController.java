package org.calminfotech.ledger.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.ledger.boInterface.LedgerCatBo;
import org.calminfotech.ledger.daoImpl.LedgerTypesImpl;
import org.calminfotech.ledger.forms.LedgerCatForm;
import org.calminfotech.ledger.models.LedgerCategory;
import org.calminfotech.ledger.models.LedgerType;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.hibernate.exception.ConstraintViolationException;
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
@RequestMapping(value = "/ledger/ledger_cat")
public class LedgerCatController {
	@Autowired
	private LedgerCatBo ledgerCatBo;
	
	@Autowired
	private Alert alert;
	/*
	@Autowired
	private UserIdentity userIdentity;
*/
	@Autowired
	private Auditor auditor;
	
	@Autowired
	LedgerTypesImpl ledgerTypesImpl;
	
	@RequestMapping(value = {"/index"}, method=RequestMethod.GET)
	public String indexledgerCat(Model model) {
		List<LedgerCategory> ledgerCats = this.ledgerCatBo.fetchAll();
		
		for(LedgerCategory ledgerCat: ledgerCats) {
			if(ledgerCat.getParentID() != 0){
				ledgerCat.setParentName(this.ledgerCatBo.getLedgerById(ledgerCat.getParentID()).getName());
			} else {
				ledgerCat.setParentName("None");
			}
		}
		
		model.addAttribute("categories", ledgerCats);
		// model.addAttribute("id", id);
		return "/ledger/ledger_cat/index";
	}
	
	/* SHOW ALL */
	@RequestMapping(value = {"/view/{id}"}, method=RequestMethod.GET)
	public String show(Model model, @PathVariable int id) {
		LedgerCategory ledgerCat = this.ledgerCatBo.getLedgerById(id);
		model.addAttribute("category", ledgerCat);
		return "/ledger/ledger_cat/show";
	}
	
	/* CREATE */

	@RequestMapping(value = {"/create"}, method=RequestMethod.GET)
	public String create(Model model) {		
		
		List<LedgerCategory> ledgerCats = this.ledgerCatBo.getCatsWithoutLedgerChildren();
		//List<LedgerType> ledgerTypes = this.ledgerTypesImpl.fetchAll();
		
		model.addAttribute("ledgerCats", ledgerCats);
		//model.addAttribute("ledgerTypes", ledgerTypes);
		model.addAttribute("category", new LedgerCatForm());
		return "/ledger/ledger_cat/create";
	}
	
	
	@RequestMapping(value = {"/create"}, method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("category") LedgerCatForm ledgerCatForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
				
		try {
			this.ledgerCatBo.save(ledgerCatForm);
		} catch (Exception e) {
			if(e instanceof ConstraintViolationException){
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Error Category '" + ledgerCatForm.getName() + "' already exist");
			}
		}
		/*
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! New ledgerCat Succesfully Added! ledgerCat id:  "
						+ category.getId());

		model.addAttribute("category", category);*/
		return "redirect:/ledger/ledger_cat/index";
	}
	
	
	
	/* UPDATE */
	@RequestMapping(value = {"/edit/{id}"}, method=RequestMethod.GET)
	public String update(Model model, @PathVariable int id, HttpServletRequest request) {
		LedgerCategory ledgerCat = this.ledgerCatBo.getLedgerById(id);
		
		LedgerCatForm ledgerCatForm = new LedgerCatForm();
		ledgerCatForm.setName(ledgerCat.getName());
		if (ledgerCat.getIsActive()) {
			ledgerCatForm.setIsActive(1);
		} else {
			ledgerCatForm.setIsActive(0);
		}
		
		ledgerCatForm.setParentID(ledgerCat.getParentID());
		
		List<LedgerCategory> ledgerCats = this.ledgerCatBo.fetchParents(id);
		List<LedgerType> ledger_types = this.ledgerTypesImpl.fetchAll();
		
		model.addAttribute("pCategories", ledgerCats);
		model.addAttribute("ledger_types", ledger_types);
		model.addAttribute("category", ledgerCatForm);
		
		this.auditor.before(request, "ledgerCatForm", ledgerCatForm);
		return "/ledger/ledger_cat/edit";
	}
	
	@RequestMapping(value = {"/edit/{id}"}, method=RequestMethod.POST)
	public String update(@Valid @ModelAttribute("category") LedgerCatForm ledgerCatForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes, @PathVariable int id, HttpServletRequest request) {

		this.ledgerCatBo.update(ledgerCatForm, id);
		/*try{
			LedgerCategory ledgerCat = this.ledgerCatBo.update(ledgerCatForm, id);
		this.auditor.after(request, "ledgerCatForm", ledgerCatForm,
				this.userIdentity.getUsername(), id);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! ledgerCat Succesfully Edited! ledgerCat id:  "
						+ id);

		model.addAttribute("category", ledgerCat);
		} catch(Exception e) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					" Failed! FAILED Editing! ledgerCat id:  " + id + " Error: " + e.getMessage()
							);
			
		}*/

		return "redirect:/ledger/ledger_cat/index";
	}
	
		
	/* DELETE */
	@RequestMapping(value={"/delete/{id}"}, method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		LedgerCategory ledgerCat = this.ledgerCatBo.getLedgerById(id);
		
		this.ledgerCatBo.delete(ledgerCat);
		return "redirect:/ledger/ledger_cat/index";
	}
}
