package org.calminfotech.system.controllers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.DiseasesCategoryBo;
import org.calminfotech.system.boInterface.DiseasesTypeBo;
import org.calminfotech.system.forms.DiseasesCategoryForm;
import org.calminfotech.system.models.DiseasesCategory;
import org.calminfotech.system.models.DiseasesType;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
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
@RequestMapping(value = "/system/diseasescategory")
@Layout(value = "layouts/form_wizard_layout")
public class DiseasesCategoryController {

	@Autowired
	private DiseasesCategoryBo diseasesCategoryBo;

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

	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	// @Layout(value = "layouts/form_wizard_layout")
	public String index(Model model) {
		model.addAttribute("category", diseasesCategoryBo
				.fetchTop50byOrganisation(userIdentity.getOrganisation()
						.getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/diseasescategory/index";
	}

	@RequestMapping(value = { "/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	// @Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model) {
		model.addAttribute("category", diseasesCategoryBo.fetchAll(userIdentity
				.getOrganisation().getId()));
		model.addAttribute("orgId", userIdentity.getOrganisation().getId());
		return "system/diseasescategory/indexall";
	}

	@RequestMapping(value = "/view/{categoryId}")
	@Layout("layouts/datatable")
	public String view(@PathVariable("categoryId") Integer categoryId,
			Model model, RedirectAttributes redirectAttributes) {
		// DiseasesCategory gCategory =
		// DiseasesCategoryBo.getDiseasesCategoryById(categoryId);

		// if (null == gCategory) {
		// alert.setAlert(redirectAttributes, Alert.DANGER,
		// "Error! Invalid resource");
		// return "redirect:/system/Diseasescategory";
		// }
		DiseasesCategoryForm cForm = new DiseasesCategoryForm();
		// cForm.setParentCategoryId(gCategory.getCategoryId());
		// model.addAttribute("gCategory", gCategory);
		model.addAttribute("diseasesCategoryForm", cForm);
		return "system/diseasescategory/viewcategory";
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String add(Model model) {
		model.addAttribute("diseasestype",
				diseasesTypeBo.fetchAllByOrganisation());

		// model.addAttribute("categories",
		// DiseasesCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));

		model.addAttribute("diseasesCategoryForm", new DiseasesCategoryForm());
		return "system/diseasescategory/add";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("diseasesCategoryForm") DiseasesCategoryForm cForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		try {

			if (result.hasErrors()) {
				System.out.println("Errors here!!!");
				return "system/diseasescategory/add";
			}

			DiseasesCategory category = new DiseasesCategory();
			System.out.print("Form value");
			System.out.print(cForm.getCategoryName());

			System.out.print("org ide");

			System.out.print(userIdentity.getOrganisation().getId());

			DiseasesType catType = this.diseasesTypeBo
					.getDiseasesTypeById(cForm.getDiseasesTypeId());

			category.setParentCategoryId(cForm.getParentCategoryId());

			category.setOrganisation(userIdentity.getOrganisation());

			category.setCategoryName(cForm.getCategoryName());

			category.setDescription(cForm.getCategoryDescription());

			category.setCreatedBy(userIdentity.getUsername());
			category.setDiseasesTypeId(catType);
			// category.setCreatedDate(new GregorianCalendar().getTime());

			// category.setModifiedBy(userIdentity.getUsername());

			// category.setModifiedDate(new GregorianCalendar().getTime());

			diseasesCategoryBo.save(category);

			alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! "
					+ cForm.getCategoryName() + " added Successfully!");

		}

		catch (Exception e) {

			alert.setAlert(redirectAttributes, Alert.DANGER,
					"May be Item exists " + e.getMessage());

		}

		return "redirect:/system/diseasescategory/index";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	// @Layout(value = "layouts/form_wizard_layout")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletRequest httpRequest) {
		// DiseasesCategory gCategory =
		// DiseasesCategoryBo.getGlobalCategoryItemById(id);

		DiseasesCategory category = diseasesCategoryBo
				.getDiseasesCategoryById(id);

		// Integer parent = category.getCategoryId();

		if (category == null) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/diseasescategory";
		}

		DiseasesCategoryForm cForm = new DiseasesCategoryForm();

		cForm.setCategoryId(category.getCategoryId());
		cForm.setDiseasesTypeId(category.getDiseasesTypeId()
				.getDiseasesTypeId());

		cForm.setParentCategoryId(category.getParentCategoryId());

		cForm.setCategoryDescription(category.getDescription());
		cForm.setCategoryName(category.getCategoryName());

		model.addAttribute("categories", diseasesCategoryBo
				.fetchAllByOrganisationByCategoryType(category
						.getDiseasesTypeId().getDiseasesTypeId()));
		model.addAttribute("diseasestype",
				diseasesTypeBo.fetchAllByOrganisation());

		model.addAttribute("diseasesCategoryForm", cForm);
		// model.addAttribute("gCategory", category);
		// auditor
		auditor.before(httpRequest, "DiseasesCategory", cForm);

		return "system/diseasescategory/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(
			@PathVariable("id") Integer id,
			@Valid @ModelAttribute("diseasesCategoryForm") DiseasesCategoryForm cForm,
			BindingResult result, Model model, HttpServletRequest httpRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		// DiseasesCategory category =
		// this.DiseasesCategoryBo.getDiseasesCategoryById(cForm.getParentCategoryId());

		try {
			cForm.setModifiedBy(userIdentity.getUsername());
			cForm.setModifiedDate(new GregorianCalendar().getTime());

			DiseasesType catType = this.diseasesTypeBo
					.getDiseasesTypeById(cForm.getDiseasesTypeId());

			DiseasesCategory category = diseasesCategoryBo
					.getDiseasesCategoryById(id);

			if (category.getOrganisation() == userIdentity.getOrganisation()
					|| userIdentity.getOrganisation().getId() == 1) {
				category.setParentCategoryId(cForm.getParentCategoryId());

				category.setOrganisation(userIdentity.getOrganisation());

				category.setCategoryName(cForm.getCategoryName());

				category.setDescription(cForm.getCategoryDescription());

				category.setModifiedBy(userIdentity.getUsername());
				category.setDiseasesTypeId(catType);

				diseasesCategoryBo.update(category);

				auditor.after(httpRequest, "DiseasesCategory", cForm,
						userIdentity.getUsername(), id);
				alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! "
						+ cForm.getCategoryName() + " edited Successfully!");
			} else {

				alert.setAlert(redirectAttributes, Alert.DANGER,

				"You are not the owner of the item. you cannot edit it.");
			}
		} catch (Exception e) {

			alert.setAlert(redirectAttributes, Alert.DANGER,
					"May be Item exists " + e.getMessage());

		}

		return "redirect:/system/diseasescategory/index";

	}

	// @RequestMapping(value = "/delete/{id}")
	// public String delete(@PathVariable("id") Integer id, Model model,
	// RedirectAttributes redirectAttributes) {
	// DiseasesCategory gCategory = DiseasesCategoryBo
	// .getDiseasesCategoryById(id);
	// // if (null == gCategory) {
	// alert.setAlert(redirectAttributes, Alert.DANGER,
	// "Error! Invalid resource");
	// return "redirect:/system/Diseasescategory/index";
	// }

	// DiseasesCategoryForm cForm = new DiseasesCategoryForm();
	// cForm.setParentCategoryId(gCategory.getCategoryId());
	// cForm.setCategoryName(gCategory.getCategoryName());
	// model.addAttribute("gCategory", gCategory);
	// model.addAttribute("cForm", cForm);
	// return "system/Diseasescategory/delete";
	// }

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Integer id,
	// @ModelAttribute("cForm") DiseasesCategoryForm cForm,
			RedirectAttributes redirectAttributes) {
		DiseasesCategory gCategory = diseasesCategoryBo
				.getDiseasesCategoryById(id);
		// if (null == gCategory) {
		// alert.setAlert(redirectAttributes, Alert.DANGER,
		// "Error! Invalid resource");
		// return "redirect:/system/Diseasescategory/index";
		// }

		gCategory.setModifiedBy(userIdentity.getUsername());
		gCategory.setModifiedDate(new GregorianCalendar().getTime());
		gCategory.setDeleted(true);
		diseasesCategoryBo.update(gCategory);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Global-Item Category deleted");
		return "redirect:/system/diseasescategory/index";
	}

	@RequestMapping(value = "/refreshdiseasescategory/{organisationId}", method = RequestMethod.GET)
	@ResponseBody
	public void refreshDiseasescategory(
			@PathVariable("organisationId") Integer organisationId, Model model)
			throws HibernateException, SQLException {

		System.out.println("inrefresh");

		Session session = sessionFactory.openSession();
		CallableStatement cs = null;
		cs = session.connection().prepareCall(
				"{? = call diseasescategory_outerrecursive(?)}");

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
