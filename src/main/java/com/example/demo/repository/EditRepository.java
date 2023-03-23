package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.ItemCategory;

/**
 * 商品編集を操作するリポシトリ.
 * 
 * @author kenji.suzuki
 *
 */
@Repository
public class EditRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<ItemCategory> ITEMCATEGORY_ROW_MAPPER = new BeanPropertyRowMapper<>(ItemCategory.class);

	/**
	 * Items情報を1件検索します.
	 * 
	 * @param id
	 * @return　item 
	 */
	public ItemCategory load(Integer id) {
		String sql = "SELECT id, name, condition, category, brand, price, shipping, description FROM items WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		ItemCategory itemCategory = template.queryForObject(sql, param, ITEMCATEGORY_ROW_MAPPER);
		System.out.println("editRepository load " + itemCategory);
		return itemCategory;
	}
	
	/**
	 * Items情報を更新します.
	 * @param item
	 */
	public void update(ItemCategory item) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		System.out.println("ItemのCategory中身" + item);
		String sql = "UPDATE items SET id=:id, name=:name, condition=:condition, category=:category, brand=:brand, price=:price, shipping=:shipping, description=:description WHERE id=:id;";
		template.update(sql, param);
	}

}
