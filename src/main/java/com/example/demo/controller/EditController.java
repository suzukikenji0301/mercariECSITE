package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.AddItem;
import com.example.demo.domain.Category;
import com.example.demo.domain.ItemCategory;
import com.example.demo.form.EditForm;
import com.example.demo.service.CategoryService;
import com.example.demo.service.EditService;

@Controller
@RequestMapping("/edit")
public class EditController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private EditService editService;

//	@Autowired
//	private AddForm addForm;

	/**
	 * Item編集画面を表示します.
	 * 
	 * @param name 商品名
	 * @return 商品編集画面
	 */
	@GetMapping("/showEdit")
	private String showEdit(EditForm form, Model model, Integer id) {
		System.out.println("Editアクセス開始id = " + id);
		List<Category> bigcategoryList = categoryService.findByBigCategory();
		model.addAttribute("bigcategoryList", bigcategoryList);
		String confirmedMessage = (String) model.getAttribute("confirmed");
		model.addAttribute("confirmed", confirmedMessage);
		ItemCategory item = editService.showEdit(id);
		System.out.println("item" + item);
		model.addAttribute("item", item);
//		
//		String category = item.getCategory();
//		String[] categoryArray = category.split("/", 3);
//		String bigCategory = categoryArray[0];
//		String childCategory = categoryArray[1];
//		String grandCategory = categoryArray[2];
//		model.addAttribute("bigCategory", bigCategory);
//			System.out.println("bigCategory" + bigCategory);
//		model.addAttribute("childCategory", childCategory);
//			System.out.println("childCategory" + childCategory);
//		model.addAttribute("grandCategory ", grandCategory);
//			System.out.println("grandCategory" + grandCategory);
		return "edit";
	}

	/**
	 * 中カテゴリー情報を表示します.
	 * 
	 * @return childListに　childcategoryListのデータを詰めて返す
	 */
	@ResponseBody
	@PostMapping("/childCategory")
	public List<Category> childCategory(Integer bigCategory) {
		List<Category> childcategoryList = categoryService.findByChildCategory(bigCategory);
		System.out.println("/childCategoryの中身"+ bigCategory);
		return childcategoryList;
	}
	
	/**
	 * 小カテゴリー情報を表示します.
	 * 
	 * @return grandListに　grandcategoryListのデータを詰めて返す
	 */
	@ResponseBody
	@PostMapping("/grandCategory")
	public List<Category> findByGrandCategory(Integer childCategory) {
			System.out.println("/grandCategoryの中身"+ childCategory);
		List<Category> grandcategoryList = categoryService.findByGrandCategory(childCategory);
			System.out.println("grandList =" + grandcategoryList);
		return grandcategoryList;
	}
	
	/**
	 * 商品情報を更新します.
	 * @param id 
	 * 
	 * @param itemForm
	 * @return
	 */
	@PostMapping("/update")
	public String update(@Validated EditForm form, BindingResult result,RedirectAttributes redirectAttributes, Model model, Integer id) {
		System.out.println("バリデーション開始");
		System.out.println("Edit update" + form);
		if (result.hasErrors()) {
			return showEdit(form,model,id);
		}
		System.out.println("バリデーション終了");
		ItemCategory item = new ItemCategory();
		item.setId(id);
		item.setName(form.getName());
		item.setPrice(Double.parseDouble(form.getPrice()));
		item.setCategory(Integer.parseInt(form.getBigcategory()));
		item.setCategory(Integer.parseInt(form.getChildcategory()));
		item.setCategory(Integer.parseInt(form.getGrandcategory()));
		item.setBrand(form.getBrand());
		item.setCondition(form.getCondition());
		item.setDescription(form.getDescription());
		BeanUtils.copyProperties(form, item);
		 System.out.println("makeItems（）itemの中身" + item);
			editService.update(item);
			redirectAttributes.addFlashAttribute("complete", "変更が完了しました");
			return "editFinish";
		}
		
//		ItemCategory item = new ItemCategory();
//		item.setName(form.getName());
//		item.setPrice(Integer.parseInt(form.getPrice()));
//		BeanUtils.copyProperties(form, item);
//		item.setCategory(Integer.parseInt(form.getGrandcategory()));
//		editService.update(item);
//		System.out.println("update item " + item);
//		redirectAttributes.addFlashAttribute("Edit confirmed", ItemCategory.class + "が新しく在庫に追加されました!");
//		System.out.println("送信された");
//		return "editFinish";
	}

	


