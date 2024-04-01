package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.service.SignupServise;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

/*サインアップ登録画面コントローラー*/
@Controller
@RequiredArgsConstructor
public class SignupController {

	/*ログイン画面 Service*/
	private final SignupServise service;

	/**メッセージソース*/
	private final MessageSource messageSource;

	@GetMapping("/signup")
	public String view(Model model, SignupForm form) {
		return "signup";
	}

	/**
	 * ユーザー登録
	 * @param modelモデル
	 * @param form入力情報
	 * @return 表示画面
	 */

	@PostMapping("/signup")
	public void signup(Model model, SignupForm form) {
		var userInfoOpt = service.resistUserInfo(form);
		var message = AppUtil.getMessage(messageSource, judgeMessageKey(userInfoOpt));
		model.addAttribute("message", message);
	}

	private String judgeMessageKey(Optional<UserInfo> userInfoOpt) {
		if (userInfoOpt.isEmpty()) {
			return MessageConst.SIGNUP_EXISTED_LOGINID;
		} else {
			return MessageConst.SIGNUP_RESIST_SUCCEED;
		}
	}
}