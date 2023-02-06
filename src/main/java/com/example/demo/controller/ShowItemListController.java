package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Item;
import com.example.demo.service.ItemService;


/**
 * Itemデータを操作するコントローラー.
 * 変遷パス　↓
 * http://localhost:8080/mercari/items/showItemList
 * 
 * @author kenji.suzuki
 *
 */
@Controller
@RequestMapping("/items")
public class ShowItemListController {
	
	
		@Autowired
		private ItemService itemService;
//		
//		@Autowired
//		private HttpSession session;
		
		//1ページに表示する商品を30件にする
//		private final int ViewItems = 30;
		
		/**
		 * Item情報一覧を表示します.
		 *	
		 * @param name 商品名
		 * @return　商品一覧画面
		 */
		@GetMapping("/showItemList")
		public String showItemPage(Model model) {
			List<Item> itemList = itemService.showItem();
			model.addAttribute("itemList", itemList);
			return "list";
		}
}
