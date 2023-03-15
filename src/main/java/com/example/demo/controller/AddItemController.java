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
import com.example.demo.form.AddForm;
import com.example.demo.service.AddService;
import com.example.demo.service.CategoryService;

@Controller
@RequestMapping("/add")
public class AddItemController {

	@Autowired
	private AddService addService;
	
	@Autowired
	private CategoryService categoryService;

	/**
	 * AddItem画面を表示します.
	 * 
	 * @param name 商品名
	 * @return 商品情報更新画面
	 */
	@GetMapping("addList")
	public String showAddPage(AddForm form,Model model) {
		List<Category> bigcategoryList = categoryService.findByBigCategory();
		model.addAttribute("bigcategoryList", bigcategoryList);
		String confirmedMessage = (String) model.getAttribute("confirmed");
		model.addAttribute("confirmed", confirmedMessage);
		return "add";
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
	 * Item登録をします.
	 * 
	 * @param form 商品追加
	 * @return 一覧画面にリダイレクト
	 */
	@PostMapping("/insertAdd")
	public String insertAdd(@Validated AddForm form, BindingResult result, Model model,RedirectAttributes redirectAttributes) {
		System.out.println("Add" + form);
		if (result.hasErrors()) {
			return showAddPage(form, model);
		}
		AddItem add = new AddItem();
		add.setPrice(Double.parseDouble(form.getPrice()));
		BeanUtils.copyProperties(form, add);
		add.setCategory(Integer.parseInt(form.getGrandcategory()));
		addService.insertAdd(add);
		redirectAttributes.addFlashAttribute("confirmed", AddItem.class + "が新しく在庫に追加されました!");
		System.out.println("送信された");
		return "addFinish";
	}
	
}