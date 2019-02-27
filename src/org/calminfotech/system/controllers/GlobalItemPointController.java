package org.calminfotech.system.controllers;

//import org.calminfotech.system.boInterface.GlobalItemBo;
//import org.calminfotech.system.boInterface.GlobalItemCategoryBo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.visitqueue.boInterface.VisitWorkflowPointBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/system/category/{cId}/globalitem/{Id}/point")
public class GlobalItemPointController {
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private Auditor auditor;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private VisitWorkflowPointBo pointBo;
	
	
	
	//@Autowired
	//private GlobalItemCategoryBo gCategoryBo;

}
