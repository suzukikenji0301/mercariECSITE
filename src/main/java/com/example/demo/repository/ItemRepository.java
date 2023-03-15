package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;
import com.example.demo.domain.RecordNum;

@Repository
public class ItemRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Item> ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);
	private static final RowMapper<RecordNum> RECORDNUM_ROW_MAPPER = new BeanPropertyRowMapper<>(RecordNum.class);

//	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
//		Item item = new Item();
//		item.setId(rs.getInt("id"));
//		item.setName(rs.getString("name"));
//		item.setCondition(rs.getInt("condition"));
//		item.setCategory(rs.getString("category"));
//		item.setBrand(rs.getString("brand"));
//		item.setPrice(rs.getInt("price"));
//		item.setShipping(rs.getInt("shipping"));
//		item.setDescription(rs.getString("description"));
//		return item;
//	};

	/**
	 * Itemデータをid昇順で検索します.
	 * 
	 * @return itemList
	 */
	public List<Item> findAll() {
		String sql = "SELECT id,name,condition,category,brand,price,shipping,description FROM items ORDER BY id ASC;";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}

//	/**
//	 * Itemデータをid昇順で100件検索します.
//	 * 
//	 * @return itemList LIMIT 100
//	 */
//	public List<Item> find30List(Integer pageCount) {
//		String sql ="SELECT id,name,condition,category,brand,price,shipping,description FROM items ORDER BY id ASC LIMIT 100;";
//		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
//			System.out.println("Repository　find30List来ました");
//		return itemList;
//	}

	/**
	 * itemsテーブルのIDをカウントし検索します.
	 * @return recordNumlist.get(0).getRecordNum
	 */
	public Integer recordNum() {
		String sql = "SELECT COUNT(id) AS record_num FROM items;";
		List<RecordNum> recordNumlist = template.query(sql, RECORDNUM_ROW_MAPPER);
			System.out.println("Repository　recordNum来ました");
		return recordNumlist.get(0).getRecordNum();
	}
	
	/**
	 * Item Categoryを結合させnum1に代入された数で制限します.
	 * @param num1 
	 * @return
	 */
	public List<Item> LimitFindAll(int num1) {
		String sql = "SELECT i.id, i.name, i.condition, c.name_all AS category, i.brand, i.price, i.shipping, i.description FROM items i INNER JOIN category c ON i.category = c.id ORDER BY id LIMIT :num1;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("num1", num1);
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
			System.out.println("Repository　LimitFindAll来ました" + itemList);
			System.out.println("Repository　LimitFindAll来ました" + num1);
		return itemList;
	}
							
	/**
	 * Item Categoryを結合させnum1としOffsetをnum2とします.
	 * @param num1
	 * @param num2
	 * @return
	 */
	public List<Item> trunPage(int num1, int num2) {
		String sql = "SELECT i.id, i.name, i.condition, c.name_all AS category, i.brand, i.price, i.shipping, i.description FROM items i INNER JOIN category c ON i.category = c.id ORDER BY id LIMIT :num1 OFFSET :num2;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("num1", num1).addValue("num2", num2);
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
			System.out.println("Repository　trunPage来ました" + itemList);
		return itemList;
	}
	
	/**
	 * ItemNameを取得する.
	 * 
	 * @param sql
	 * @return itemList
	 */
	public List<Item> findByName(String sql) {
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
//	/**
//	 * 検索Formに入力された物を検索して該当アイテムを表示させる(1ページ30件まで).
//	 * 
//	 * @param name
//	 * @param brand
//	 * @param categoryId
//	 * @param page
//	 * @return
//	 */
//	public List<ItemCategory> findBySearchForm(String name, String brand, Integer categoryId, Integer page) {
//
//		Integer andNum = 0;// 次ANDが必要なときは1にする
//		StringBuilder sql = new StringBuilder();
//		sql.append("SELECT i.id i_id,i.name i_name,co.condition co_condition,ca.name_all ca_name_all,i.brand i_brand,i.price i_price,i.shipping i_shipping,d.description d_description,i.version i_version FROM items i JOIN category ca ON i.category = ca.id JOIN Condition co ON i.condition = co.id JOIN description d ON i.description = d.id ");
//		if ((!name.equals("") || !brand.equals("")) || categoryId != null) {
//			sql.append(" WHERE ");
//		}
//		if (!name.equals("")) {
//			sql.append(" i.name LIKE '%" + name + "%' ");
//			andNum = 1;
//			if (categoryId != null) {
//				if (andNum == 1) {
//					sql.append(" AND ");
//					andNum = 1;
//				}
//				sql.append(" ca.category = " + categoryId + " ");
//			}
//			if (!brand.equals("")) {
//				if (andNum == 1) {
//					sql.append(" AND ");
//					andNum = 1;
//				}
//				sql.append(" i.brand LIKE '%" + brand + "%' ");
//			}
//			sql.append(" ORDER BY i.name ");
//			if (page == 0) {
//				sql.append(" LIMIT 30");
//			} else {
//				page = page * 30;
//				sql.append(" LIMIT 30 OFFSET " + page);
//			}
//			sql.append(" ;");
//			List<ItemCategory> itemList = template.query(sql.toString(), ITEM_RESULTSET_EXTRACTOR);
//			return itemList;
//		}
//		return null;
//	}

	/**
	 * Itemデータをidで紐づけて1件検索します.
	 * 
	 * @return itemList
	 */
	public Item findById(int id) {
		String sql = "SELECT i.id, i.name, i.condition, c.name_all AS category, i.brand, i.price, i.shipping, i.description FROM items i INNER JOIN category c ON i.category = c.id WHERE i.id=:id;";		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}

	/**
	 * 選択された商品のIdからItemを１件返すメソッド.
	 * 
	 * @param itemId
	 * @return
	 */
	public Item searchDetail(Integer itemId) {
		String sql = "SELECT i.id i_id,i.name i_name,co.condition co_condition,ca.name_all ca_name_all,i.brand i_brand,i.price i_price,i.shipping i_shipping,d.description d_description,i.version i_version FROM items i JOIN category ca ON i.category = ca.id JOIN Condition co ON i.condition = co.id JOIN description d ON i.description = d.id WHERE i.id = :itemId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId);
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList.get(0);
	}

	/**
	 * 商品を追加する.
	 * 
	 * @param item 商品情報
	 */
	public int insert(Item item) {
		String sql = "insert into items(name,condition,category,brand,price,shipping,description) values(:name,:condition,:category,:brand,:price,:shipping,:description);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumNames = { "id" };
		template.update(sql, param, keyHolder, keyColumNames);
		Integer id = keyHolder.getKey().intValue();
		return id;
	}
	
}
	    

