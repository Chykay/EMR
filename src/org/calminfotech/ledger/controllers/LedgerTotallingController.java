package org.calminfotech.ledger.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.ledger.boInterface.LedgerTotallingBo;
import org.calminfotech.ledger.daoImpl.LedgerTypesImpl;
import org.calminfotech.ledger.forms.TotalingForm;
import org.calminfotech.ledger.models.LedgerType;
import org.calminfotech.ledger.models.TotalingCode;
import org.calminfotech.user.utils.UserIdentity;
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
@RequestMapping(value = "/ledger/totaling")
public class LedgerTotallingController {
	
	@Autowired
	private LedgerTotallingBo ledgerTotallingBo;
	
	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Auditor auditor;
	
	@Autowired
	LedgerTypesImpl ledgerTypesImpl;
	
	@RequestMapping(value = {"/index"}, method=RequestMethod.GET)
	public String indexTotaling(Model model) {
		List<TotalingCode> totalingCodes = this.ledgerTotallingBo.fetchAll();
		
		for (TotalingCode totalingCode : totalingCodes) {
			totalingCode.setEditable(!this.ledgerTotallingBo.isUsed(totalingCode));
		}
		
		model.addAttribute("totalingCodes", totalingCodes);
		return "/ledger/totaling/index";
	}
	


	@RequestMapping(value = {"/create"}, method=RequestMethod.GET)
	public String create(Model model) {		
		List<LedgerType> ledgerTypes = this.ledgerTypesImpl.fetchAll();	
		
		model.addAttribute("totalingCode", new TotalingForm());
		model.addAttribute("ledgerTypes", ledgerTypes);
		return "/ledger/totaling/create";
	}
	
	
	@RequestMapping(value = {"/create"}, method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("totalingCode") TotalingForm totalingForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		TotalingCode totalingCode = new TotalingCode();
		try {
			totalingCode = this.ledgerTotallingBo.save(totalingForm);
		} catch (Exception e) {
			
			if(e instanceof ConstraintViolationException){
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Error Totaling Code '" + totalingForm.getCode() + "' already exist");
			}
		}
		
		

		return "redirect:/ledger/totaling/index";
	}
	
	
	
	/* UPDATE */
	@RequestMapping(value = {"/edit/{id}"}, method=RequestMethod.GET)
	public String update(Model model, @PathVariable int id, HttpServletRequest request) {

		if (this.ledgerTotallingBo.isUsed(this.ledgerTotallingBo.getLedgerById(id))) {
			return "redirect:/ledger/totaling/index";
		}
		TotalingCode totalingCode = this.ledgerTotallingBo.getLedgerById(id);
		
		TotalingForm totalingForm = new TotalingForm();
		
		totalingForm.setCode(totalingCode.getCode());
		totalingForm.setLedgerType(totalingCode.getLedgerType().getId());
		totalingForm.setName(totalingCode.getName());
		if (totalingCode.getIsActive()) {
			totalingForm.setIsActive(1);
		} else {
			totalingForm.setIsActive(0);
		}
		

		List<LedgerType> ledgerTypes = this.ledgerTypesImpl.fetchAll();
		
		
		model.addAttribute("ledgerTypes", ledgerTypes);
		model.addAttribute("totalingCode", totalingForm);
		this.auditor.before(request, "TotalingCode", totalingCode);

		return "/ledger/totaling/edit";
	}
	
	 @RequestMapping(value = {"/edit/{id}"}, method=RequestMethod.POST)
		public String update(@Valid @ModelAttribute("totalingCode") TotalingForm totalingForm, BindingResult result, Model model,
				RedirectAttributes redirectAttributes, @PathVariable int id, HttpServletRequest request) {
					
			try{

			TotalingCode totalingCode = this.ledgerTotallingBo.update(totalingForm, id);
			this.auditor.after(request, "TotalingForm", totalingForm,
					this.userIdentity.getUsername(), id);
			
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					" Success! TotalingAccount Succesfully Edited! TotalingAccount id:  "
							+ id);

			model.addAttribute("totalingCode", totalingCode);
			} catch(Exception e) {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						" Failed! FAILED Editing! TotalingAccount id:  " + id + " Error: " + e.getMessage()
								);
			}

			return "redirect:/ledger/totaling/index";
		}
	/* UPDATE */
	@RequestMapping(value = {"/status/{id}"}, method=RequestMethod.GET)
	public String updateStatus(Model model, @PathVariable int id, HttpServletRequest request) {
		
		TotalingCode totalingCode = this.ledgerTotallingBo.getLedgerById(id);
		this.auditor.before(request, "TotalingCode", totalingCode);

		this.ledgerTotallingBo.updateStatus(totalingCode);
		
		this.auditor.after(request, "TotalingCode", totalingCode,
				this.userIdentity.getUsername(), id);
		return "redirect:/ledger/totaling/index";
	}
	

	/* 
	 *
	
	@RequestMapping(value = {"/view/{id}"}, method=RequestMethod.GET)
	public String show(Model model, @PathVariable int id) {
		TotalingCode totalingCode = this.totCodeBo.getLedgerById(id);
		model.addAttribute("totalingCode", totalingCode);
		return "/ledger/totaling/show";
	}
		
		
	  
	@RequestMapping(value={"/delete/{id}"}, method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		TotalingCode totalingCode = this.totCodeBo.getLedgerById(id);
		
		this.totCodeBo.delete(totalingCode);
		return "redirect:/ledger/totaling/index";
	}*/
}