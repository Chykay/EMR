package org.calminfotech.ledger.controllers;

import java.util.List;

import org.calminfotech.ledger.boInterface.BalSheetCatBo;
import org.calminfotech.ledger.boInterface.GenLedgerBo;
import org.calminfotech.ledger.boInterface.TotAccBo;
import org.calminfotech.ledger.daoImpl.PermImpl;
import org.calminfotech.ledger.models.BalSheetCat;
import org.calminfotech.ledger.models.GPermission;
import org.calminfotech.ledger.models.GeneralLedger;
import org.calminfotech.ledger.models.TotalingAccount;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Layout(value = "layouts/datatable")
@RequestMapping(value = "/ledger")
public class IndexController {
	
	@Autowired
	private TotAccBo totAccBo;

	@Autowired
	private BalSheetCatBo balSheetCatBo;
	
	@Autowired
	private GenLedgerBo genLedgerBo;
	
	@Autowired
	private PermImpl permImpl;
	
	

	/* TOTALING ACCOUNT */
	@RequestMapping(value = {"/totaling/index"}, method=RequestMethod.GET)
	public String indexTotaling(Model model) {
		List<TotalingAccount> totalingAccounts = this.totAccBo.fetchAll();
		
		model.addAttribute("accounts", totalingAccounts);
		// model.addAttribute("id", id);
		return "/ledger/totaling/index";
	}

	/* BALANCE SHEET CATEGORY */
	@RequestMapping(value = {"/bal_sheet_cat/index"}, method=RequestMethod.GET)
	public String indexBalSheetCat(Model model) {
		List<BalSheetCat> balSheetCats = this.balSheetCatBo.fetchAll();
		
		for(BalSheetCat balSheetCat: balSheetCats) {
			if(balSheetCat.getParent_id() != 0){
				balSheetCat.setParent_name(this.balSheetCatBo.getLedgerById(balSheetCat.getParent_id()).getName());
			} else {
				balSheetCat.setParent_name("None");
			}
		}
		
		model.addAttribute("accounts", balSheetCats);
		// model.addAttribute("id", id);
		return "/ledger/bal_sheet_cat/index";
	}
	

	/* GENERAL LEDGER */
	@RequestMapping(value = {"/gen_ledger/index"}, method=RequestMethod.GET)
	public String indexGenLedgert(Model model) {
		List<GeneralLedger> generalLedgers = this.genLedgerBo.fetchAll();
		model.addAttribute("accounts", generalLedgers);
		// model.addAttribute("id", id);
		return "/ledger/gen_ledger/index";
	}
	
	@RequestMapping(value = {"/permissions"}, method=RequestMethod.GET)
	public String perm(Model model) {
		List<GPermission> gPermissions = this.permImpl.fetchAll();
		model.addAttribute("permissions", gPermissions);
		return "/ledger/permission";
	}
}
