package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Category;

@Repository
public class CategoryRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Category> CATEGORY_ROW_MAPPER = new BeanPropertyRowMapper<>(Category.class);

//	private static final RowMapper<Category> CATEGORY_ROW_MAPPER =(rs, i) -> {
//		Category category = new Category();
//		category.setId(rs.Int("id"));
//		category.setParent(rs.Int("parent"));
//		category.setName(rs.String("name"));
//		category.setName_All(rs.String("name_all"));
//		return category;
//	};

	/**
	 * 全件検索を行います.
	 * 
	 * @return categoryデータ情報
	 */
	public List<Category> findAll() {
		String sql = "SELECT id,parent,name,name_all FROM category WHERE id= :id, parent= :parent, name= :name, name_all= :name_all;";
		List<Category> categoryList = template.query(sql, CATEGORY_ROW_MAPPER);
		return categoryList;
	}
}
