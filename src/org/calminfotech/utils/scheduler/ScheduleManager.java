package org.calminfotech.utils.scheduler;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface ScheduleManager {

	public void sendMailSchedule();
	
	public void sendSMSSchedule();

	void sendMailSchedule(RedirectAttributes redirectAttributes);

}
