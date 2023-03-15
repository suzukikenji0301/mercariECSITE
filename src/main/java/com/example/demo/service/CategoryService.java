package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Category;
import com.example.demo.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	/**
	 * BigCategory情報を検索します.
	 * 
	 * @return 大項目アイテム情報
	 */
	public List<Category> findByBigCategory() {
		List<Category> bigCategoryList = categoryRepository.findByBigCategory();
		return bigCategoryList;
	}
	
	/**
	 * ChildCategory情報を検索します.
	 * 
	 * @return 中項目アイテム情報
	 */
	public List<Category> findByChildCategory(Integer id) {
		List<Category> childCategoryList = categoryRepository.findByChildCategory(id);
		return childCategoryList;
	}
	
	/**
	 * GrandCategory情報を検索します.
	 * 
	 * @return 小項目アイテム情報
	 */
	public List<Category> findByGrandCategory(Integer id) {
		List<Category> grandCategoryList = categoryRepository.findByGrandCategory(id);
		return grandCategoryList;
	}
	
	
}
