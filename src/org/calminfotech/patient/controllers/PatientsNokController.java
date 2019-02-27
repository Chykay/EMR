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

import org.calminfotech.patient.boInterface.PatientNokBo;
import org.calminfotech.patient.boInterface.PatientHmoBo;
import org.calminfotech.patient.boInterface.PatientHistoryBo;
import org.hibernate.SessionFactory;

import org.calminfotech.patient.boInterface.PatientHistoryBo;
import org.calminfotech.patient.boInterface.PatientPaymentOptionBo;
import org.calminfotech.patient.boInterface.PatientSearchBo;

import org.calminfotech.patient.forms.PatientAllergyForm;
import org.calminfotech.patient.forms.PatientDocumentForm;
import org.calminfotech.patient.forms.PatientEmergencyForm;
import org.calminfotech.patient.forms.PatientNokForm;
import org.calminfotech.patient.forms.PatientForm;
import org.calminfotech.patient.forms.PatientHmoForm;
import org.calminfotech.patient.forms.PatientImageForm;
import org.calminfotech.patient.forms.PatientMedicalHistoryForm;
import org.calminfotech.patient.forms.PatientPaymentOptionForm;
import org.calminfotech.patient.forms.PatientSocialHistoryForm;
import org.calminfotech.patient.forms.PatientSurgicalHistoryForm;
import org.calminfotech.patient.forms.PatientHistoryForm;
import org.calminfotech.patient.forms.PatientSearchForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.patient.models.PatientHistory;
import org.calminfotech.patient.models.PatientNok;

import org.calminfotech.patient.models.PatientDocument;


import org.calminfotech.patient.models.PatientHmo;

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
import org.calminfotech.utils.HistoryTypeList;
import org.calminfotech.utils.RelationshipTypeList;
import org.calminfotech.utils.models.Historytype;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.SurgicalList;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.models.LocalGovernmentArea;
import org.calminfotech.utils.models.MaritalStatus;
import org.calminfotech.utils.models.Historytype;
import org.calminfotech.utils.models.Relationshiptype;
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
public class PatientsNokController {
	
	
	
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
	private RelationshipTypeList relationshiptypeBo;




	
	@Autowired
	private PatientPaymentOptionBo patientPaymentOptionBo;

	@Autowired
	private PatientSearchBo searchBo;

	

	
	
	@Autowired
	private PatientCodeGenerator patientCodeGenerator;
	
	@Autowired
	private PatientNokBo patientNokBo;
	
	@RequestMapping(value = "/nok/save", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String addHistory(
			@ModelAttribute("nokForm") PatientNokForm nokForm,
			BindingResult result, Model model, 
			RedirectAttributes redirectAttributes) {
	
		PatientNok  patientnok = new PatientNok();
		
		Patient patient= patientBo.getPatientById(nokForm.getPatientId());
		
		Relationshiptype   relationshiptype = relationshiptypeBo.getRelationshipTypeById(nokForm.getRelationshiptypeId());
		
		
		patientnok.setPatient(patient);
		patientnok.setFullname(nokForm.getFullname());
		patientnok.setRelationshipType(relationshiptype);
		patientnok.setEmail(nokForm.getEmail());
		patientnok.setEmail(nokForm.getTelephone());
		patientnok.setEmail(nokForm.getAddress());
		patientnok.setCreatedBy(userIdentity.getUsername());
		patientnok.setCreatedDate(new GregorianCalendar().getTime());
		patientnok.setOrganisation(userIdentity.getOrganisation());
		
		
		patientNokBo.save(patientnok);
		
		
		return "redirect:/patients/view/" + patientnok.getPatient().getPatientId();
	
	}

	@RequestMapping(value = "/nok/edit/{id}", method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String geteditHistory(@PathVariable("id") Integer id,
			@ModelAttribute("nokForm") PatientNokForm nokForm,
			BindingResult result, Model model, 
			RedirectAttributes redirectAttributes,HttpServletRequest request) {

		PatientNok  patientnok = patientNokBo.getPatientNokById(id);
		List<Relationshiptype> relationshiptype = relationshiptypeBo.fetchAll();
		
		nokForm.setId(patientnok.getId());
		nokForm.setFullname(patientnok.getFullname());
		nokForm.setRelationshiptypeId(patientnok.getRelationshipType().getRelationshiptypeId());
		nokForm.setEmail(patientnok.getEmail());
		nokForm.setAddress(patientnok.getAddress());
		nokForm.setTelephone(patientnok.getTelephone());
		
		
		
		
		model.addAttribute("relationshiptypelist",relationshiptype);
	
		this.auditor.before(request, "Patient Next of Kin History", nokForm);
		return "customers/patientsnok/edit";
	
	}

	

	@RequestMapping(value = "/nok/edit/{id}", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String posteditHistory( @PathVariable("id") Integer id,
			@ModelAttribute("nokForm") PatientNokForm nokForm,
			BindingResult result, Model model, 
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
	
		PatientNok  patientnok = patientNokBo.getPatientNokById(id);
		
		
		patientnok.setFullname(nokForm.getFullname());
		patientnok.setRelationshipType(relationshiptypeBo.getRelationshipTypeById(nokForm.getRelationshiptypeId()));
		patientnok.setEmail(nokForm.getEmail());
		patientnok.setAddress(nokForm.getAddress());
		patientnok.setTelephone(nokForm.getTelephone());
		
		
		patientnok.setModifiedBy(userIdentity.getUsername());
		
		patientnok.setModifiedDate(new GregorianCalendar().getTime());
		patientNokBo.update(patientnok);
				
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! History  Updated");
		this.auditor.after(request, "Patient History", nokForm,
				this.userIdentity.getUsername(),id);
		
		return "redirect:/patients/view/" + patientnok.getPatient().getPatientId();
	
	}

	
	
	@RequestMapping(value = "/nok/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Integer id,
	@ModelAttribute("nokForm") PatientHistoryForm historyForm,
	RedirectAttributes redirectAttributes) {
		
		PatientNok  patientnok = patientNokBo.getPatientNokById(id);
		
		patientnok.setModifiedBy(userIdentity.getUsername());
		patientnok.setModifiedDate(new GregorianCalendar().getTime());
		patientnok.setDeleted(true);
		patientNokBo.update(patientnok);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! History  deleted");
		return "redirect:/patients/view/" + patientnok.getPatient().getPatientId();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
