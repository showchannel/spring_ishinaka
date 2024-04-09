package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.constant.UrlConst;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final String USERNAME_PARAMETER = "loginId";

	/** PasswordEncoder */
	private final PasswordEncoder passwordEncoder;

	/** ユーザー情報取得Service */
	private final UserDetailsService userDetailsService;

	/** メッセージ取得 */
	private final MessageSource messageSource;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		/* これでログインコントローラーが呼ばれる */
		/* どういうnameで持ってくるかの設定ができる */
		/* loginIdというnameでユーザーネームを拾ってくる */
		/* ログインに成功したときに"/menu"にリダイレクトする */
		/* permitAll:認証済み関係なく常にアクセス可 */
		/* authenticated：ユーザー認証済みの場合のみアクセス可 */
		/* requestMatchers("")で""urlはユーザー認証アクセス可 */
		/* anyRequest()でそれ以外のurlはすべて認証必要 */
		http.authorizeHttpRequests(authorize -> authorize.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll()
				.anyRequest().authenticated())
				.formLogin(login -> login.loginPage(UrlConst.LOGIN).usernameParameter(USERNAME_PARAMETER)
						.defaultSuccessUrl(UrlConst.MENU));
		return http.build();
	}

	@Bean
	AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		provider.setMessageSource(messageSource);

		return provider;
	}
	
	/*
	 * 
	 * @Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws
	 * Exception {
	 * 
	 * http.formLogin(login -> login .loginPage("/login")
	 * .usernameParameter(USERNAME_PARAMETER)); return http.build();
	 * 
	 * 
	 * http.authorizeHttpRequests(authorize ->
	 * authorize.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll()
	 * .anyRequest().authenticated()).formLogin(login ->
	 * login.loginPage(UrlConst.LOGIN).usernameParameter(USERNAME_PARAMETER)
	 * .defaultSuccessUrl(UrlConst.MENU)); return http.build();
	 * 
	 * 
	 * 
	 * .SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	 * { http.formLogin(login -> login.loginPage("/login")
	 * .usernameParameter("loginId") .defaultSuccessUrl("/menu")); return
	 * http.build();
	 * 
	 * 
	 * 
	 * http.authorizeHttpRequests(authorize ->
	 * authorize.requestMatchers("").permitAll .anyRequest().authenticated())
	 * .formLogin(login -> login.loginPage("/login") .usernameParameter("loginId")
	 * .defaultSuccessUrl("/menu")) return http.build();
	 * 
	 * 
	 * }
	 */
}
