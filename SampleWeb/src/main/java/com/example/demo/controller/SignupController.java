package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.SignupMessage;
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

	/*ログイン画面 Service*/
	private final SignupServise EXISTED_LOGIN_ID;

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
	 * @param bdResult入力チェック
	 * @return 表示画面
	 */

	@PostMapping("/signup")
	public void signup(Model model, @Validated SignupForm form, BindingResult bdResult) {
		if (bdResult.hasErrors()) {
			return;
		}
		var userInfoOpt = service.resistUserInfo(form);
		var signupMessage = judgeMessageKey(userInfoOpt);
		var messageId = AppUtil.getMessage(messageSource, signupMessage.getMessageId());
		model.addAttribute("message", messageId);
		model.addAttribute("isError", signupMessage.isError());
	}

	private SignupMessage judgeMessageKey(Optional<UserInfo> userInfoOpt) {
		if (userInfoOpt.isEmpty()) {
			return SignupMessage.EXISTED_LOGIN_ID;
		} else {
			return SignupMessage.SUCCEED;
		}
	}
}