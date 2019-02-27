package org.calminfotech.ledger.controllers;

import java.util.List;

import javax.validation.Valid;

import org.calminfotech.ledger.boInterface.BalSheetCatBo;
import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.TotAccBo;
import org.calminfotech.ledger.daoImpl.GLBalDaoImpl;
import org.calminfotech.ledger.daoImpl.PostCodeDaoImpl;
import org.calminfotech.ledger.forms.GLPostingForm;
import org.calminfotech.ledger.forms.GenLedgerForm;
import org.calminfotech.ledger.models.BalSheetCat;
import org.calminfotech.ledger.models.GeneralLedger;
import org.calminfotech.ledger.models.PostCode;
import org.calminfotech.ledger.models.TotalingAccount;
import org.calminfotech.system.boInterface.OrganisationBo;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value = "/ledger/gen_ledger/postings")
public class GLPostingController {
	@Autowired
	private GenLedgerBo genLedgerBo;
	
	@Autowired
	private BalSheetCatBo balSheetCatBo;

	@Autowired
	private TotAccBo totAccBo;
	
	@Autowired
	private OrganisationBo organisationBo;
	
	@Autowired
	private GLBalDaoImpl gLBalDaoImpl;
	
	@Autowired
	private PostCodeDaoImpl postCodeDaoImpl;
	
	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Auditor auditor;
	
	/* POST GL */
	@RequestMapping(value = {"/index"}, method=RequestMethod.GET)
	public String index(Model model) {		
		
		List<TotalingAccount> totalingAccounts = this.totAccBo.fetchAll();
		List<BalSheetCat> balSheetCats = this.balSheetCatBo.fetchAll();
		
		model.addAttribute("account", new GenLedgerForm());
		model.addAttribute("balSheetCats", balSheetCats);
		model.addAttribute("totalingAccounts", totalingAccounts);
		return "/ledger/gen_ledger/postings/create";
	}
	
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = {"/post"}, method=RequestMethod.GET)
	public String postGl(Model model) {		
		Organisation org = userIdentity.getOrganisation();
		GLPostingForm posting = new GLPostingForm();
		float posting_bal = this.gLBalDaoImpl.getBalance(org.getId(), org.getOrgCoy().getId()).getCurr_balance();
		
		posting.setP_branch_bal(posting_bal);
		List<GeneralLedger> generalLedgers = this.genLedgerBo.fetchAll();
		
		for(GeneralLedger gl : generalLedgers) {
			gl.setBalance(this.gLBalDaoImpl.getBalance(gl.getOrganisation().getId(), gl.getOrgCoy().getId()).getCurr_balance());
		}
		List<Organisation> branches = this.organisationBo.fetchAll(org.getId());
		List<PostCode> postCodes = this.postCodeDaoImpl.fetchAll();
		
		model.addAttribute("posting", posting);
		model.addAttribute("generalLedgers", generalLedgers);
		model.addAttribute("postCodes", postCodes);
		model.addAttribute("branches", branches);
		return "/ledger/gen_ledger/postings/create";
	}
	
	
	@RequestMapping(value = {"/post"}, method=RequestMethod.POST)
	public String postGl(@Valid @ModelAttribute("account") GenLedgerForm genLedgerForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
				
		GeneralLedger account = this.genLedgerBo.save(genLedgerForm);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! New GeneralLedger Succesfully Added! GeneralLedger id:  "
						+ account.getId());

		model.addAttribute("account", account);
		return "redirect:/ledger/gen_ledger/postings/index";
	}
}
