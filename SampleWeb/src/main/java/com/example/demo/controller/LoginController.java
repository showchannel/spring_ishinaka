package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.ErrorMessageConst;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginServise;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

/*ログイン画面コントローラー*/
@Controller
@RequiredArgsConstructor
public class LoginController {

	/*ログイン画面 Service*/
	private final LoginServise service;
	
	/*メッセージソース*/
	private final MessageSource messageSource;

	@GetMapping("/login")
	public String view(Model model, LoginForm form) {
		return "login";
	}

	@PostMapping("/login")
	public String login(Model model, LoginForm form) {
		var userInfo = service.searchUserById(form.getLoginId());
		var isCorrectUserAuth = userInfo.isPresent() 
				&& form.getPassword().equals(userInfo.get().getPassword());
		if (isCorrectUserAuth) {
			return "redirect:/menu";
		} else {
			var errorMsg = AppUtil.getMessage(messageSource,ErrorMessageConst.LOGIN_WRONG_INPUT);
			model.addAttribute("errorMsg", errorMsg);
			return "login";
		}

	}
}