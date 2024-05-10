package com.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ラジオボタン（性別）
 * 
 * @param flg 「すべて」有無のフラグ
 */
public class ScreenUtils {
	public static Map<String, String> initSexyRadios(boolean flg) {
		Map<String, String> radio = new LinkedHashMap<>();
		radio.put("0", "男性");
		radio.put("1", "女性");
		if(flg) {
			radio.put("2", "すべて");
		}
		return radio;
	}
}
