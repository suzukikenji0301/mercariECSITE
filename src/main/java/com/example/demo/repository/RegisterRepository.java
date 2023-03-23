package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

/**
 * 商品情報を操作するリポシトリ.
 * 
 * @author kenji.suzuki
 *
 */
@Repository
public class RegisterRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * ユーザー登録をします.
	 * 
	 * @param user
	 */
	public void insertUser(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "INSERT INTO users(name, password, authority) VALUES(:username, :password, :authority);";
		template.update(sql, param);
	}
	
}
