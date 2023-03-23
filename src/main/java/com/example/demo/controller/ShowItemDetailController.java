package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Item;
import com.example.demo.service.DetailService;

/**
 * 商品詳細画面を操作するコントローラー.
 * 
 * @author kenji.suzuki
 *
 */
@Controller
@RequestMapping("/")
public class ShowItemDetailController {

	@Autowired
	private DetailService detailService;
	
	/**
	 * ItemDetail画面を表示させます.
	 * 
	 * @param id
	 * @param model
	 * @return DetailHTML
	 */
	@GetMapping("/itemDetail")
	public String ItemDetail(int id, Model model) {
		Item itemDetail = detailService.showItemDetail(id);
		model.addAttribute(itemDetail);
			System.out.println(itemDetail);
		return "detail";
	}
	
	
}
