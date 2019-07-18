/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.calminfotech.inventory.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.calminfotech.inventory.domains.MenuItem;
import org.calminfotech.inventory.serviceInterface.InventoryManagerInterface;
import org.calminfotech.system.boInterface.GlobalItemUnitofMeasureBo;
import org.calminfotech.system.models.GlobalItemUnitofMeasureVw;
import org.calminfotech.utils.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

//import org.calminfotech.system.models.UnitItem;

/**
 * 
 * @author Lala
 */
// @RequestMapping("/admin")
// @SessionAttributes("configurations")
public abstract class AbstractBaseController {

	/**
	 * Logger for this class and subclasses
	 */
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	protected InventoryManagerInterface inventoriesManager;

	@Autowired
	GlobalItemUnitofMeasureBo vwGlobalItemUnitofMeasureBo;

	@ModelAttribute("alert")
	public Object getMsgObj() {

		return new Alert();

	}

	/*
	 * @ExceptionHandler(Exception.class) public String
	 * handleAllException(Exception ex) { StackTraceElement[] trace =
	 * ex.getStackTrace(); //model.addAttribute("msgObj", new MsgObj(true,
	 * ex.getMessage(), "message")); //ex.printStackTrace(); StringBuilder
	 * buffer = new StringBuilder(); buffer.append("\nError trace:\n"); int size
	 * = trace.length; for (int i = 0; i < 10; i++) { if (i >= size) { break; }
	 * buffer.append(trace[i].toString()); buffer.append("\n"); } //
	 * log.info(buffer.toString()); // log.info("Error msg:" + ex.getMessage());
	 * 
	 * return "/exception"; }
	 */
	protected String notNull(String value) {
		return value == null ? "" : value;
	}

	protected Object getGlobalItemUnitOfMeasureList(int globalItemId) {
		// TODO Auto-generated method stub
		// List<GlobalUnitofMeasure> list = this.inventoriesManager
		// .fetchGlobalItemUnitOfMeasure(globalItemId);

		List<GlobalItemUnitofMeasureVw> list = this.vwGlobalItemUnitofMeasureBo
				.fetchAllByItemIdvw(globalItemId);

		// Map retMap = null;
		List retList = null;
		// System.out.print(list);
		if (list != null) {
			// retMap=new HashMap();
			retList = new ArrayList();
			for (GlobalItemUnitofMeasureVw item : list) {
				// retMap.put(item.getId(), item.getUnit_of_measure());
				retList.add(new MenuItem(String.valueOf(item.getId()), String
						.valueOf(item.getSearchname())));
			}

		}
		return retList;
	}

	/*
	 * public Object getGlobalItemUnitOfMeasureList(int parseInt) { // TODO
	 * Auto-generated method stub return null; }
	 */
	protected void alert(boolean msg, String type, String message,
			ModelMap model) {
		model.addAttribute("msg", msg);
		model.addAttribute("type", type);
		model.addAttribute("message", message);
	}

	protected void modalAlert(boolean msg, String type, String message,
			ModelMap model) {
		model.addAttribute("modal_msg", msg);
		model.addAttribute("type", type);
		model.addAttribute("message", message);
	}
}
