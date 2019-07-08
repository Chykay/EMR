package org.calminfotech.ledger.controllers;

import java.util.List;

import javax.validation.Valid;

import org.calminfotech.ledger.boInterface.GLSetupBo;
import org.calminfotech.ledger.boInterface.LedgerCatBo;
import org.calminfotech.ledger.boInterface.TotAccBo;
import org.calminfotech.ledger.forms.LedgerAccForm;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.models.LedgerCategory;
import org.calminfotech.ledger.models.TotalingAccount;
import org.calminfotech.utils.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/ledger/setup")
public class GLSetupController {

	@Autowired
	private TotAccBo totAccBo;
	
	@Autowired
	private LedgerCatBo ledgerCatBo;
	
	@Autowired
	private Alert alert;
/*
	@Autowired
	private LedgerAccBo ledgerAccBo;*/

	@Autowired
	private GLSetupBo glSetupBo;
	
	@RequestMapping(value = {"/P_L"}, method=RequestMethod.GET)
	public String PandL(Model model){
		

		List<TotalingAccount> totalingAccounts = this.totAccBo.fetchAll();
		List<LedgerCategory> ledgerCats = this.ledgerCatBo.fetchAll();
		
		model.addAttribute("account", new LedgerAccForm());
		model.addAttribute("ledgerCats", ledgerCats);
		model.addAttribute("totalingAccounts", totalingAccounts);
		return "ledger/gen_ledger/setup/PandL";
	}
	
	@RequestMapping(value = {"/P_L"}, method=RequestMethod.POST)
	public String PandL1(@Valid @ModelAttribute("account") LedgerAccForm ledgerAccForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		LedgerAccount account = new LedgerAccount();
		try {
			account = this.glSetupBo.reserveGL(ledgerAccForm);
			
			
		} catch (Exception e) {
			
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Failure! Account Number Already Exists for this branch  "
							+ account.getId());
			return "redirect:/ledger/setup/P_L";
		}
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! New GeneralLedger Succesfully Added! GeneralLedger id:  "
						+ account.getId());

		model.addAttribute("account", account);
		return "redirect:/ledger/setup/P_L";
	}
	
}
