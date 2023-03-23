package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;

/**
 * ユーザー登録する際受け取るフォーム.
 * 
 * @author kenji.suzuki
 *
 */
public class UserForm {

	/** ユーザーネーム */
	@NotBlank(message = "名前の入力は必須です")
	private String username;
	/** パスワード */
	@NotBlank(message = "パスワードの入力は必須です")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserForm [username=" + username + ", password=" + password + "]";
	}
}
