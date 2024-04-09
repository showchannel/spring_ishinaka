package com.example.demo.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignupServise {

	private final UserInfoRepository repository;

	/* Dozer mapper */
	private final Mapper mapper;

	/** PasswordEncoder */
	private final PasswordEncoder passwordEncoder;

	/**
	 * 
	 * @param form 入力情報
	 * @return 登録情報(ユーザー情報Entity)すでに同じユーザーIDで登録がある場合はEmpty
	 */

	/* ユーザー情報登録新規登録 */

	public Optional<UserInfo> resistUserInfo(SignupForm form) {
		/*
		 * var mapper = new DozerBeanMapper(); var userInfo = new UserInfo();
		 * userInfo.setLoginId(form.getLoginId());
		 * userInfo.setPassword(form.getPassword()); return repository.save(userInfo);
		 * 
		 * var userInfoExsistedOpt = repository.findById(form.getLoginId()); if
		 * (userInfoExsistedOpt.isPresent()) { return Optional.empty(); }
		 * 
		 * var userInfo = mapper.map(form, UserInfo.class); return
		 * Optional.of(repository.save(userInfo));
		 */

		var userInfoExsistedOpt = repository.findById(form.getLoginId());
		if (userInfoExsistedOpt.isPresent()) {
			return Optional.empty();
		}

		var userInfo = mapper.map(form, UserInfo.class);
		var encordedPassword = passwordEncoder.encode(form.getPassword());
		userInfo.setPassword(encordedPassword);
		return Optional.of(repository.save(userInfo));
	}

}
