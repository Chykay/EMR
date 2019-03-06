package org.calminfotech.ledger.controllers;

import java.util.List;

import javax.validation.Valid;

import org.calminfotech.ledger.boInterface.BalSheetCatBo;
import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.LedgerAccBo;
import org.calminfotech.ledger.boInterface.TotAccBo;
import org.calminfotech.ledger.daoImpl.PostCodeDaoImpl;
import org.calminfotech.ledger.forms.GLPostingForm;
import org.calminfotech.ledger.forms.LedgerAccForm;
import org.calminfotech.ledger.models.BalSheetCat;
import org.calminfotech.ledger.models.LedgerAccount;
import org.calminfotech.ledger.models.PostCode;
import org.calminfotech.ledger.models.TotalingAccount;
import org.calminfotech.ledger.utiility.LedgerException;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.annotations.Layout;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
	private LedgerAccBo ledgerAccBo;
	
	@Autowired
	private BalSheetCatBo balSheetCatBo;

	@Autowired
	private TotAccBo totAccBo;
	
	@Autowired
	private OrganisationBo organisationBo;
	
	@Autowired
	private GenLedgerBo genLedgerBo;
	
	
	@Autowired
	private PostCodeDaoImpl postCodeDaoImpl;

	@Autowired
	private Alert alert;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	
	/* POST GL */
	@RequestMapping(value = {"/index"}, method=RequestMethod.GET)
	public String index(Model model) {		
		
		List<TotalingAccount> totalingAccounts = this.totAccBo.fetchAll();
		List<BalSheetCat> balSheetCats = this.balSheetCatBo.fetchAll();
		
		model.addAttribute("account", new LedgerAccForm());
		model.addAttribute("balSheetCats", balSheetCats);
		model.addAttribute("totalingAccounts", totalingAccounts);
		return "/ledger/gen_ledger/postings/create";
	}
	
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = {"/post"}, method=RequestMethod.GET)
	public String postGl(Model model) {		
		Organisation org = userIdentity.getOrganisation();
	
		List<LedgerAccount> ledgerAccounts = this.ledgerAccBo.fetchAll(org.getId(), org.getOrgCoy().getId());
		
		for(LedgerAccount gl : ledgerAccounts) {
			 try {
				//if (this.genLedgerBo.getBalance(gl.getAccount_no(), gl.getOrganisation().getId(), gl.getOrgCoy().getId()) != null) {
					gl.setBalance(this.genLedgerBo.getBalance(gl.getAccount_no(), gl.getOrganisation().getId(), gl.getOrgCoy().getId()).getCurr_balance());
				/*} else {
					gl.setBalance(0);
					this.genLedgerBo.updateGLBalance(gl);
				}*/
			} catch (LedgerException e) {
				e.printStackTrace();
			}
		}
		List<Organisation> branches = this.organisationBo.fetchAll(org.getId());
		List<PostCode> postCodes = this.postCodeDaoImpl.fetchAll();

		
		model.addAttribute("posting", new GLPostingForm());
		model.addAttribute("generalLedgers", ledgerAccounts);
		model.addAttribute("postCodes", postCodes);
		model.addAttribute("branches", branches);
		return "/ledger/gen_ledger/postings/create";
	}
	
	
	@RequestMapping(value = {"/post"}, method=RequestMethod.POST)
	public String postGl(@Valid @ModelAttribute("account") GLPostingForm glPostingForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
				
		/* begin Transaction */
		Transaction tx = sessionFactory.openSession().beginTransaction();;
				try {

					/*tx = sessionFactory.openSession().beginTransaction();*/
					System.out.println("GLPostingController");
					this.genLedgerBo.GLPosting(glPostingForm);
					if (!tx.wasCommitted()){
						tx.commit();
					}
				} catch (LedgerException e) {
				    if(tx!=null){
				        tx.rollback();
				    }
				    alert.setAlert(redirectAttributes, Alert.DANGER,
							e.getExceptionMsg());

				} catch (Exception e) {
					 if(tx!=null){
					        tx.rollback();
					    }
					 alert.setAlert(redirectAttributes, Alert.DANGER,
								e.getMessage());
				}
	
		
		return "redirect:/ledger/ledger_acc/index";
	}
	
	
}
