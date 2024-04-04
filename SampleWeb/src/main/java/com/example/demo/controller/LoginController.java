package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginServise;
import com.example.demo.util.AppUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/*ログイン画面コントローラー*/
@Controller
@RequiredArgsConstructor
public class LoginController {

	/* ログイン画面 Service */
	private final LoginServise service;

	/** PasswordEncoder */
	private final PasswordEncoder passwordEncoder;

	/* メッセージソース */
	private final MessageSource messageSource;

	/** セッション情報 */
	private final HttpSession session;

	@GetMapping("/login")
	public String view(Model model, LoginForm form) {
		return "login";
	}

	/*
	 * @PostMapping("/login") public void login(LoginForm form) {
	 * System.out.println(form.toString()); }
	 */

	@PostMapping("/login")
	public String login(Model model, LoginForm form) {
		var userInfo = service.searchUserById(form.getLoginId());
		var encordedPassword = passwordEncoder.encode(form.getPassword());
		var isCorrectUserAuth = userInfo.isPresent()
				&& passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());

		/*
		 * var encordedPassword = passwordEncoder.encode(form.getPassword()); var
		 * isCorrectUserAuth = userInfo.isPresent() &&
		 * passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());
		 */
		if (isCorrectUserAuth) {
			return "redirect:/menu";
		} else {
			var errorMsg = AppUtil.getMessage(messageSource,MessageConst.LOGIN_WRONG_INPUT);
			model.addAttribute("errorMsg", errorMsg);
			return "login";
		}

	}

	/*
	 * @GetMapping(value = UrlConst.LOGIN, params = "error") public String
	 * viewWithError(Model model, LoginForm form) { var errorInfo = (Exception)
	 * session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	 * model.addAttribute("errorMsg", errorInfo.getMessage()); return "login"; }
	 */

	/*
	 * @PostMapping(UrlConst.LOGIN) public String login(Model model, LoginForm form)
	 * { var userInfo = service.searchUserById(form.getLoginId());
	 * 
	 * var isCorrectUserAuth = userInfo.isPresent() &&
	 * form.getPassword().equals(userInfo.get().getPassword());
	 * 
	 * 
	 * var encordedPassword = passwordEncoder.encode(form.getPassword()); var
	 * isCorrectUserAuth = userInfo.isPresent() &&
	 * passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword()); if
	 * (isCorrectUserAuth) { return "redirect:/menu"; } else { var errorMsg =
	 * AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_INPUT);
	 * model.addAttribute("errorMsg", errorMsg); return "login"; }
	 * 
	 * }
	 */
}