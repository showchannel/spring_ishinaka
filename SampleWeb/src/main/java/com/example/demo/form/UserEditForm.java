package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * ユーザー登録画面Formクラス
 * 
 * @author ys-fj
 *
 */
@Data
public class UserEditForm {

	/** ログインID */
	@Length(min = 8, max = 20)
	private String loginId;

	/** ユーザー一覧情報から選択されたログインID */
	private String selectedLoginId;
}