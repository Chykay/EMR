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

import org.calminfotech.patient.boInterface.PatientDocumentBo;
import org.calminfotech.patient.boInterface.PatientHmoBo;
import org.calminfotech.patient.boInterface.PatientTelephoneBo;
import org.hibernate.SessionFactory;

import org.calminfotech.patient.boInterface.PatientHistoryBo;
import org.calminfotech.patient.boInterface.PatientPaymentOptionBo;
import org.calminfotech.patient.boInterface.PatientSearchBo;

import org.calminfotech.patient.forms.PatientAddressForm;
import org.calminfotech.patient.forms.PatientAllergyForm;
import org.calminfotech.patient.forms.PatientDocumentForm;
import org.calminfotech.patient.forms.PatientEmergencyForm;
import org.calminfotech.patient.forms.PatientDocumentForm;
import org.calminfotech.patient.forms.PatientForm;
import org.calminfotech.patient.forms.PatientHistoryForm;
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

import org.calminfotech.patient.models.PatientDocument;
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
import org.calminfotech.utils.AddressTypeList;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.BloodgenotypeList;
import org.calminfotech.utils.BloodgroupList;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.GenderList;
import org.calminfotech.utils.HistoryTypeList;
import org.calminfotech.utils.LifestatusList;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.MaritalStatusList;
import org.calminfotech.utils.OccupationList;
import org.calminfotech.utils.PhoneTypeList;
import org.calminfotech.utils.SearchUtility;
import org.calminfotech.utils.models.Phonetype;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.SurgicalList;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.models.Addresstype;
import org.calminfotech.utils.models.Historytype;
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
public class PatientsDocumentController {
	
	
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
	private AddressTypeList addresstypeBo;

	@Autowired
	private HistoryTypeList historytypeBo;

	
	@Autowired
	private PatientHmoBo patientHmoBo;

	@Autowired
	private TitleBo titleBo;

	@Autowired
	private LanguageBo languageBo;


	@Autowired
	private GenderList genderBo;
	
	@Autowired
	private SurgicalList surgicalList;

	@Autowired
	private PatientDocumentBo patientDocumentBo;

	@Autowired
	private HmoPackageBo hmoPackageBo;



	@Autowired
	private PatientHistoryBo patientMedicalHistoryBo;

	
	@Autowired
	private PatientTelephoneBo  patienttelephoneBo;


	
	@Autowired
	private PatientPaymentOptionBo patientPaymentOptionBo;

	@Autowired
	private PatientSearchBo searchBo;

	
	@Autowired
	private PatientCodeGenerator patientCodeGenerator;
	
	@Autowired
	private PatientTelephoneBo patientTelephoneBo;
	
	@Autowired
	private SearchUtility searchUtilBo;
	
	



	// this is for document inside patient
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/documents/{id}", method = RequestMethod.GET)
	public String documentAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Patient patient = this.patientBo.getPatientById(id);
		
		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/patients";
		}
		PatientDocumentForm pdForm = new PatientDocumentForm();
		pdForm.setPatientId(patient.getPatientId());

		model.addAttribute("documentForm", pdForm);
		
		
		model.addAttribute("patient", patient);

		return "customers/patients/documents";
	}

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/documents/{id}", method = RequestMethod.POST)
	public String saveDocument(
			@Valid @ModelAttribute("documentForm") PatientDocumentForm patientDocumentForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes)  {

		Patient patient = this.patientBo.getPatientById(patientDocumentForm
				.getPatientId());

		if (result.hasErrors()) {
			model.addAttribute("patient", patient);
			return "customers/patients/documents";
		}

		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Could not upload document. Invalid resource");
			return "redirect:/patients";
		}

		
		
		
		PatientDocument patientDocument = new PatientDocument();
		try {
			@SuppressWarnings("deprecation")
			Blob blob = Hibernate.createBlob(patientDocumentForm.getDocument()
					.getInputStream());
			patientDocument.setFile(blob);

			String contentType = patientDocumentForm.getDocument()
					.getContentType();
		

			patientDocument.setContentType(contentType);
			patientDocument.setName(patientDocumentForm.getDocument().getOriginalFilename());
			patientDocument.setPatient(patient);
			patientDocument.setOrganisation(userIdentity.getOrganisation());
			patientDocument.setCreateDate(new GregorianCalendar().getTime());
			patientDocument.setCreatedBy(userIdentity.getUsername());
			patientDocument.setDeleted(false);
			patientDocument.setDescription(patientDocumentForm.getDescription());
			
			patient.getPatientDocuments().add(patientDocument);
			

			// Used for saving
			this.patientBo.update(patient);

			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success!  Uploaded successfully");
		
			

			
			
		} catch (Exception e) {
		e.printStackTrace();
		alert.setAlert(redirectAttributes, Alert.DANGER,
				"Document Upload failed " + e.getMessage());
	}

		return "redirect:/patients/documents/" + patient.getPatientId();
	}

	@ResponseBody
	@RequestMapping(value = "/documents/view/{documentId}/{documentName}")
	public String viewDocumentAction(@PathVariable Integer documentId,
			@PathVariable("documentName") String documentName,
			RedirectAttributes redirectAttributes, HttpServletResponse response) {

		PatientDocument patientDocument = this.patientDocumentBo
				.getPatientDocumentById(documentId);

		if (null == patientDocument) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}

		try {
			response.setContentType(patientDocument.getContentType());

			response.setHeader("Content-Disposition", "inline;filename=\""
					+ patientDocument.getName() + "\"");

			OutputStream outputStream = response.getOutputStream();

			IOUtils.copy(patientDocument.getFile().getBinaryStream(),
					outputStream);

			outputStream.flush();
			outputStream.close();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/documents/delete/{id}")
	public String documentDeleteAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		PatientDocument patientDocument = this.patientDocumentBo
				.getPatientDocumentById(id);

		if (null == patientDocument) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}

		PatientDocumentForm patientDocumentForm = new PatientDocumentForm();
		patientDocumentForm.setPatientDocumentId(patientDocument.getId());

		model.addAttribute("document", patientDocument);
		model.addAttribute("pForm", patientDocumentForm);

		return "customers/patients/delete_document";
	}

	@RequestMapping(value = "/documents/delete/{id}", method = RequestMethod.POST)
	public String confirmDocumentDeleteAction(
			@ModelAttribute("pForm") PatientDocumentForm patientDocumentForm,
			RedirectAttributes redirectAttributes) {

		PatientDocument patientDocument = this.patientDocumentBo
				.getPatientDocumentById(patientDocumentForm.getPatientDocumentId());

		if (null == patientDocument) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/patients/documents/" + patientDocumentForm.getPatientDocumentId();
		
		}

		int patientId = patientDocument.getPatient().getPatientId();
		patientDocument.setDeleted(true);
		
		patientDocument.setModifiedBy(userIdentity.getUsername());
		patientDocument.setModifyDate(new GregorianCalendar().getTime());
		this.patientDocumentBo.update(patientDocument);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! File deleted");

		return "redirect:/patients/documents/" + patientId;
	}
	
	
	
	
	
	@RequestMapping(value = "/documents/edit/{id}", method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String geteditDocument(@PathVariable("id") Integer id,Model model, 
			RedirectAttributes redirectAttributes,HttpServletRequest request) {

		
		PatientDocumentForm patientDocumentForm = new  PatientDocumentForm();
		
		
		PatientDocument  patientdocument = patientDocumentBo.getPatientDocumentById(id);
		//List<Historytype> historytype = historytypeBo.fetchAll();
	//	patientdocument.getPatient().getPatientId()
		
		patientDocumentForm.setDescription(patientdocument.getDescription());
		
		
		model.addAttribute("documentForm",patientDocumentForm);
		model.addAttribute("patientdocument",patientdocument);
		
	
		this.auditor.before(request, "Patient Document", patientDocumentForm);
		return "customers/patientsdocuments/edit";
	
	}

	

	@RequestMapping(value = "/documents/edit/{id}", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String posteditDocument( @PathVariable("id") Integer id,
			@ModelAttribute("histForm") PatientDocumentForm patientDocumentForm,
			BindingResult result, Model model, 
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
	
		PatientDocument  patientDocument = patientDocumentBo.getPatientDocumentById(id);
		
		
		patientDocument.setDescription(patientDocumentForm.getDescription());
		
		
		patientDocument.setModifyDate(new GregorianCalendar().getTime());
		patientDocument.setModifiedBy(userIdentity.getUsername());
		
		patientDocumentBo.update(patientDocument);
				
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! History  Updated");
		this.auditor.after(request, "Patient Document", patientDocumentForm,
				this.userIdentity.getUsername(),id);
		
		return "redirect:/patients/view/" + patientDocument.getPatient().getPatientId();
	
	}
	
	
	
	
}
