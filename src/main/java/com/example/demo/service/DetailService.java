package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

@Service
@Transactional
public class DetailService {

	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * Item情報を1件検索します.
	 * 
	 * @return Item詳細
	 */
	public Item showItemDetail(int id) {
		Item itemDetail = itemRepository.findById(id);
		return itemDetail;
		
	}
}
