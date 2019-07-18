package org.calminfotech.ledger.controllers;

import java.util.List;

import javax.validation.Valid;

import org.calminfotech.ledger.boInterface.GLSetupBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.boInterface.LedgerCatBo;
import org.calminfotech.ledger.boInterface.TotCodeBo;
import org.calminfotech.ledger.forms.BankAccForm;
import org.calminfotech.ledger.forms.LedgerAccForm;
import org.calminfotech.ledger.models.BankAccount;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.models.LedgerCategory;
import org.calminfotech.ledger.models.TotalingCode;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.models.Bank;
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
	private TotCodeBo totCodeBo;

	@Autowired
	private LedgerCatBo ledgerCatBo;
	
	@Autowired
	private LedgerAccBo ledgerAccBo;
	
	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private GLSetupBo glSetupBo;
	
	@RequestMapping(value = {"/P_L"}, method=RequestMethod.GET)
	public String PandL(Model model){
		

		List<TotalingCode> totalingCodes = this.totCodeBo.fetchAll();
		List<LedgerCategory> ledgerCats = this.ledgerCatBo.fetchAll();
		
		model.addAttribute("account", new LedgerAccForm());
		model.addAttribute("ledgerCats", ledgerCats);
		model.addAttribute("totalingAccounts", totalingCodes);
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
	
	@RequestMapping(value = {"/banks/index"}, method=RequestMethod.GET)
	public String indexB(Model model){
		

		List<BankAccount> bankAccounts = this.glSetupBo.getAllBankAccs(this.userIdentity.getOrganisation());
		
		model.addAttribute("bankAccounts", bankAccounts);
		return "ledger/gen_ledger/setup/banks/index";
	}

	@RequestMapping(value = {"/banks/add"}, method=RequestMethod.GET)
	public String addB(Model model){
		
		List<LedgerAccount> ledgerAccounts = this.ledgerAccBo.getAssetLedgers();
		List<Bank> banks = this.glSetupBo.getAllBanks();
		
		model.addAttribute("bankAccount", new BankAccForm());
		model.addAttribute("banks", banks);
		model.addAttribute("ledgerAccounts", ledgerAccounts);
		return "ledger/gen_ledger/setup/banks/create";
	}
	

	@RequestMapping(value = {"/banks/add"}, method=RequestMethod.POST)
	public String addB_P(@Valid @ModelAttribute("bankAccount") BankAccForm bankAccForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes){
		this.glSetupBo.addBankAcc(bankAccForm);
		return "ledger/gen_ledger/setup/banks/index";
	}
	
}
