package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.constant.UrlConst;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	private final String USERNAME_PARAMETER = "loginId";

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		/*
		 * http.formLogin(login -> login .loginPage("/login")
		 * .usernameParameter(USERNAME_PARAMETER)); return http.build();
		 */
		
		  http.authorizeHttpRequests(authorize ->
		  authorize.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll()
		  .anyRequest().authenticated()).formLogin(login ->
		  login.loginPage(UrlConst.LOGIN).usernameParameter(USERNAME_PARAMETER)
		  .defaultSuccessUrl(UrlConst.MENU)); return http.build();
		 

		/*
		 * .SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
		 * { http.formLogin(login -> login.loginPage("/login")
		 * .usernameParameter("loginId") .defaultSuccessUrl("/menu")); return
		 * http.build();
		 */

		/*
		 * http.authorizeHttpRequests(authorize ->
		 * authorize.requestMatchers("").permitAll .anyRequest().authenticated())
		 * .formLogin(login -> login.loginPage("/login") .usernameParameter("loginId")
		 * .defaultSuccessUrl("/menu")) return http.build();
		 */

	}
}
