package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	/**
	 * Item情報を検索します.
	 * 
	 * @return アイテム情報
	 */
	public List<Item> showItem() {
		List<Item> itemList = itemRepository.findAll();
		return itemList;
	}
	
	/**
	 * 商品の曖昧検索を行います。
	 * 
	 * @param name 商品名
	 * @return 商品情報リスト
	 */
	public List<Item> serchByName(String name){
		List<Item> itemList = itemRepository.findByItemName(name);
		return itemList;
	}
}
