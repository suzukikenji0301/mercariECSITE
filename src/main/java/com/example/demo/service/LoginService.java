package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.form.UserForm;
import com.example.demo.repository.LoginRepository;

/**
 * ログイン情報を操作するサービス.
 * 
 * @author kenji.suzuki
 *
 */
@Service
@Transactional
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	
	/**
	 * 登録されたユーザー情報を送ります.
	 * 
	 * @param userForm
	 * @return loginRepository
	 */
	public User loginUser(UserForm userForm) {
		return loginRepository.findByMailAndPassword(userForm.getUsername(), userForm.getPassword());
	}
}
