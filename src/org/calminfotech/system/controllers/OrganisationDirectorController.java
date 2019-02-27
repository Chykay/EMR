package org.calminfotech.system.controllers;

import org.calminfotech.system.boInterface.OrganisationDirectorBo;
import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.forms.OrganisationDirectorForm;
import org.calminfotech.system.models.OrganisationDirector;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.utils.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/admin/organisations")
public class OrganisationDirectorController {
	
	@Autowired
	private OrganisationDirectorBo directorBo;
	
	@Autowired
	private OrganisationBo organisationBo;
	
	@Autowired
	private Alert alert;
	
	/*@RequestMapping(value =  "/{hId}/director" )
	@Layout("layouts/datatable")
	public String index(@PathVariable("hId")Integer hId, Model model) {
		
		Organisation organisation = organisationBo.getOrganisationById(hId);
		System.out.println("Organisation Id: " + organisation.getItemId());
		model.addAttribute("organisation", organisation);
		model.addAttribute("director", this.directorBo.fetchAll());

		return "admin/organisations/directors/index";
	}
	
	@RequestMapping(value="/{hId}/director/add", method = RequestMethod.GET)
	public String addDirector(@PathVariable("hId") Integer id, Model model){		
		
		Organisation hosipital = organisationBo.getOrganisationById(id);
		OrganisationDirectorForm directorForm = new OrganisationDirectorForm();	
		directorForm.setOrganisationId(hosipital.getItemId());
		model.addAttribute("directorForm", directorForm);	
		model.addAttribute("hosipital", hosipital);
		return "admin/organisations/directors/add";		
	}
	
	
	@RequestMapping(value="/{hId}/director/add", method = RequestMethod.POST)
	public String saveDirector(@Valid @ModelAttribute("directorForm") OrganisationDirectorForm directorForm,
									@ModelAttribute("hosipital") Organisation organisation,
							   BindingResult result ,Model model,
							   RedirectAttributes redirectAttributes){
		
		 organisation = organisationBo.getOrganisationById(directorForm.getOrganisationId());
		OrganisationDirector director = this.directorBo.save(directorForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! Organisation Director Succesfully Added!");
			
		return "redirect:/admin/organisations/" + organisation.getItemId() + "/director" ;		
	}	*/
	
	/*@RequestMapping(value="/director/view/{hId}/{dId}", method = RequestMethod.GET)
	public String viewDirector(@PathVariable("dId") Integer dId,
							   @PathVariable("hId") Integer hId, Model model){				
		
		Organisation organisation = organisationBo.getOrganisationById(hId);
		System.out.println("Organisation View Id is:" + organisation.getItemId());
		
		OrganisationDirector director = directorBo.getOrganisationDirectorById(dId);		
		System.out.println("Organisation Director View Id is:" + director.getDirectorId());
		System.out.println("Organisation Name is: " + director.getDirectorLastName());
		
		OrganisationDirectorForm directorForm = new OrganisationDirectorForm();		
		directorForm.setOrganisationId(organisation.getItemId());		
		directorForm.setDirectorId(director.getDirectorId());	
		 
		System.out.println("Director Form id is: " + directorForm.getDirectorId());
		System.out.println("Director's Name: " + directorForm.getDirectorLastName());
		
		model.addAttribute("directorForm", directorForm);		
		
		return "admin/organisations/directors/view";		
	}*/
	
	
	@RequestMapping(value="/director/edit/{hId}/{dId}", method = RequestMethod.GET)
	public String editDirector(@PathVariable("dId") Integer dId,
							   @PathVariable("hId") Integer hId, Model model){				
		
		Organisation organisation = organisationBo.getOrganisationById(hId);
		System.out.println("Organisation Id is :" + organisation.getId());
		
	//	OrganisationDirector director = directorBo.getOrganisationDirectorById(dId);
		//System.out.println("Directors Id is :" + director.getDirectorId());
		
		OrganisationDirectorForm directorForm = new OrganisationDirectorForm();
		
	//	directorForm.setOrganisationId(organisation.getId());
		//directorForm.setDirectorId(director.getDirectorId());
		//directorForm.setDirectorLastName(director.getDirectorLastName());
		//directorForm.setDirectorFirstName(director.getDirectorFirstName());
	//	directorForm.setDirectorEmail(director.getDirectorEmail());
	//	directorForm.setDirectorPhone(director.getDirectorPhone());
		
		model.addAttribute("directorForm", directorForm);
		
		
		return "admin/organisations/directors/edit";		
	}
	
	@RequestMapping(value="/director/edit/{hId}/{dId}", method = RequestMethod.POST)
	public String updateDirector(@ModelAttribute("directorForm") OrganisationDirectorForm directorForm, 
								 BindingResult result ,Model model,
								 RedirectAttributes redirectAttributes){
		
	//	Organisation organisation = organisationBo.getOrganisationById(directorForm.getOrganisationId());
		//OrganisationDirector director = directorBo.getOrganisationDirectorById(directorForm.getDirectorId());
	//	this.directorBo.update(director);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! Organisation Director Updated Succesfully!");
		
	//	return "redirect:/admin/organisations/" + organisation.getId() + "/director" ;			
	return null;	
	}
	
	
	//Delete Organisation Director
	@RequestMapping(value="/director/delete/{hId}/{dId}", method = RequestMethod.GET)
	public String deleteDirector(@PathVariable("dId") Integer dId,
							   @PathVariable("hId") Integer hId, Model model){				
		
		Organisation organisation = organisationBo.getOrganisationById(hId);
		System.out.println("Organisation View Id is:" + organisation.getId());
		
		//OrganisationDirector director = directorBo.getOrganisationDirectorById(dId);		
		//System.out.println("Organisation Director View Id is:" + director.getDirectorId());
		
		OrganisationDirectorForm directorForm = new OrganisationDirectorForm();		
	//	directorForm.setOrganisationId(organisation.getId());		
		//directorForm.setDirectorId(director.getDirectorId());	
		
		System.out.println("Director's Name: " + directorForm.getDirectorLastName());
		
		model.addAttribute("directorForm", directorForm);		
		
		return "admin/organisations/directors/view";		
	}
	
	
	
	
	
	
	

}
