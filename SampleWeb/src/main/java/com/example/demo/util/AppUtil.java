package com.example.demo.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * アプリケーション共通クラス
 * @author ys~fj
 */

public class AppUtil {

	public static String getMessage(MessageSource massageSource,String Key,Object...params) {
		return massageSource.getMessage(Key,params,Locale.JAPAN);
	}
}
