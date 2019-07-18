package org.calminfotech.inventory.web;

import java.util.List;

import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.models.GlobalItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/inventory")
public class InventoryHomeController {
	
	@Autowired
	private GlobalItemBo globalItemBo;

	@RequestMapping(method = RequestMethod.GET)
	public String indexh(ModelMap model) {
		// return "redirect: system/umgt/permission";

		return displayHomePage(model);
	}

	private String displayHomePage(ModelMap model) {
		List<GlobalItem> globalItems = null;
		globalItems = this.globalItemBo.fetchAll();
		model.addAttribute("globalItemsStockList", globalItems);
		return "default/index";
	}

}