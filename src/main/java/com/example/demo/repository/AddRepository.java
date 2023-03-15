package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.AddItem;


@Repository
public class AddRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 商品情報を挿入します.
	 * 
	 * @param contact 商品情報
	 */
	public void insertAdd(AddItem add) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(add);
		String sql = "insert into items(name,price,category,brand,condition,description) values(:name,:price,:category,:brand,:condition,:description);";
		System.out.println("商品を追加しました内容は" + add + "となります。");
		template.update(sql, param);
	}
}
