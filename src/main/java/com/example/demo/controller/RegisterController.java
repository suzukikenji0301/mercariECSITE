package com.example.demo.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.User;
import com.example.demo.form.UserForm;
import com.example.demo.service.RegisterService;


/**
 * ユーザー登録機能を操作するコントローラー.
 * 
 * @author kenji.suzuki
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private RegisterService registerService;
	
	/**
	 * 登録画面を表示します.
	 * 
	 * @param form
	 * @return register.HTML
	 */
	@GetMapping("/in")
	public String register(UserForm form,Model model) {
		return "register";
	}
	
	/**
	 * ユーザー登録をします.
	 * 
	 * @param form　ユーザー追加
	 * @return
	 */
	@PostMapping("/insert")
	public String insert( UserForm form,Model model,BindingResult result,RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
	        return register(form,model);
		}
		User user = new User();
		user.setUsername(form.getUsername());
		user.setPassword(form.getPassword());
		BeanUtils.copyProperties(form,user);
		registerService.insertUser(form);
		model.addAttribute("Username",form.getUsername());
		model.addAttribute("Password",form.getPassword());
			System.out.println(form.getUsername() + "登録されたユーザーネーム");
			System.out.println(form.getPassword() + "登録されたパスワード");
		return "registerFinish";
	}
}
