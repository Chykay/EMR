package org.calminfotech.system.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.AssetCatgoryBo;
import org.calminfotech.system.boInterface.AssetsBoInterface;
import org.calminfotech.system.forms.AssetsForm;
import org.calminfotech.system.models.AssetCategory;
import org.calminfotech.system.models.Assets;
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
@RequestMapping(value = "/system/assets")
public class AssetsController {
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private Auditor auditor;
	
	@Autowired
	private AssetsBoInterface assetsBo;
	
	@Autowired
	private AssetCatgoryBo assetCategoryBo;
	
	
	@RequestMapping(value = { "", "/" })
	@Layout(value = "layouts/datatable")
	public String index(Model model){		
		model.addAttribute("assets", assetsBo.fetchAll());
		return "system/assets/index";		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model, AssetsForm assetsForm){		
		
		System.out.println("Am here before ");
		
		//AssetCategory assetCat = assetCategoryBo.getAssetCategoryId(assetsForm.getAssetId());
		
		System.out.println("Am here after ");
		
		//System.out.println("Assets Category Id: " + assetCat.getAssetCatId());
		
		Assets assets = new Assets();
		assetsForm.setAssetName(assets.getAssetName());
		assetsForm.setAssetQty(assets.getAssetQty());
		assetsForm.setAssetDesc(assets.getAssetDesc());
		assetsForm.setAssetYearBought(assets.getAssetYearBought());
		assetsForm.setAssetYearExpired(assets.getAssetYearExpired());
		
		
		//assetsForm.setAssetCategory(assetCat.getAssetCatId());	
		
		model.addAttribute("assetCategoryList", assetCategoryBo.fetchAll());
		model.addAttribute("assetsForm", assetsForm);		
		
		return "system/assets/add";		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAction(@Valid @ModelAttribute("assetsForm") AssetsForm assetsForm,
							BindingResult result, Model model,
							RedirectAttributes redirectAttributes){		
	
		
		if (result.hasErrors()) {			
			return "system/assets/add";
		}
		
		Assets assets = new Assets();
		
		assets.setAssetName(assetsForm.getAssetName());
		assets.setAssetDesc(assetsForm.getAssetDesc());
		assets.setAssetQty(assetsForm.getAssetQty());
		assets.setAssetYearBought(assetsForm.getAssetYearBought());
		assets.setAssetYearExpired(assetsForm.getAssetYearExpired());
		assets.setAssetCategory(assetCategoryBo.getAssetCategoryId(assetsForm.getAssetCategory()));		
		
		assetsBo.save(assets);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! Assets Added Successfully! ");
		return "redirect:/system/assets";
		
	}
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Integer id, Model model){
		Assets assets = assetsBo.getAssetsId(id);
		model.addAttribute("assets", assets);
		return "system/assets/view";		
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id") int assetId, Model model,
					   RedirectAttributes redirectAttributes, 
					   HttpServletRequest request){
		
		Assets assets = assetsBo.getAssetsId(assetId);
		if(null == assets){
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/assets";
		}
		AssetCategory assetCat = assetCategoryBo.getAssetCategoryId(assets.getAssetId());
		AssetsForm assetsForm = new AssetsForm();
		
		assets.setAssetId(assetsForm.getAssetId());
		assetsForm.setAssetName(assets.getAssetName());
		assetsForm.setAssetDesc(assets.getAssetDesc());
		assetsForm.setAssetQty(assets.getAssetQty());
		assetsForm.setAssetYearBought(assets.getAssetYearBought());
		assetsForm.setAssetYearExpired(assets.getAssetYearExpired());
		assetsForm.setAssetCategory(assetCat.getAssetCatId());
		
		model.addAttribute("assetsForm", assetsForm);
		model.addAttribute("assetCategoryList", assetCategoryBo.fetchAll());
		auditor.before(request, "Assets Form", assetsForm);
		
		return "system/assets/edit";
		
	}
	
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("assetsForm") AssetsForm assetsForm,
						BindingResult result, Model model,
						RedirectAttributes redirectAttributes, HttpServletRequest request){
		
		System.out.println("Am here!");
		
		Assets assets = assetsBo.getAssetsId(assetsForm.getAssetId());
		
		System.out.println("Am here after");
		System.out.println("Assets Id is " + assets.getAssetId());
		
		assets.setAssetName(assetsForm.getAssetName());
		assets.setAssetDesc(assetsForm.getAssetDesc());
		assets.setAssetQty(assetsForm.getAssetQty());
		assets.setAssetYearBought(assetsForm.getAssetYearBought());
		assets.setAssetYearExpired(assetsForm.getAssetYearExpired());
		assets.setAssetCategory(assetCategoryBo.getAssetCategoryId(assetsForm.getAssetId()));
		
		assetsBo.update(assets);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! Assets Updated Successfully! ");
		
		return "redirect:/system/assets";		
				
	}
	
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Assets assets = assetsBo.getAssetsId(id);

		if (null == assets) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/assets";
		}

		AssetsForm assetsForm = new AssetsForm();
		assetsForm.setAssetId(assets.getAssetId());

		model.addAttribute("assets", assets);
		model.addAttribute("assetsForm", assetsForm);

		return "system/assets/delete";
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("assetsForm") AssetsForm assetsForm,
			RedirectAttributes redirectAttributes) {

		Assets assets = assetsBo.getAssetsId(assetsForm.getAssetId());
		if (null == assets) {
			alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/assets";
		}

		assetsBo.delete(assets);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Assets profile deleted");

		return "redirect:/system/assets";
	}
	
	
	

}
