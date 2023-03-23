package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.AddItem;
import com.example.demo.domain.Item;
import com.example.demo.repository.AddRepository;
import com.example.demo.repository.ItemRepository;

/**
 * 商品情報を操作するサービス.
 * 
 * @author kenji.suzuki
 *
 */
@Service
@Transactional
public class AddService {

	@Autowired
	private AddRepository addRepository;
	@Autowired 
	private ItemRepository itemRepository;

	/**
	 * 商品情報を送信します.
	 * 
	 * @param add 商品情報
	 */
	public void insertAdd(AddItem add) {
		addRepository.insertAdd(add);
	}
	

	/**
	 * 商品を追加する.
	 * 
	 * @param item 商品
	 * @return ID
	 */
	public Integer addItem(Item item) {
		Integer id = itemRepository.insert(item);
		return id;
	}
}

