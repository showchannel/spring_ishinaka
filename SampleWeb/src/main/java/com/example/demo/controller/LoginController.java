package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginServise;
import com.example.demo.util.AppUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/*ログイン画面コントローラー*/
@Controller
@RequiredArgsConstructor
public class LoginController {

	/*ログイン画面 Service*/
	private final LoginServise service;
	
	/*メッセージソース*/
	private final MessageSource messageSource;
	
	/** セッション情報 */
	private final HttpSession session;
	

	@GetMapping(UrlConst.LOGIN)
	public String view(Model model, LoginForm form) {
		return "login";
	}
	
	@GetMapping(value = UrlConst.LOGIN,params = "error")
	public String viewWithError(Model model, LoginForm form) {
		var errorInfo = (Exception) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		
		return "login";
	}
	
	

	@PostMapping(UrlConst.LOGIN)
	public String login(Model model, LoginForm form) {
		var userInfo = service.searchUserById(form.getLoginId());
		var isCorrectUserAuth = userInfo.isPresent() 
				&& form.getPassword().equals(userInfo.get().getPassword());
		if (isCorrectUserAuth) {
			return "redirect:/menu";
		} else {
			var errorMsg = AppUtil.getMessage(messageSource,MessageConst.LOGIN_WRONG_INPUT);
			model.addAttribute("errorMsg", errorMsg);
			return "login";
		}

	}
}