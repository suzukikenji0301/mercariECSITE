package com.example.demo.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * ユーザのログイン情報を格納するドメイン.
　* 
 * @author　kenji.suzuki
 *
　*/
public class LoginUser extends org.springframework.security.core.userdetails.User { 
	
	private static final long serialVersionUID = 1L;
	private final User user;
	
	public LoginUser(User user, Collection<GrantedAuthority> authorityList) {
		super(user.getUsername(), user.getPassword(), authorityList);
		this.user = user;
	}

	/**
	 * ユーザ情報を返します.
	 * 
	 * @return ユーザ情報
	 */
	public User getUser() {
		System.out.println("ログインユーザー情報" + user);
		return user;
	}
}
