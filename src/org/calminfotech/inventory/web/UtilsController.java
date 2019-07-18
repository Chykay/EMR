package org.calminfotech.inventory.web;



import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/inventory/")
public class UtilsController extends AbstractBaseController {



	@RequestMapping(value = "/item_unit_of_measure/{globalItemId}", method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody
	Object ajax(@PathVariable int globalItemId, ModelMap model) {

		return getGlobalItemUnitOfMeasureList(globalItemId);
	}

}