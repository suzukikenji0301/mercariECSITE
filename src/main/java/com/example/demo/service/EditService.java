package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Item;
import com.example.demo.domain.ItemCategory;
import com.example.demo.repository.EditRepository;

@Service
public class EditService {

//	@Autowired
//	private CategoryRepository categoryRepository;
//	@Autowired
//	private ItemRepository itemRepository;
	@Autowired
	private EditRepository editRepository;
	
	
	/**
	 * 1件Itemを取り出します.
	 * 
	 * @param id
	 * @return item
	 */
	public ItemCategory showEdit(Integer id) {
		System.out.println("Service処理開始" + id);
		ItemCategory item = editRepository.load(id);
		System.out.println(item);
		return item;
	}
	
	
	/**
	 * Item情報を更新.
	 * 
	 * @param item
	 * @return　商品情報
	 */
	public ItemCategory update(ItemCategory item) {
		editRepository.update(item);
		return item;
	}
}
