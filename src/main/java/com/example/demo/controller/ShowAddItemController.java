package com.example.demo.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Add;
import com.example.demo.form.AddForm;
import com.example.demo.service.AddService;

@Controller
@RequestMapping("/")
public class ShowAddItemController {

	@Autowired
	private AddService addService;

	/**
	 * AddItem画面を表示します.
	 * 
	 * @param name 商品名
	 * @return 商品情報更新画面
	 */
	@GetMapping("addList")
	public String showAddPage(AddForm form) {
		return "add";
	}

	/**
	 * 登録します.
	 * 
	 * @param form お問い合わせフォーム
	 * @return 一覧画面にリダイレクト
	 */
	@PostMapping("/insertAdd")
	public String insertAdd(@Validated AddForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return showAddPage(form);
		}
		Add add = new Add();
		BeanUtils.copyProperties(form, add);
		addService.insertAdd(add);
		System.out.println("送信された");
		return "AddFinish";
	}
}