package com.example.demo.dto;

import lombok.Data;

/**
 * ユーザー更新情報DTOクラス
 * 
 * @author ys-fj
 *
 */
@Data
public class UserUpdateInfo {

	/** ログインID */
	private String loginId;

	/** ログイン失敗状況をリセットするか(リセットする場合、true) */
	private boolean resetsLoginFailure;
	
	/** 更新ユーザーID */
	private String updateUserId;
}