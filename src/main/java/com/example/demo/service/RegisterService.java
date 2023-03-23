package com.example.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.form.UserForm;
import com.example.demo.repository.RegisterRepository;

/**
 * ユーザー登録を操作するサービス.
 * 
 * @author kenji.suzuki
 *
 */
@Service
@Transactional
public class RegisterService {

	@Autowired
	private RegisterRepository registerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * ユーザー登録情報を挿入します.
	 * 
	 * @param form
	 */
	public void insertUser(UserForm form) {
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUsername(form.getUsername());
		user.setPassword(form.getPassword());
		user.setAuthority(1);
		registerRepository.insertUser(user);
	}


}
