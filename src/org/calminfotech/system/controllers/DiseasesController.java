package org.calminfotech.system.controllers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.DiseasesBo;
import org.calminfotech.system.boInterface.DiseasesCategoryBo;
import org.calminfotech.system.boInterface.DiseasesTypeBo;
import org.calminfotech.system.forms.DiseasesForm;
import org.calminfotech.system.forms.DiseasesSearchForm;
import org.calminfotech.system.models.Diseases;
import org.calminfotech.system.models.DiseasesCategory;
import org.calminfotech.system.models.DiseasesType;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.SearchUtility;
import org.calminfotech.utils.Test;
import org.calminfotech.utils.annotations.Layout;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

@Controller
@RequestMapping(value = "/system/diseases")
public class DiseasesController {

	@Autowired
	private DiseasesCategoryBo diseasesCategoryBo;

	@Autowired
	private DiseasesBo diseasesBo;

	@Autowired
	private SearchUtility searchUtilBo;

	@Autowired
	private DiseasesBo diseasesBou;

	@Autowired
	private DiseasesTypeBo diseasesTypeBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	public Test test;

	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	// @Layout(value = "layouts/form_wizard_layout")
	public String index(Model model) {

		// test.setH("2222222jjjjjj");

		model.addAttribute("diseases", diseasesBo
				.fetchTop50byOrganisation(userIdentity.getOrganisation()
						.getId()));

		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/diseases/index";
	}

	/*
	 * @RequestMapping(value = {"/index/all" }, method = RequestMethod.GET)
	 * 
	 * @Layout("layouts/datatable") //@Layout(value =
	 * "layouts/form_wizard_layout") public String indexall(Model model) { //
	 * /System.out.println("ttt"); // System.out.println(test.getH());
	 * //System.out.println("ttt"); //model.addAttribute("ttt",j.getH());
	 * model.addAttribute("diseases",
	 * diseasesBo.fetchAll(userIdentity.getOrganisation().getId()));
	 * model.addAttribute("orgId", userIdentity.getOrganisation().getId());
	 * return "system/diseases/indexall"; }
	 */

	@RequestMapping(value = { "/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	// @Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model) {
		model.addAttribute("diseasesType",
				this.diseasesTypeBo.fetchAllByOrganisation());
		// model.addAttribute("global",
		// globalItemBo.fetchAll(userIdentity.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		DiseasesSearchForm pf = new DiseasesSearchForm();
		pf.setMysp(0);
		model.addAttribute("diseasesSearch", pf);

		return "system/diseases/indexall";
	}

	@RequestMapping(value = "/index/all", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String searchPatient(
			@ModelAttribute("diseasesSearch") DiseasesSearchForm diseasesSearchForm,
			BindingResult result, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {

		// List patientList = searchBo.searchPatient(searchForm, session);

		model.addAttribute("diseasesType",
				this.diseasesTypeBo.fetchAllByOrganisation());
		List diseasesList = searchUtilBo.searchDiseases(diseasesSearchForm,
				session);

		model.addAttribute("diseases", diseasesList);

		return "system/diseases/indexall";
	}

	/*
	 * @RequestMapping(value = "/view/{Id}")
	 * 
	 * @Layout("layouts/datatable") public String view(@PathVariable("Id")
	 * Integer categoryId, Model model, RedirectAttributes redirectAttributes) {
	 * //DiseasesCategory gCategory =
	 * DiseasesCategoryBo.getDiseasesCategoryById(categoryId);
	 * 
	 * // if (null == gCategory) { // alert.setAlert(redirectAttributes,
	 * Alert.DANGER, // "Error! Invalid resource"); // return
	 * "redirect:/system/Diseasescategory"; // } DiseasesCategoryForm cForm =
	 * new DiseasesCategoryForm();
	 * //cForm.setParentCategoryId(gCategory.getCategoryId());
	 * //model.addAttribute("gCategory", gCategory); model.addAttribute("cForm",
	 * cForm); return "system/Diseasescategory/viewcategory"; }
	 */
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String add(Model model) {
		model.addAttribute("diseasestype",
				diseasesTypeBo.fetchAllByOrganisation());

		// model.addAttribute("categories",
		// DiseasesCategoryBo.fetcAll(userIdentity.getOrganisation().getId()));

		model.addAttribute("diseasesForm", new DiseasesForm());
		return "system/diseases/add";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("diseasesForm") DiseasesForm dForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			System.out.println("Errors here!!!");
			return "system/diseases/add";
		}

		try {

			Diseases Diseases = new Diseases();
			DiseasesCategory category = diseasesCategoryBo
					.getDiseasesCategoryById(dForm.getParentCategoryId());

			DiseasesType catType = this.diseasesTypeBo
					.getDiseasesTypeById(dForm.getDiseasestypeId());

			Diseases.setDiseasesCategory(category);
			Diseases.setOrganisation(userIdentity.getOrganisation());

			System.out.println("name");
			System.out.println(dForm.getDiseasesName());
			System.out.println("name");

			Diseases.setDiseasesName(dForm.getDiseasesName());

			System.out.println("desc");
			System.out.println(dForm.getDiseasesDescription());
			System.out.println("desc");

			Diseases.setDescription(dForm.getDiseasesDescription());

			Diseases.setCreatedBy(userIdentity.getUsername());
			Diseases.setDiseasesTypeId(catType);
			// category.setCreatedDate(new GregorianCalendar().getTime());

			// category.setModifiedBy(userIdentity.getUsername());

			// category.setModifiedDate(new GregorianCalendar().getTime());

			diseasesBo.save(Diseases);

			alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! "
					+ dForm.getDiseasesName() + " added Successfully!");
		} catch (Exception e) {

			alert.setAlert(redirectAttributes, Alert.DANGER,
					"May be Item exists " + e.getMessage());

		}

		return "redirect:/system/diseases/index";

	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	// @Layout(value = "layouts/form_wizard_layout")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletRequest httpRequest) {

		// DiseasesCategory gCategory =
		// DiseasesCategoryBo.getGlobalCategoryItemById(id);

		Diseases diseases = diseasesBo.getDiseasesById(id);

		// Integer parent = category.getCategoryId();

		if (diseases == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/diseases";
		}

		DiseasesForm dForm = new DiseasesForm();

		dForm.setParentCategoryId(diseases.getDiseasesCategory()
				.getCategoryId());
		dForm.setDiseasestypeId(diseases.getDiseasesType().getDiseasesTypeId());

		dForm.setDiseasesDescription(diseases.getDescription());
		dForm.setDiseasesName(diseases.getDiseasesName());

		model.addAttribute("categories", diseasesCategoryBo
				.fetchAllByOrganisationByCategoryType(diseases
						.getDiseasesType().getDiseasesTypeId()));
		model.addAttribute("diseasesType",
				diseasesTypeBo.fetchAllByOrganisation());

		model.addAttribute("diseasesForm", dForm);
		// model.addAttribute("gCategory", category);
		// auditor
		auditor.before(httpRequest, "Diseases", dForm);

		return "system/diseases/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("dForm") DiseasesForm dForm,
			BindingResult result, Model model, HttpServletRequest httpRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		try {
			dForm.setModifiedBy(userIdentity.getUsername());
			dForm.setModifiedDate(new GregorianCalendar().getTime());

			DiseasesCategory category = diseasesCategoryBo
					.getDiseasesCategoryById(dForm.getParentCategoryId());

			DiseasesType catType = this.diseasesTypeBo
					.getDiseasesTypeById(dForm.getDiseasestypeId());

			Diseases Diseases = diseasesBo.getDiseasesById(id);

			if (Diseases.getOrganisation() == userIdentity.getOrganisation()
					|| userIdentity.getOrganisation().getId() == 1) {

				Diseases.setDiseasesName(dForm.getDiseasesName());

				Diseases.setDescription(dForm.getDiseasesDescription());

				Diseases.setDiseasesCategory(category);

				Diseases.setOrganisation(userIdentity.getOrganisation());

				Diseases.setModifiedBy(userIdentity.getUsername());

				Diseases.setModifiedDate(new GregorianCalendar().getTime());
				Diseases.setDiseasesTypeId(catType);

				diseasesBo.update(Diseases);

				auditor.after(httpRequest, "Diseases", dForm,
						userIdentity.getUsername(), id);
				alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! "
						+ dForm.getDiseasesName() + " edited Successfully!");

			} else {
				alert.setAlert(redirectAttributes, Alert.DANGER,

				"You are not the owner of the item. you cannot edit it.");

			}
		} catch (Exception e) {

			alert.setAlert(redirectAttributes, Alert.DANGER,
					"May be Item exists " + e.getMessage());

		}

		return "redirect:/system/Diseases/index";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Integer id,
	// @ModelAttribute("cForm") DiseasesCategoryForm cForm,
			RedirectAttributes redirectAttributes) {
		Diseases diseases = diseasesBo.getDiseasesById(id);

		diseases.setModifiedBy(userIdentity.getUsername());
		diseases.setModifiedDate(new GregorianCalendar().getTime());
		diseases.setDeleted(true);
		diseasesBo.update(diseases);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Diagnosis  deleted");
		return "redirect:/system/diseases/index";
	}

	@RequestMapping(value = "/refreshDiseasescategory/{organisationId}", method = RequestMethod.GET)
	@ResponseBody
	public void refreshDiseasescategory(
			@PathVariable("organisationId") Integer organisationId, Model model)
			throws HibernateException, SQLException {

		System.out.println("inrefresh");

		Session session = sessionFactory.openSession();
		CallableStatement cs = null;
		cs = session.connection().prepareCall(
				"{? = call Diseasescategory_outerrecursive(?)}");

		cs.registerOutParameter(1, Types.INTEGER);
		cs.setInt(2, organisationId);

		cs.execute();
		System.out.println(cs.getInt(1));

		System.out.println("Done with the query");

	}

	@RequestMapping(value = "/fetchcategorybytype/{cattypeid}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String ajaxfetchcategory(
			@PathVariable("cattypeid") Integer cattypeid, Model model,
			RedirectAttributes redirectAttributes) {
		String itemcatStr = "<option value=''>Select Category</option>";

		// model.addAttribute("DiseasesType",DiseasesTypeBo.fetchAllByOrganisation());

		// model.addAttribute("categories",
		// DiseasesCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
		List<DiseasesCategory> list = diseasesCategoryBo
				.fetchAllByOrganisationByCategoryType(cattypeid);
		// System.out.println("Ajax Size Controller");
		// System.out.println(list.size());
		try {
			for (DiseasesCategory itemcat : list) {
				itemcatStr += "<option value='" + itemcat.getCategoryId()
						+ "'>" + itemcat.getCategoryName() + "</option>";
			}
		} catch (Exception e) {
		}

		return itemcatStr;
	}

}
