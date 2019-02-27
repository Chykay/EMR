package org.calminfotech.patient.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;

import org.calminfotech.hmo.boInterface.HmoBo;
import org.calminfotech.hmo.boInterface.HmoPackageBo;
import org.calminfotech.hmo.models.Hmo;
import org.calminfotech.hmo.models.HmoPackage;

import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientDocumentBo;
import org.calminfotech.patient.boInterface.PatientDocumentBo;

import org.calminfotech.patient.boInterface.PatientFamilyHistoryBo;
import org.calminfotech.patient.boInterface.PatientHmoBo;
import org.calminfotech.patient.boInterface.PatientTelephoneBo;
import org.hibernate.SessionFactory;

import org.calminfotech.patient.boInterface.PatientHistoryBo;
import org.calminfotech.patient.boInterface.PatientPaymentOptionBo;
import org.calminfotech.patient.boInterface.PatientSearchBo;

import org.calminfotech.patient.forms.PatientAllergyForm;
import org.calminfotech.patient.forms.PatientDocumentForm;
import org.calminfotech.patient.forms.PatientEmergencyForm;
import org.calminfotech.patient.forms.PatientFamilyHistoryForm;
import org.calminfotech.patient.forms.PatientForm;
import org.calminfotech.patient.forms.PatientHmoForm;
import org.calminfotech.patient.forms.PatientImageForm;
import org.calminfotech.patient.forms.PatientMedicalHistoryForm;
import org.calminfotech.patient.forms.PatientPaymentOptionForm;
import org.calminfotech.patient.forms.PatientSocialHistoryForm;
import org.calminfotech.patient.forms.PatientSurgicalHistoryForm;
import org.calminfotech.patient.forms.PatientTelephoneForm;
import org.calminfotech.patient.forms.PatientSearchForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;

import org.calminfotech.patient.models.PatientDocument;

import org.calminfotech.patient.models.PatientFamilyHistory;
import org.calminfotech.patient.models.PatientHmo;
import org.calminfotech.patient.models.PatientTelephone;


import org.calminfotech.patient.models.PatientPaymentOption;


import org.calminfotech.patient.utils.PatientCodeGenerator;

import org.calminfotech.system.boInterface.LanguageBo;
import org.calminfotech.system.boInterface.TitleBo;
import org.calminfotech.billing.boInterface.BillSchemeBo;
import org.calminfotech.billing.boInterface.BillSchemeItemBo;

import org.calminfotech.system.boInterface.PointBo;
import org.calminfotech.system.forms.AllergyForm;

import org.calminfotech.system.models.Diseases;
import org.calminfotech.system.models.Title;

import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.BloodgenotypeList;
import org.calminfotech.utils.BloodgroupList;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.GenderList;
import org.calminfotech.utils.LifestatusList;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.MaritalStatusList;
import org.calminfotech.utils.OccupationList;
import org.calminfotech.utils.PhoneTypeList;
import org.calminfotech.utils.models.Phonetype;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.SurgicalList;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.models.LocalGovernmentArea;
import org.calminfotech.utils.models.MaritalStatus;
import org.calminfotech.utils.models.Phonetype;
import org.calminfotech.utils.models.State;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
@Controller
@RequestMapping(value = "/patients")
public class PatientsTelehoneController {
	

	
	@Autowired
	private PatientBo patientBo;


	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Auditor auditor;

	@Autowired
	private LocalGovernmentAreaList lgasList;

	@Autowired
	private MaritalStatusList MSList;

	@Autowired
	private OccupationList occuList;
	
	
	@Autowired
	private BloodgroupList groupList;
	
	@Autowired
	private BloodgenotypeList genoList;
	
	@Autowired
	private LifestatusList lifeList;
	

	

	
	@Autowired
	private StatesList stateList;
	
	@Autowired
	private HmoBo hmoBo;
	
	/*@Autowired
	private EhmoBo ehmoBo;*/

	@Autowired
	private PhoneTypeList telephonetypeBo;




	

	
	@Autowired
	private PatientPaymentOptionBo patientPaymentOptionBo;

	@Autowired
	private PatientSearchBo searchBo;

	
	@Autowired
	private PatientCodeGenerator patientCodeGenerator;
	
	@Autowired
	private PatientTelephoneBo patientTelephoneBo;
	
	
	
	
	
	


	@RequestMapping(value = "/telephone/save", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String addPhone(
			@ModelAttribute("teleForm") PatientTelephoneForm phoneForm,
			BindingResult result, Model model, 
			RedirectAttributes redirectAttributes) {
	
		PatientTelephone  patienttele = new PatientTelephone();
		Patient patient= patientBo.getPatientById(phoneForm.getPatientId());
		Phonetype  phonetype=telephonetypeBo.getPhoneTypeById(phoneForm.getPhonetypeId());
		
	
		patienttele.setPatient(patient);
		patienttele.setTelnumber(phoneForm.getTelnumber());
		patienttele.setPhoneType(phonetype);
		
		
		patienttele.setCreatedBy(userIdentity.getUsername());
		patienttele.setCreateDate(new GregorianCalendar().getTime());
		
		patienttele.setOrganisation(userIdentity.getOrganisation());
		
		patientTelephoneBo.save(patienttele);
		
		
		return "redirect:/patients/view/" + patienttele.getPatient().getPatientId();
	
	}

	@RequestMapping(value = "/telephone/edit/{id}", method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String geteditPhone(@PathVariable("id") Integer id,
			@ModelAttribute("teleForm") PatientTelephoneForm phoneForm,
			BindingResult result, Model model, 
			RedirectAttributes redirectAttributes,HttpServletRequest request) {

		PatientTelephone  patienttele = patientTelephoneBo.getPatientTelephoneById(id);
		List<Phonetype> telephonetype = telephonetypeBo.fetchAll();
		
		phoneForm.setId(patienttele.getId());
		phoneForm.setPhonetypeId(patienttele.getPhoneType().getPhonetypeId());
		phoneForm.setTelnumber(patienttele.getTelnumber());
		
		
		model.addAttribute("phonetypelist",telephonetype);
	
		this.auditor.before(request, "Patient Telephone", phoneForm);
		return "customers/patientstelephone/edit";
	
	}

	

	@RequestMapping(value = "/telephone/edit/{id}", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String posteditPhone( @PathVariable("id") Integer id,
			@ModelAttribute("teleForm") PatientTelephoneForm phoneForm,
			BindingResult result, Model model, 
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
	
		PatientTelephone  patienttele = patientTelephoneBo.getPatientTelephoneById(id);
		patienttele.setTelnumber(phoneForm.getTelnumber());
		patienttele.setPhoneType(telephonetypeBo.getPhoneTypeById(phoneForm.getPhonetypeId()));
		patienttele.setModifiedBy(userIdentity.getUsername());
		
		patienttele.setModifiedDate(new GregorianCalendar().getTime());
		patientTelephoneBo.update(patienttele);
				
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Telephone  Updated");
		this.auditor.after(request, "Patient Telephone", phoneForm,
				this.userIdentity.getUsername(),id);
		
		return "redirect:/patients/view/" + patienttele.getPatient().getPatientId();
	
	}

	
	
	@RequestMapping(value = "/telephone/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Integer id,
	@ModelAttribute("teleForm") PatientTelephoneForm phoneForm,
	RedirectAttributes redirectAttributes) {
		
		PatientTelephone  patienttele = patientTelephoneBo.getPatientTelephoneById(id);
		
		patienttele.setModifiedBy(userIdentity.getUsername());
		patienttele.setModifiedDate(new GregorianCalendar().getTime());
		patienttele.setDeleted(true);
		patientTelephoneBo.update(patienttele);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Telephone  deleted");
		return "redirect:/patients/view/" + patienttele.getPatient().getPatientId();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
