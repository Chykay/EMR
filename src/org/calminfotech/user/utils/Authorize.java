package org.calminfotech.user.utils;

import java.util.List;

import org.calminfotech.hrunit.boInterface.StaffRegBoInterface;
import org.calminfotech.hrunit.models.StaffRegistration;
import org.calminfotech.user.boInterface.PermissionBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.Authorization;
import org.calminfotech.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
//import org.calminfotech.system.models.Organisation;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Authorize {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private UserBo userBo;

	@Autowired
	private PermissionBo pemissionBo;

	@Autowired
	private StaffRegBoInterface staffregBo;

	private int userId;

	public boolean isAllowed(Integer userid, String authcode) {
		Boolean action = false;
		if (userIdentity.getOrganisation().getOrgCoy().getId() == 1) {
			action = true;
			return action;
		}

		// Authorization auth = null;
		// boolean render = (this.pList.contains(pId)||
		// this.uList.contains(pId));

		List<Authorization> list = this.pemissionBo.fecthAuthorizationlist(
				userid, authcode);

		if (list.size() > 0) {
			action = true;
		}

		return action;
	}

	public boolean isAllowed(String authcode) {
		Boolean action = false;
		// Authorization auth = null;
		// boolean render = (this.pList.contains(pId)||
		// this.uList.contains(pId));
		if (userIdentity.getUser().getUserType().getId() == 2
				|| userIdentity.getUser().getUserType().getId() == 3) {
			action = true;
			return action;
		}

		if (userIdentity.getOrganisation().getOrgCoy().getId() == 1) {
			action = true;
			return action;
		}

		Integer userid = userIdentity.getUserId();
		List<Authorization> list = this.pemissionBo.fecthAuthorizationlist(
				userid, authcode);

		if (list.size() > 0) {
			action = true;

		} else {
			action = false;
		}
		return action;

	}

	public boolean isLevel(String username, String creator, String modifier) {
		Boolean action = true;

		Integer usernamerank = 0;
		Integer creatorrank = 0;
		Integer modifierrank = 0;

		// boolean render = (this.pList.contains(pId)||
		// this.uList.contains(pId));

		User usr = this.userBo.getUserByEmail(username, userIdentity
				.getOrganisation().getId());

		if (usr != null && usr.getLevelsecurity() == true)

		{
			/*StaffRegistration staffusername = this.staffregBo
					.getStaffByUsernameId(username, userIdentity
							.getOrganisation().getId());
			StaffRegistration staffcreator = this.staffregBo
					.getStaffByUsernameId(creator, userIdentity
							.getOrganisation().getId());
			StaffRegistration staffmodifier = this.staffregBo
					.getStaffByUsernameId(modifier, userIdentity
							.getOrganisation().getId());*/
			/*if (staffusername.equals(null)) {
				usernamerank = 0;
			} else {
				usernamerank = (staffusername.getStaffranking() != null ? staffusername
						.getStaffranking().getRanking() : 0);
			}

			if (staffcreator.equals(null)) {
				creatorrank = 0;
			} else {
				creatorrank = (staffcreator.getStaffranking() != null ? staffcreator
						.getStaffranking().getRanking() : 0);
			}
			if (staffmodifier.equals(null)) {
				modifierrank = 0;
			} else {
				modifierrank = (staffcreator.getStaffranking() != null ? staffcreator
						.getStaffranking().getRanking() : 0);
			}

			if (usernamerank >= creatorrank && usernamerank >= modifierrank) {
				action = true;
			} else {
				action = false;
			}
*/
		}

		else

		{

			action = true;
		}

		return action;
	}

	public boolean isLevel(String creator, String modifier) {
		Boolean action = true;

		Integer usernamerank = 0;
		Integer creatorrank = 0;
		Integer modifierrank = 0;

		// boolean render = (this.pList.contains(pId)||
		// this.uList.contains(pId));

		String username = userIdentity.getUsername();

		User usr = this.userBo.getUserByEmail(username, userIdentity
				.getOrganisation().getId());

		if (usr != null && usr.getLevelsecurity() == true)

		{/*
			StaffRegistration staffusername = this.staffregBo
					.getStaffByUsernameId(username, userIdentity
							.getOrganisation().getId());
			StaffRegistration staffcreator = this.staffregBo
					.getStaffByUsernameId(creator, userIdentity
							.getOrganisation().getId());
			StaffRegistration staffmodifier = this.staffregBo
					.getStaffByUsernameId(modifier, userIdentity
							.getOrganisation().getId());
			if (staffusername.equals(null)) {
				usernamerank = 0;
			} else {
				usernamerank = (staffusername.getStaffranking() != null ? staffusername
						.getStaffranking().getRanking() : 0);
			}

			if (staffcreator.equals(null)) {
				creatorrank = 0;
			} else {
				creatorrank = (staffcreator.getStaffranking() != null ? staffcreator
						.getStaffranking().getRanking() : 0);
			}
			if (staffmodifier.equals(null)) {
				modifierrank = 0;
			} else {
				modifierrank = (staffcreator.getStaffranking() != null ? staffcreator
						.getStaffranking().getRanking() : 0);
			}

			if (usernamerank >= creatorrank && usernamerank >= modifierrank) {
				action = true;
			} else {
				action = false;
			}
*/
		}

		else

		{

			action = true;
		}

		return action;
	}

}
