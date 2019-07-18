package org.calminfotech.inventory.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.calminfotech.hrunit.models.ClockAssignment;
import org.calminfotech.inventory.exceptions.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.inventory.models.PointStockCurrentBalance;
import org.calminfotech.inventory.models.StockCurrentBalance;
import org.calminfotech.inventory.utils.Text;
import org.calminfotech.inventory.utils.UnitOfMeasureConverter;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemTypeBo;
import org.calminfotech.system.forms.InventorySearchForm;
import org.calminfotech.user.utils.Authorize;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.ClockedUsersList;
import org.calminfotech.utils.SearchUtility;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.visitqueue.forms.ChooseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/inventory")
public class InventoryController extends AbstractBaseController {

	@Autowired
	private GlobalItemBo globalItemBo;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SearchUtility searchUtilBo;

	@Autowired
	private GlobalItemTypeBo globalItemTypeBo;

	@Autowired
	private ClockedUsersList clockuserBo;

	@Autowired
	private Alert alert;

	@Autowired
	private UnitOfMeasureConverter unitconvert;

	@Autowired
	private Authorize authorize;

	/*
	 * @RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	 * 
	 * @Layout("layouts/datatable") //@Layout(value =
	 * "layouts/form_wizard_layout") public String index(Model model) {
	 * 
	 * 
	 * model.addAttribute("global",
	 * globalItemBo.fetchTop50byOrganisation(userIdentity
	 * .getOrganisation().getId()));
	 * 
	 * model.addAttribute("orgId", userIdentity.getOrganisation().getId());
	 * return "system/globalitem/index"; }
	 */

	@RequestMapping(value = { "/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	// @Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model, RedirectAttributes redirectAttributes) {

		if (!authorize.isAllowed("INV000")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity.setCurrentUrl("redirect:/inventory/index/all");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your current store unit");

			return "redirect:/visits/queue/12";
		}

		model.addAttribute("globalItemType",
				this.globalItemTypeBo.fetchAllByOrganisation());
		// model.addAttribute("global",
		// globalItemBo.fetchAll(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		InventorySearchForm pf = new InventorySearchForm();
		pf.setMysp(0);
		model.addAttribute("inventoryitemSearch", pf);

		return "inventory/indexall";
	}

	@RequestMapping(value = "/index/all", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String searchPatient(
			@ModelAttribute("inventoryitemSearch") InventorySearchForm inventorySearchForm,
			BindingResult result, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {

		if (!authorize.isAllowed("INV000")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity.setCurrentUrl("redirect:/inventory/index/all");

		if (!unitconvert.checkCurrentunit(12)) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Please choose your store unit");

			return "redirect:/visits/queue/12";
		}

		// List patientList = searchBo.searchPatient(searchForm, session);
		model.addAttribute("globalItemType",
				this.globalItemTypeBo.fetchAllByOrganisation());

		List<StockCurrentBalance> stockbalances = searchUtilBo
				.searchStockBalance(inventorySearchForm, session);

		System.out.print("Oyanowsize" + stockbalances.size());

		// System.out.print("Oyanowoya"
		// + stockbalances.get(0).getPrescriptionmeasurements().size());

		try {
			this.inventoriesManager.loadStockMeasureBalances(stockbalances);

		} catch (InvalidUnitOfMeasureConfiguration e) { // TODO

			// Auto-generated catch block e.printStackTrace();
			alert.setAlert(redirectAttributes, Alert.DANGER,
					e.getExceptionMsg());

			return "redirect:/visits/queue/12";

		}

		model.addAttribute("inventorylist", stockbalances);

		return "inventory/indexall";
		// return null;
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = "stock", method = RequestMethod.GET)
	public String fetchStock(ModelMap model) {

		return displayStocksListPage(model);
	}

	private String displayStocksListPage(ModelMap model) {
		// List<GlobalItem> globalItems = null;
		// globalItems = this.globalItemBo.fetchAll();
		// if (globalItems == null || globalItems.isEmpty()) {
		// this.alert(true, Alert.DANGER, Text.RECORD_NOT_FOUND, model);
		// }

		// model.addAttribute("globalItemsStockList", globalItems);

		return "/inventory/stock";
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = "point/stock", method = RequestMethod.GET)
	public String fetchPointStock(ModelMap model) {

		return displayPointStocksListPage(model);
	}

	private String displayPointStocksListPage(ModelMap model) {
		List<PointStockCurrentBalance> pointStockCurentBalancesList = null;
		pointStockCurentBalancesList = this.inventoriesManager
				.getPointStockCurrentBalances(userIdentity.getCurrentPointId(),
						userIdentity.getCurrentUnitId());
		if (pointStockCurentBalancesList == null
				|| pointStockCurentBalancesList.isEmpty()) {
			this.alert(true, Alert.DANGER, Text.RECORD_NOT_FOUND, model);
		}
		model.addAttribute("pointStockCurentBalancesList",
				pointStockCurentBalancesList);
		return "/inventory/point/stock";
	}

	@RequestMapping(value = "/changestore")
	// @Layout(value = "layouts/form_wizard_layout")
	public String newVisitAction4(Model model,
			RedirectAttributes redirectAttributes) {
		if (!authorize.isAllowed("INVCHST")) {

			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You have no permission to do this");

			return "redirect:/";
		}

		userIdentity.setCurrentPointId(12);
		List<ClockAssignment> clockin = this.clockuserBo.fetchAllByPoint(12);
		ChooseForm choose = new ChooseForm();

		model.addAttribute("choose", choose);
		model.addAttribute("clockin", clockin);

		return "/visits/changestore";

	}

}
