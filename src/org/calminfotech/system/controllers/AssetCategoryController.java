package org.calminfotech.system.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.AssetCatgoryBo;
import org.calminfotech.system.forms.AssetCategoryForm;
import org.calminfotech.system.models.AssetCategory;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.annotations.Layout;
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
@RequestMapping(value = "/system/assetcategory")
public class AssetCategoryController {
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private Auditor auditor;
	
	@Autowired
	private AssetCatgoryBo assetCategoryBo;
	
	//@RequestMapping(value = "/index", method = RequestMethod.GET)
	@RequestMapping(value = { "", "/" })
	@Layout(value = "layouts/datatable")
	public String index(Model model){		
		model.addAttribute("assetCategory", assetCategoryBo.fetchAll());
		return "system/assetcategory/index";		
	}
	
	@RequestMapping(value = "/add")
	public String add(Model model, AssetCategoryForm assetCategoryForm){
		
		AssetCategory assetCategory = new AssetCategory();
		assetCategoryForm.setAssetCatName(assetCategory.getAssetCatName());
		assetCategoryForm.setAssetCatDesc(assetCategory.getAssetCatDesc());
		model.addAttribute("assetCategoryForm", assetCategoryForm);		
		return "system/assetcategory/add";		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAction(@Valid @ModelAttribute("assetCategoryForm") AssetCategoryForm assetCatForm,
							BindingResult result, Model model,
							RedirectAttributes redirectAttributes){
		
		System.out.println("AM here add Action");
		
		if (result.hasErrors()) {			
			return "system/assetcategory/add";
		}
		
		AssetCategory assetCategory = new AssetCategory();
		
		assetCategory.setAssetCatName(assetCatForm.getAssetCatName());
		assetCategory.setAssetCatDesc(assetCatForm.getAssetCatDesc());
		
		
		assetCategoryBo.save(assetCategory);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! Asset Category Added Successfully! ");
		return "redirect:/system/assetcategory";
		
	}
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model){
		AssetCategory assetCat = assetCategoryBo.getAssetCategoryId(id);
		model.addAttribute("assetCat", assetCat);
		return "system/assetcategory/view";		
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id") int assestCatId, Model model,
					   RedirectAttributes redirectAttributes, 
					   HttpServletRequest request){
		
		AssetCategory assetCategory = assetCategoryBo.getAssetCategoryId(assestCatId);
		if(null == assetCategory){
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/assetcategory";
		}
		
		AssetCategoryForm assetCatForm = new AssetCategoryForm();
		
		assetCatForm.setAssetCatId(assetCategory.getAssetCatId());
		assetCatForm.setAssetCatName(assetCategory.getAssetCatName());
		assetCatForm.setAssetCatDesc(assetCategory.getAssetCatDesc());
		
		model.addAttribute("assetCatForm", assetCatForm);
		
		auditor.before(request, "Asset Category Form", assetCatForm);
		
		return "system/assetcategory/edit";
		
	}
	
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("assetCatForm") AssetCategoryForm assetCatForm,
						BindingResult result, Model model,
						RedirectAttributes redirectAttributes, HttpServletRequest request){
		
		AssetCategory assetCategory = assetCategoryBo.getAssetCategoryId(assetCatForm.getAssetCatId());
		
		assetCategory.setAssetCatName(assetCatForm.getAssetCatName());
		assetCategory.setAssetCatDesc(assetCatForm.getAssetCatDesc());
		
		assetCategoryBo.update(assetCategory);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! Asset Category Updated Successfully! ");
		
		return "redirect:/system/assetcategory";		
				
	}
	
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		AssetCategory assetCategory = assetCategoryBo.getAssetCategoryId(id);

		if (null == assetCategory) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/assetcategory";
		}

		AssetCategoryForm assetCategoryForm = new AssetCategoryForm();
		assetCategoryForm.setAssetCatId(assetCategory.getAssetCatId());

		model.addAttribute("assetCategory", assetCategory);
		model.addAttribute("assetCategoryForm", assetCategoryForm);

		return "system/assetcategory/delete";
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("assetCategoryForm") AssetCategoryForm assetCategoryForm,
			RedirectAttributes redirectAttributes) {

		AssetCategory assetCategory = assetCategoryBo.getAssetCategoryId(assetCategoryForm.getAssetCatId());
		if (null == assetCategory) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/assetcategory";
		}

		assetCategoryBo.delete(assetCategory);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Asset Category profile deleted");

		return "redirect:/system/assetcategory";
	}
	
	
	

}
