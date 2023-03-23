package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * ユーザー情報を操作するリポシトリ.
 * 
 * @author kenji.suzuki
 *
 */
@Repository
public class UserRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * ユーザーオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);
	
	/**
	 * ユーザーを1件検索します.
	 * 
	 * @param username ユーザー名
	 * @return ユーザー
	 */
	public User findByName(String username) {
		String sql = "SERECT id, user_name AS username, password, authority FROM users WHERE user_name = :username";
		SqlParameterSource param = new MapSqlParameterSource().addValue("username", username);
		User user = template.queryForObject(sql, param, USER_ROW_MAPPER);
		return user;
	}
}
