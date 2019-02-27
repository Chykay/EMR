package org.calminfotech.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public  class Alert {

	public static final String SUCCESS = "success";
	public static final String PRIMARY = "primary";
	public static final String DANGER = "danger";
	
	public static final String WARNING = "warning";
	public static final String INFO = "info";

	public   void setAlert(RedirectAttributes redirectAttributes, String type,
			String message) {
		redirectAttributes.addFlashAttribute("msg", true);
		redirectAttributes.addFlashAttribute("type", type);
		String icon = null;
		if (type.equals(SUCCESS)) {
			icon = "fa-check";
		} else if (type.equals(DANGER)) {
			icon = "fa-times";
		} else if (type.equals(WARNING)) {
			icon = "fa-warning";
		} else if (type.equals(INFO)) {
			icon = "fa-thumbs-up";
		}

		redirectAttributes.addFlashAttribute("icon", icon);
		String str = String.format("%s", message);
		redirectAttributes.addFlashAttribute("message", str);
	}

	
}
