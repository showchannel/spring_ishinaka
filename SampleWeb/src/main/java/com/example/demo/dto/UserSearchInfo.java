package com.example.demo.dto;

import lombok.Data;

/**
 * ユーザー一覧画面DTOクラス
 * 
 * @author ys-fj
 *
 */
@Data
public class UserSearchInfo {

	/** ログインID */
	private String loginId;
	
	private String password;
}