package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/*ユーザー登録*/
@Data
public class SignupForm {
	
	/**ログインID*/
	@Length(min=1,max=20)
	private String loginId;
	
	/**パスワード*/
	@Length(min=1,max=20)
	private String password;

}
