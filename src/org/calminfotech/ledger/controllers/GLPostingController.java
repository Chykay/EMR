package org.calminfotech.ledger.controllers;

import java.util.List;

import javax.validation.Valid;

import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.daoImpl.PostCodeDaoImpl;
import org.calminfotech.ledger.forms.GLPostingForm;
import org.calminfotech.ledger.models.GLEntry;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.models.PostCode;
import org.calminfotech.ledger.utiility.LedgerException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value = "/ledger/gen_ledger")
public class GLPostingController {
	@Autowired
	private LedgerAccBo ledgerAccBo;
	
	@Autowired
	private OrganisationBo organisationBo;
	
	@Autowired
	private GenLedgerBo genLedgerBo;
	
	
	@Autowired
	private PostCodeDaoImpl postCodeDaoImpl;

	@Autowired
	private Alert alert;

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
	@RequestMapping(value = {"/index"}, method=RequestMethod.GET)
	public String index(Model model) {		
		
		List<GLEntry> glEntries = null;
		try {
			glEntries = this.genLedgerBo.getGLEntries();
		} catch (LedgerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("glEntries", glEntries);
		return "/ledger/gen_ledger/index";
	}
	
	/* REVERSE ENTRY */
	@RequestMapping(value = {"/reversal/{batch_no}"}, method=RequestMethod.GET)
	public String GLReversal(@PathVariable("batch_no") String batch_no, Model model) {		
		
		try {
			this.genLedgerBo.GLReversal(batch_no);
		} catch (LedgerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/ledger/gen_ledger/index";
	}
	
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = {"/direct/post"}, method=RequestMethod.GET)
	public String postGl(Model model) {		
		Organisation org = userIdentity.getOrganisation();
	
		List<LedgerAccount> ledgerAccounts = this.ledgerAccBo.fetchAll(org.getId(), org.getOrgCoy().getId());
		List<Organisation> branches = this.organisationBo.fetchAll(org.getId());
		List<PostCode> postCodes = this.postCodeDaoImpl.fetchAll();

		
		model.addAttribute("posting", new GLPostingForm());
		model.addAttribute("generalLedgers", ledgerAccounts);
		model.addAttribute("postCodes", postCodes);
		model.addAttribute("branches", branches);
		return "/ledger/gen_ledger/direct/create";
	}

	
	@RequestMapping(value = {"/direct/post"}, method=RequestMethod.POST)
	public String postGl(@Valid @ModelAttribute("account") GLPostingForm glPostingForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
				
		/* begin Transaction 
		Transaction tx = sessionFactory.getCurrentSession().beginTransaction();*/
				/*try {*/

					System.out.println("GLPostingController");
					try {
						this.genLedgerBo.GLPosting(glPostingForm);
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

}
