package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.form.ItemSearchForm;
import com.example.demo.repository.ItemRepository;

/**
 * アイテム情報を操作するサービス.
 * 
 * @author kenji.suzuki
 *
 */
@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
//	/**
//	 * Item情報を検索します.
//	 * @param pageCount 
//	 * 
//	 * @return アイテム情報
//	 */
//	public List<Item> showItem(Integer pageCount) {
//		List<Item> itemList = itemRepository.find30List(pageCount);
//		return itemList;
//	}
	
	/**
	 * 指定したページのリストを返す.
	 * 
	 * @param int num1 ページ数
	 * @return　itemRepositoryのLimitFindAll(num1)メソッド
	 */
	public List<Item> showItemList(int num1) {
			System.out.println(" Service showItemList来ました" + num1);
		return itemRepository.LimitFindAll(num1);
	}
	
	/**
	 * 指定したページのリストを返す.
	 * 
	 * @param int num1, int num2 ページ数
	 * @return　itemRepositoryのtrunPage(num1, num2)メソッド
	 */
	public List<Item> LimitAndOffset(int num1, int num2) {
		return itemRepository.trunPage(num1, num2);
	}
	
	/**
	 * 指定されたidのItemを返す
	 * 
	 * @param itemId
	 * @return
	 */
	public Item searchDetail(Integer itemId) {
		return itemRepository.searchDetail(itemId);
	}
	
	/**
	 * Itemを検索します.
	 * 
	 * @param OUT_PUT_NUM
	 * @param itemSearchForm
	 * @return itemRepository.findByName(sql)
	 */
	public List<Item> search(int OUT_PUT_NUM, ItemSearchForm itemSearchForm) {
		String sql = "SELECT i.id, i.name, i.condition, c.name_all AS category, i.brand, i.price, i.shipping, i.description FROM items i INNER JOIN category c ON i.category = c.id ";
		sql += "WHERE i.name ILIKE " + "'" + "%" + itemSearchForm.getName() + "%" + "'" + " AND i.brand ILIKE " + "'" + "%" + itemSearchForm.getBrand() + "%" + "' ";
		
		if(itemSearchForm.getGrandCategory() != null) {
			sql += "AND i.category=" + itemSearchForm.getChildCategory() + " ";
		}
		sql += "ORDER BY id LIMIT " + OUT_PUT_NUM + ";";
		return itemRepository.findByName(sql);
	}
	
	
	/**
	 * 検索結果のページングを行います.
	 * 
	 * @param OUT_PUT_NUM
	 * @param num2
	 * @param searchLog
	 * @return itemRepository.findByName(sql)
	 */
	public List<Item> turnPage(int OUT_PUT_NUM, int num2, ItemSearchForm searchLog) {
		String sql = "SELECT i.id, i.name, i.condition, c.name_all AS category, i.brand, i.price, i.shipping, i.description FROM items i INNER JOIN category c ON i.category = c.id ";
		sql += "WHERE i.name ILIKE " + "'" + "%" + searchLog.getName() + "%" + "'" + " AND i.brand ILIKE " + "'" + "%" + searchLog.getBrand() + "%" + "' ";
		
		if(searchLog.getGrandCategory() != null) {
			sql += "AND i.category=" + searchLog.getGrandCategory() + " ";
		}
		sql += "ORDER BY id LIMIT " + OUT_PUT_NUM + "OFFSET " + num2 + ";";
		return itemRepository.findByName(sql);
	}

}
