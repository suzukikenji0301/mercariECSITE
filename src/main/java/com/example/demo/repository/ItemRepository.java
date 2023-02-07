package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;

@Repository
public class ItemRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Item> ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);
	
//	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
//		Item item = new Item();
//		item.setId(rs.getInt("id"));
//		item.setName(rs.getInt("name"));
//		item.setConditionId(rs.getInt("condition"));
//		item.setCategory(rs.getString("category"));
//		item.setBrand(rs.getString("brand"));
//		item.setPrice(rs.getInteger("price"));
//		item.setShipping(rs.getInteger("shipping"));
//		item.setDescription(rs.String("description"));
//		return item;
//	};
	
	/**
	 * Itemデータをid昇順で30件検索します.
	 * @return itemList
	 */
	public List<Item> findAll(){
		String sql ="SELECT id,name,condition,category,brand,price,shipping,description FROM items ORDER BY id ASC LIMIT 30;";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;

	}

	/**
	 * Itemデータをidで紐づけて1件検索します.
	 * @return itemList
	 */
	public Item findById(int id){
		String sql ="SELECT id,name,condition,category,brand,price,shipping,description FROM items WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
		Item itemId = template.queryForObject(sql,param, ITEM_ROW_MAPPER);
		return itemId;

	}
	/**
	 * nameで商品のあいまい検索を行います.
	 * 
	 * @param name　商品名
	 * @return　商品一覧
	 */
	public List<Item> findByItemName(String name){
		String sql = "SELECT id,name,condition,category,brand,price,shipping,description FROM items WHERE name ILIKE :name ORDER BY asc;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
		List<Item> itemList= template.query(sql,param, ITEM_ROW_MAPPER);
		return itemList;
	}
	
//	/**
//	 * 商品情報を更新します.
//	 * 
//	 * @param item　商品情報
//	 */
//	public void update(Item item) {
//		String sql ="UPDATE items SET name =:name, condition =:condition, category =:category, brand =:brand, price =:price, shipping =:shipping, description =:description, WHERE id=:id;";
//		SqlParameteSouce param = new BeanPropertySqlParameterSource(item);
//		template.update(sql, param);
//	}
}
