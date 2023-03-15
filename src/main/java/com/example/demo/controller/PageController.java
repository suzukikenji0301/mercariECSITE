package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("")
@Controller
public class PageController {
	
//	@Autowired
//	private ItemService itemService;
//	
//	@Autowired
//	private PageConfig pageConfig;	
//	
//	/**
//	 * 登録されている商品情報.
//	 */
//	private List<Item> itemList;
//	
//	/**
//	 * offsetからnum件のアイテムリストを返す
//	 * @param page
//	 * @return
//	 */
//	public List<Item> getItemList(PageConfig page) {
//		page.config((long) itemList.size());
//		List<Item> itemList = itemService.showItem();
//		
//		int offset = page.offset();
//		int num = PageConfig.OUTPUT_NUM;
//
//		// recordNum % OUTPUT_NUM != 0の場合リストの外を参照する
//		try {
//			for (int i = offset; i < offset + num; i++) {
//				itemList.add(this.itemList.get(i));
//			}
//		} catch (IndexOutOfBoundsException e) {
//			e.printStackTrace();
//		}
//		return itemList;
//	}
}
