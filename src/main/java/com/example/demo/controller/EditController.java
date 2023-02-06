package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditController {

	/**
	 * AddItem情報を表示します.
	 *	
	 * @param name 商品名
	 * @return　商品一覧画面
	 */
	@GetMapping("/edit")
	public String showEditPage(Model model) {
//		List<add> itemList = itemService.showItem();
//		model.addAttribute("addList", addList);
		return "edit";
	}
}
