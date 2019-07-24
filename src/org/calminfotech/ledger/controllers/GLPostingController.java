package org.calminfotech.ledger.controllers;

import java.util.List;

import javax.validation.Valid;

import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.boInterface.LedgerPostingBo;
import org.calminfotech.ledger.daoImpl.PostCodeDaoImpl;
import org.calminfotech.ledger.forms.GLPostingForm;
import org.calminfotech.ledger.forms.LedgerListingForm;
import org.calminfotech.ledger.models.GLEntry;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.models.PostCode;
import org.calminfotech.ledger.utility.LedgerException;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.sf.json.JSONArray;
/*
import org.json.simple.JSONObject;*/
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/ledger/gen_ledger")
public class GLPostingController {	
	@Autowired
	private OrganisationBo organisationBo;
	
	@Autowired
	private LedgerPostingBo ledgerPostingBo;
	
	
	@Autowired
	private PostCodeDaoImpl postCodeDaoImpl;

	@Autowired
	private Alert alert;
	
	@Autowired
	private LedgerAccBo ledgerAccBo;

	/*	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private BalSheetCatBo balSheetCatBo;

	@Autowired
	private TotAccBo totAccBo;
	*/

	@Autowired
	private UserIdentity userIdentity;

	
	/* GET ALL GL ENTRIES*/
	@Layout("layouts/datatable")
	@RequestMapping(value = {"/index"}, method=RequestMethod.GET)
	public String index(Model model) {		
		
		List<GLEntry> glEntries = null;
		try {
			glEntries = this.ledgerPostingBo.getGLEntries(this.userIdentity.getOrganisation().getId());
		} catch (LedgerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("glEntries", glEntries);
		return "/ledger/gen_ledger/index";
	}

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = {"/direct/post"}, method=RequestMethod.GET)
	public String postGl(Model model) {		
		Organisation org = userIdentity.getOrganisation();
	
		List<LedgerAccount> ledgerAccounts = this.ledgerAccBo.fetchPostingGLS(org.getOrgCoy().getId());
		List<Organisation> branches = this.organisationBo.fetchAll(org.getOrgCoy().getId());
		List<PostCode> postCodes = this.postCodeDaoImpl.fetchAll();

		
		model.addAttribute("posting", new GLPostingForm());
		model.addAttribute("generalLedgers", ledgerAccounts);
		model.addAttribute("postCodes", postCodes);
		model.addAttribute("branches", branches);
		return "/ledger/gen_ledger/direct/create";
	}

	
	@RequestMapping(value = {"/direct/post"}, method=RequestMethod.POST)
	public String postGl(@Valid @ModelAttribute("posting") GLPostingForm glPostingForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
				
		/* begin Transaction 
		Transaction tx = sessionFactory.getCurrentSession().beginTransaction();*/
				/*try {*/

					System.out.println("GLPostingController");
					try {
						this.ledgerPostingBo.GLPosting(glPostingForm);
					} catch (LedgerException e) {
						// TODO Auto-generated catch block
						alert.setAlert(redirectAttributes, Alert.DANGER, e.getExceptionMsg());
					}
/*					if (!tx.wasCommitted()){
						tx.commit();
					}
				} catch (LedgerException e) {
				    if(tx!=null){
				        tx.rollback();
				        System.out.println("Ledger Exception");
				    }
				    alert.setAlert(redirectAttributes, Alert.DANGER,
							e.getExceptionMsg());

				} catch (Exception e) {
					 if(tx!=null){
					        tx.rollback();
					    }
					 alert.setAlert(redirectAttributes, Alert.DANGER,
								e.getMessage() + " exception");
				}*/
	
		
		return "redirect:/ledger/gen_ledger/index";
	}
	
	
	@Layout("layouts/datatable")
	@RequestMapping(value = {"/listings"}, method=RequestMethod.GET)
	public String listings(Model model) {
		model.addAttribute("criteria", new LedgerListingForm());
		model.addAttribute("glEntries");
		return "/ledger/gen_ledger/list";
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = {"/listings"}, method=RequestMethod.POST)
	public String postListings(Model model, @Valid @ModelAttribute("criteria") LedgerListingForm criteria) {
		String start_date = criteria.getStartDate();
		String end_date = criteria.getEndDate();
		String account_no = criteria.getAccountNo();
		
		List<GLEntry>  glEntries = null;

		System.out.println(account_no + " : " + start_date + " : " + end_date);
		if (end_date.equals("")) end_date = "2999-01-01";
		System.out.println(account_no + " : " + start_date + " : " + end_date);
		try {
			glEntries = this.ledgerPostingBo.getGLEntriesListing(account_no, start_date, end_date);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("error oo");
		}

		model.addAttribute("criteria", criteria);
		model.addAttribute("glEntries", glEntries);
		
		
		return "/ledger/gen_ledger/list";
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = {"/listings/{id}"}, method=RequestMethod.GET)
	public String listingsGL(Model model, @PathVariable("id") String accountNo) {
		
		List<GLEntry> glEntries = null;
		model.addAttribute("criteria", new LedgerListingForm());
		try {
			glEntries = this.ledgerPostingBo.getEntriesForGL(this.userIdentity.getOrganisation().getId(), accountNo);
		} catch (LedgerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("glEntries", glEntries);
		return "/ledger/gen_ledger/listGL";
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = {"/listings/{id}"}, method=RequestMethod.POST)
	public String postListingsGL(Model model, @PathVariable("id") String accountNo, @Valid @ModelAttribute("criteria") LedgerListingForm criteria) {
		String start_date = criteria.getStartDate();
		String end_date = criteria.getEndDate();
		String account_no = accountNo;
		
		List<GLEntry>  glEntries = null;

		System.out.println(account_no + " : " + start_date + " : " + end_date);
		if (end_date.equals("")) end_date = "2999-01-01";
		System.out.println(account_no + " : " + start_date + " : " + end_date);
		try {
			glEntries = this.ledgerPostingBo.getGLEntriesListing(account_no, start_date, end_date);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("error oo");
		}

		model.addAttribute("criteria", criteria);
		model.addAttribute("glEntries", glEntries);
		
		
		return "/ledger/gen_ledger/listGL";
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = {"/listings/{company_id}/{id}"}, method=RequestMethod.GET)
	public String listingsGLCompany(Model model, @PathVariable("company_id") int company_id, @PathVariable("id") String accountNo) {
		
		List<GLEntry> glEntries = null;
		model.addAttribute("criteria", new LedgerListingForm());
		try {
			glEntries = this.ledgerPostingBo.getEntriesForGLCompany(company_id, accountNo);
		} catch (LedgerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("glEntries", glEntries);
		return "/ledger/gen_ledger/listGL";
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = {"/listings/{company_id}/{id}"}, method=RequestMethod.POST)
	public String listingsGLCompany(Model model, @PathVariable("company_id") int company_id,  @PathVariable("id") String accountNo, @Valid @ModelAttribute("criteria") LedgerListingForm criteria) {
		String start_date = criteria.getStartDate();
		String end_date = criteria.getEndDate();
		String account_no = accountNo;
		
		List<GLEntry>  glEntries = null;

		if (end_date.equals("")) end_date = "2999-01-01";
		try {
			glEntries = this.ledgerPostingBo.getGLEntriesListingCom(account_no, start_date, end_date);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("error oo");
		}

		model.addAttribute("criteria", criteria);
		model.addAttribute("glEntries", glEntries);
		
		
		return "/ledger/gen_ledger/listGL";
	}
	
	/* REVERSE ENTRY */
	@RequestMapping(value = {"/reversal/{batch_no}"}, method=RequestMethod.GET)
	public String GLReversal(@PathVariable("batch_no") String batch_no, Model model) {
		
		try {
			this.ledgerPostingBo.GLReversal(batch_no);
		} catch (LedgerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/ledger/gen_ledger/index";
	}
	

	@RequestMapping(value = {"/direct/dummy"}, method=RequestMethod.POST)
	public Object dummy(@RequestBody Object jsonObject){
		
		JSONArray array = (JSONArray)jsonObject;
		//List <String> names = null;
		
		System.out.println(jsonObject);
		System.out.println(array.get(0));
		
		JSONObject jObject = (JSONObject)array.get(0);
		

		System.out.println(jObject.get("age"));
		return jsonObject;
	
		
	}
	
/*	 GET ALL JOURNAL ENTRIES
	@RequestMapping(value = {"/journal/index"}, method=RequestMethod.GET)
	public String indexJ(Model model) {		
		
		List<JournalEntry> journalEntries = null;
		try {
			journalEntries = this.genLedgerBo.getJournalEntries();
		} catch (LedgerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("journalEntries", journalEntries);
		return "/ledger/gen_ledger/journal/index";
	}*/
	

}
