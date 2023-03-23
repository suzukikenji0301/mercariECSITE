package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * ログイン機能を操作するコントローラー.
 * 
 * @author kenji.suzuki
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private HttpSession session;
	
	/**
	 * ログインします.
	 * 
	 * @return　ログイン画面
	 */
	@RequestMapping("/loginUser")
	public String toLogin(String username,String password,Model model,HttpServletRequest httpServletRequest) {
		User user = new User();
		model.addAttribute("username",user);
		model.addAttribute("password",password);
		return "login";
	}
	
	/**
	 * ログアウトします.
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/logOut")
	public String logout() {
		session.invalidate();
		return "login";
	}
}
