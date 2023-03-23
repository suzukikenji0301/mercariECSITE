package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * ログイン情報を操作するリポシトリ.
 * 
 * @author kenji.suzuki
 *
 */
@Repository
public class LoginRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);
	
	/**
	 * ユーザーネーム・パスワードを検索します.
	 * 
	 * @param userName
	 * @param password
	 * @return userList
	 */
	public User findByMailAndPassword(String userName, String password) {
		String sql = "SELECT user_name, password FROM users WHERE user_name=:userName AND password=:password;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userName", userName).addValue("password", password);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}
}
