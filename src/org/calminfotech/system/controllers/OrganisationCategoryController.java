package org.calminfotech.system.controllers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.OrganisationBo;
import org.calminfotech.system.boInterface.OrganisationCategoryBo;
import org.calminfotech.system.boInterface.OrganisationCompanyBo;
import org.calminfotech.system.boInterface.OrganisationTypeBo;
import org.calminfotech.system.forms.OrganisationCategoryForm;
import org.calminfotech.system.models.OrganisationCategory;
import org.calminfotech.system.models.OrganisationType;
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
@RequestMapping(value = "/system/organisationcategory")
@Layout(value = "layouts/form_wizard_layout")
public class OrganisationCategoryController {

	@Autowired
	private OrganisationCategoryBo organisationCategoryBo;

	@Autowired
	private OrganisationBo organisationBo;

	@Autowired
	private OrganisationCompanyBo organisationCoyBo;

	@Autowired
	private OrganisationTypeBo organisationTypeBo;

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
	public String index(Model model, RedirectAttributes redirectAttributes) {
		if (userIdentity.getOrganisation().getOrgCoy().getId() == 1) {
			model.addAttribute("category", organisationCategoryBo
					.fetchTop50byOrganisation(userIdentity.getOrganisation()
							.getId()));
			model.addAttribute("orgId", userIdentity.getOrganisation().getId());
			return "system/organisationcategory/index";

		}

		else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@RequestMapping(value = { "/index/all" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	// @Layout(value = "layouts/form_wizard_layout")
	public String indexall(Model model, RedirectAttributes redirectAttributes) {

		if (userIdentity.getOrganisation().getOrgCoy().getId() == 1) {
			model.addAttribute("category", organisationCategoryBo
					.fetchAll(userIdentity.getOrganisation().getId()));
			model.addAttribute("orgId", userIdentity.getOrganisation().getId());
			return "system/organisationcategory/indexall";
		}

		else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@RequestMapping(value = "/view/{categoryId}")
	@Layout("layouts/datatable")
	public String view(@PathVariable("categoryId") Integer categoryId,
			Model model, RedirectAttributes redirectAttributes) {

		if (userIdentity.getOrganisation().getOrgCoy().getId() == 1) {
			// OrganisationCategory gCategory =
			// OrganisationCategoryBo.getOrganisationCategoryById(categoryId);

			// if (null == gCategory) {
			// alert.setAlert(redirectAttributes, Alert.DANGER,
			// "Error! Invalid resource");
			// return "redirect:/system/Organisationcategory";
			// }
			OrganisationCategoryForm cForm = new OrganisationCategoryForm();
			// cForm.setParentCategoryId(gCategory.getCategoryId());
			// model.addAttribute("gCategory", gCategory);
			model.addAttribute("organisationCategoryForm", cForm);
			return "system/organisationcategory/viewcategory";
		}

		else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String add(Model model, RedirectAttributes redirectAttributes) {

		if (userIdentity.getOrganisation().getOrgCoy().getId() == 1) {
			model.addAttribute("organisationtype",
					organisationTypeBo.fetchAllByOrganisation());

			// model.addAttribute("categories",
			// OrganisationCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));

			model.addAttribute("organisationCategoryForm",
					new OrganisationCategoryForm());
			return "system/organisationcategory/add";
		}

		else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("organisationCategoryForm") OrganisationCategoryForm cForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (userIdentity.getOrganisation().getOrgCoy().getId() == 1) {
			if (result.hasErrors()) {
				System.out.println("Errors here!!!");
				return "system/organisationcategory/add";
			}

			OrganisationCategory category = new OrganisationCategory();
			System.out.print("Form value");
			System.out.print(cForm.getCategoryName());

			System.out.print("org ide");

			System.out.print(userIdentity.getOrganisation().getId());

			OrganisationType catType = this.organisationTypeBo
					.getOrganisationTypeById(cForm.getOrganisationTypeId());

			category.setParentCategoryId(cForm.getParentCategoryId());

			category.setCategoryName(cForm.getCategoryName());

			category.setDescription(cForm.getCategoryDescription());

			category.setCreatedBy(userIdentity.getUsername());
			category.setOrganisationTypeId(catType);
			// category.setCreatedDate(new GregorianCalendar().getTime());

			// category.setModifiedBy(userIdentity.getUsername());

			// category.setModifiedDate(new GregorianCalendar().getTime());

			organisationCategoryBo.save(category);

			alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! "
					+ cForm.getCategoryName() + " added Successfully!");
			return "redirect:/system/organisationcategory/index";
		}

		else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	// @Layout(value = "layouts/form_wizard_layout")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletRequest httpRequest) {
		if (userIdentity.getOrganisation().getOrgCoy().getId() == 1) {

			// OrganisationCategory gCategory =
			// OrganisationCategoryBo.getGlobalCategoryItemById(id);

			OrganisationCategory category = organisationCategoryBo
					.getOrganisationCategoryById(id);

			// Integer parent = category.getCategoryId();

			if (category == null) {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Error! Invalid resource");
				return "redirect:/system/organisationcategory";
			}

			OrganisationCategoryForm cForm = new OrganisationCategoryForm();

			cForm.setCategoryId(category.getCategoryId());
			cForm.setOrganisationTypeId(category.getOrganisationTypeId()
					.getOrganisationTypeId());

			cForm.setParentCategoryId(category.getParentCategoryId());

			cForm.setCategoryDescription(category.getDescription());
			cForm.setCategoryName(category.getCategoryName());

			model.addAttribute("categories", organisationCategoryBo
					.fetchAllByOrganisationByCategoryType(category
							.getOrganisationTypeId().getOrganisationTypeId()));
			model.addAttribute("organisationtype",
					organisationTypeBo.fetchAllByOrganisation());

			model.addAttribute("organisationCategoryForm", cForm);
			// model.addAttribute("gCategory", category);
			// auditor
			auditor.before(httpRequest, "OrganisationCategory", cForm);

			return "system/organisationcategory/edit";
		}

		else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(
			@PathVariable("id") Integer id,
			@Valid @ModelAttribute("organisationCategoryForm") OrganisationCategoryForm cForm,
			BindingResult result, Model model, HttpServletRequest httpRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (userIdentity.getOrganisation().getOrgCoy().getId() == 1) {

			// OrganisationCategory category =
			// this.OrganisationCategoryBo.getOrganisationCategoryById(cForm.getParentCategoryId());

			cForm.setModifiedBy(userIdentity.getUsername());
			cForm.setModifiedDate(new GregorianCalendar().getTime());

			OrganisationType catType = this.organisationTypeBo
					.getOrganisationTypeById(cForm.getOrganisationTypeId());

			OrganisationCategory category = organisationCategoryBo
					.getOrganisationCategoryById(id);

			category.setParentCategoryId(cForm.getParentCategoryId());

			// category.ssetOrganisation(this.organisationBo.getOrganisationById(1));

			category.setCategoryName(cForm.getCategoryName());

			category.setDescription(cForm.getCategoryDescription());

			category.setModifiedBy(userIdentity.getUsername());
			category.setOrganisationTypeId(catType);

			organisationCategoryBo.update(category);

			auditor.after(httpRequest, "OrganisationCategory", cForm,
					userIdentity.getUsername(), id);
			alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! "
					+ cForm.getCategoryName() + " edited Successfully!");
			return "redirect:/system/organisationcategory/index";
		}

		else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	// @RequestMapping(value = "/delete/{id}")
	// public String delete(@PathVariable("id") Integer id, Model model,
	// RedirectAttributes redirectAttributes) {
	// OrganisationCategory gCategory = OrganisationCategoryBo
	// .getOrganisationCategoryById(id);
	// // if (null == gCategory) {
	// alert.setAlert(redirectAttributes, Alert.DANGER,
	// "Error! Invalid resource");
	// return "redirect:/system/Organisationcategory/index";
	// }

	// OrganisationCategoryForm cForm = new OrganisationCategoryForm();
	// cForm.setParentCategoryId(gCategory.getCategoryId());
	// cForm.setCategoryName(gCategory.getCategoryName());
	// model.addAttribute("gCategory", gCategory);
	// model.addAttribute("cForm", cForm);
	// return "system/Organisationcategory/delete";
	// }

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String confirmDelete(@PathVariable("id") Integer id,
	// @ModelAttribute("cForm") OrganisationCategoryForm cForm,
			RedirectAttributes redirectAttributes) {
		if (userIdentity.getOrganisation().getOrgCoy().getId() == 1) {

			OrganisationCategory gCategory = organisationCategoryBo
					.getOrganisationCategoryById(id);
			// if (null == gCategory) {
			// alert.setAlert(redirectAttributes, Alert.DANGER,
			// "Error! Invalid resource");
			// return "redirect:/system/Organisationcategory/index";
			// }
			gCategory.setModifiedBy(userIdentity.getUsername());
			gCategory.setModifiedDate(new GregorianCalendar().getTime());
			gCategory.setDeleted(true);
			organisationCategoryBo.update(gCategory);

			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Global-Item Category deleted");
			return "redirect:/system/organisationcategory/index";
		}

		else

		{
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"You cannot do this");

			return "redirect:/";

		}
	}

	@RequestMapping(value = "/refreshorganisationcategory/{organisationId}", method = RequestMethod.GET)
	@ResponseBody
	public void refreshOrganisationcategory(
			@PathVariable("organisationId") Integer organisationId, Model model)
			throws HibernateException, SQLException {

		System.out.println("inrefresh");

		Session session = sessionFactory.openSession();
		CallableStatement cs = null;
		cs = session.connection().prepareCall(
				"{? = call organisationcategory_outerrecursive(?)}");

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

		// model.addAttribute("OrganisationType",OrganisationTypeBo.fetchAllByOrganisation());

		// model.addAttribute("categories",
		// OrganisationCategoryBo.fetchAll(userIdentity.getOrganisation().getId()));
		List<OrganisationCategory> list = organisationCategoryBo
				.fetchAllByOrganisationByCategoryType(cattypeid);
		// System.out.println("Ajax Size Controller");
		// System.out.println(list.size());
		try {
			for (OrganisationCategory itemcat : list) {
				itemcatStr += "<option value='" + itemcat.getCategoryId()
						+ "'>" + itemcat.getCategoryName() + "</option>";
			}
		} catch (Exception e) {
		}

		return itemcatStr;
	}
}
