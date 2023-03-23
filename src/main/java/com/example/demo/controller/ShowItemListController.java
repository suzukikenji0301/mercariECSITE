package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.form.ItemSearchForm;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ItemService;

/**
 * 商品情報を操作するコントローラー. 
 * 変遷パス 	↓
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
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ItemRepository itemRepository;
	
		// １ページの最大表示件数
		public final int OUT_PUT_NUM = 30;
		
		// itemsテーブルのレコード総数
		private int recordNum;
		
		// 現在のページ
		private int currentPage = 1;
		
		// 全レコードの件数から算出されるページ数（recordNum/outputNum）
		private int maxPage;
		
		/**
	 * Item情報一覧を表示します.
	 * 
	 * @param name 商品名
	 * @return 商品一覧画面
	 */
	@GetMapping("/showItemList")
	public String showItemList(ItemSearchForm form, Model model) {
		List<Category> bigCategoryList = categoryService.findByBigCategory();
		model.addAttribute("bigCategoryList", bigCategoryList);	
		recordNum = itemRepository.recordNum();
		maxPage = recordNum / OUT_PUT_NUM;
		model.addAttribute("maxPage", maxPage);
		List<Item> itemList = itemService.showItemList(OUT_PUT_NUM);
		model.addAttribute("itemList", itemList);
		return "list";
	}
	
	/**
	 * Searchボタンを押して商品検索を行い表示します.
	 * 
	 * @param name　商品名
	 * @return　商品情報検索結果一覧画面
	 */
	@PostMapping("/search")
	public String searchItem(ItemSearchForm itemSearchForm, Model model) {
		//検索フォームの親要素を入れる処理
		List<Category> bigCategoryList = categoryService.findByBigCategory();
		model.addAttribute("bigCategoryList", bigCategoryList);	
		//searchLogの初期化と更新を行っている 初期化は2回目以降検索のログが更新できるようにする
		
		List<Item> itemList = itemService.search(OUT_PUT_NUM, itemSearchForm);
		model.addAttribute("itemList", itemList);
		return "search";
	}
	
	/**
	 * ページング機能を操作する.
	 * 
	 * @param num1
	 * @param model
	 * @param itemSearchForm
	 * @return
	 */
	@GetMapping("/next")
	public String turnPage(Integer num1, Model model, ItemSearchForm itemSearchForm) {
		currentPage += num1;
		//1ページよりも前に戻ろうとすると１ページ目に遷移するようにしているif文
		if(currentPage <= 0) {
			currentPage = 0;
			return "list";
		}
		int num2 = OUT_PUT_NUM * currentPage;
		List<Item> itemList = itemService.LimitAndOffset(OUT_PUT_NUM, num2);
		//最後のページ以降に行こうとすると直前のページに戻るようにするif文
		if(itemList == null) {
			currentPage -= 1;
			num2 = OUT_PUT_NUM * currentPage;
			itemList = itemService.LimitAndOffset(OUT_PUT_NUM, num2);
		}
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("itemList", itemList);
		return "list";
	}
	
	/**
	 * SELECTボックス内の検索を行う.
	 * 
	 * @param num1
	 * @param model
	 * @param itemSearchForm
	 * @return
	 */
	@PostMapping("/selectPage")
	public String selectPage(Integer num1, Model model, ItemSearchForm itemSearchForm) {
		currentPage = 0;
		currentPage = num1 - 1;
		int num2 = OUT_PUT_NUM * currentPage;
		List<Item> itemList = itemService.LimitAndOffset(OUT_PUT_NUM, num2);
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("itemList", itemList);
		return "list";
	}
	
		//中小カテゴリーのメソッドを作成
	/**
	 * 中カテゴリー情報を表示します.
	 * 
	 * @return childListに　childcategoryListのデータを詰めて返す
	 */
	@ResponseBody
	@PostMapping("/childCategory")
	public List<Category> childCategory(Integer bigCategory) {
			System.out.println("/childCategoryの中身"+ bigCategory);
		List<Category> childList = categoryService.findByChildCategory(bigCategory);
		return childList;
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
		List<Category> grandList = categoryService.findByGrandCategory(childCategory);
			System.out.println("grandList =" + grandList);
		return grandList;
	}
}