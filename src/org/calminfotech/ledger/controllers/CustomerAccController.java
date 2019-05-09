package org.calminfotech.ledger.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.ledger.boInterface.BalSheetCatBo;
import org.calminfotech.ledger.boInterface.CustomerAccBo;
import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.boInterface.TotAccBo;
import org.calminfotech.ledger.forms.LedgerAccForm;
import org.calminfotech.ledger.models.BalSheetCat;
import org.calminfotech.ledger.models.CustomerAccount;
import org.calminfotech.ledger.models.CustomerEntry;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.models.TotalingAccount;
import org.calminfotech.ledger.utiility.LedgerException;
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
@RequestMapping(value = "/ledger/customer_acc")
public class CustomerAccController {
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
	
	@Autowired
	private GenLedgerBo genLedgerBo;
	
	@Autowired
	private CustomerAccBo customerAccBo;
	
	@RequestMapping(value = {"/index"}, method=RequestMethod.GET)
	public String index(Model model) {
		List<CustomerEntry> customerEntries = null;
		try {
			customerEntries = this.genLedgerBo.getCustEntries();
		} catch (LedgerException e) {
			e.printStackTrace();
		}
		model.addAttribute("custEntries", customerEntries);
		return "/ledger/customer_acc/index";
	}
	

	@RequestMapping(value = {"/listings"}, method=RequestMethod.GET)
	public String getListings(Model model) {
		
		model.addAttribute("custEntries");
		return "/ledger/customer_acc/list";
	}
	
	
	@RequestMapping(value = {"/listings"}, method=RequestMethod.POST)
	public String postListings(Model model, @Valid @ModelAttribute("product_type") String product_type,
			@Valid @ModelAttribute("account_no") String account_no,  @Valid @ModelAttribute("start_date") String start_date,
			@Valid @ModelAttribute("end_date") String end_date) {
		
		System.out.println(product_type + "got here : " + start_date );
		if (product_type.contains("CA")) {
			System.out.println(product_type + "got here  2 : " );
			List<CustomerEntry>  customerEntries = null;
			try {
				customerEntries = this.genLedgerBo.getCustEntriesListing(account_no, start_date, end_date);
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println("error oo");
			}
			model.addAttribute("custEntries", customerEntries);
		}
		
		List<CustomerEntry>  customerEntries = null;
		try {
			customerEntries = this.genLedgerBo.getCustEntriesListing(account_no, start_date, end_date);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("error oo");
		}
		model.addAttribute("custEntries", customerEntries);
		
		return "/ledger/customer_acc/list";
	}
	
	/* REVERSE ENTRY */
	@RequestMapping(value = {"/reversal/{batch_no}"}, method=RequestMethod.GET)
	public String GLReversal(@PathVariable("batch_no") String batch_no, Model model) {
		
		try {
			this.genLedgerBo.CustReversal(batch_no);
		} catch (LedgerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/ledger/gen_ledger/index";
	}
	
	/* SHOW ALL */
	@RequestMapping(value = {"/view/{id}"}, method=RequestMethod.GET)
	public String show(Model model, @PathVariable int id) {
		LedgerAccount genLedger = this.ledgerAccBo.getLedgerById(id);
		model.addAttribute("account", genLedger);
		return "/ledger/ledger_acc/show";
	}
	
	/* CREATE */
	@Layout(value = "layouts/form_wizard_layout")
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
		ledgerAccForm.setAccountNo(genLedger.getAccountNo());
		if (genLedger.getIsActive()) {
			ledgerAccForm.setIsActive(1);
		} else {
			ledgerAccForm.setIsActive(0);
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
	

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@Layout(value = "layouts/blank")
	public String custAccSearch( Model model) {
		Organisation org = this.userIdentity.getOrganisation();
		model.addAttribute("customerAccounts", this.customerAccBo.fetchAll(org.getId(), org.getOrgCoy().getId()));
		model.addAttribute("customerAcc", new CustomerAccount());
		return "ledger/customer_acc/search";
	}
}
