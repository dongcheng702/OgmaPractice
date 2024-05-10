package com.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.cms.common.Gender;

public class CmsUtils {

	/**
	 * ラジオボタンの作成
	 * 
	 * @return ラジオボタンの初期値
	 */
	public static List<Gender> createGenders() {
		// 社員区分（ラジオボタン）
		List<Gender> genders = new ArrayList<Gender>() {
			{
				add(Gender.of("radioKbn", "社員", "社員"));
				add(Gender.of("radioKbn", "BP", "BP"));
			}
		};
		return genders;
	}
	
	/**
	 * システム日付を取得する
	 * 
	 * @return ラジオボタンの初期値
	 */
	public static Timestamp getSystemDate() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 文字列を日付にフォーマットする
	 * 
	 * @return フォーマット後の日付
	 */
	public static Date FormatToDate(String dt) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);

		Date retDate = null;
		try {
			retDate = formatter.parse(dt);
		} catch (ParseException e) {
			return null;
		}
	    return retDate;
	}
	
	/**
	 * 文字列を日付にフォーマットする
	 * 
	 * @param dt 日付（文字列）
	 * @param format フォーマット
	 * @return フォーマット後の日付
	 */
	public static Date FormatToDate(String dt, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.JAPAN);

		Date retDate = null;
		try {
			retDate = formatter.parse(dt);
		} catch (ParseException e) {
			return null;
		}
	    return retDate;
	}
	/**
	 * 文字列を日付にフォーマットする
	 * 
	 * @return フォーマット後の日付
	 */
	public static Timestamp FormatToTimestame(String dt) {

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	    Date parsedDate;
		try {
			parsedDate = dateFormat.parse(dt);
		} catch (ParseException e) {
			return null;
		}
	    return new java.sql.Timestamp(parsedDate.getTime());
	}
	
	/**
	 * 计算时差 根据 long 返回时间点
	 * 
	 * @param millisecond
	 * @return string 0天0时11分55秒
	 */
	public static String parseMillisecone(long millisecond) {
	    long yushu_day = millisecond % (1000 * 60 * 60 * 24);
	    return String.valueOf(yushu_day / (1000 * 60 * 60));
	}
	

	/**
	 * 日付を文字列に変更する
	 * 
	 * @param ft フォーマット
	 * @return フォーマット後の日付
	 */
	public static String formatDateToString(Date dt ,String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);  
		return dateFormat.format(dt);  
	}
	

	/**
	 * 日付を文字列に変更する
	 * 
	 * @return フォーマット後の日付
	 */
	public static java.sql.Date getToday() {
		LocalDate dt = LocalDate.now();
		return java.sql.Date.valueOf(dt);  
	}
	

	/**
	 * ラジオボタンの作成
	 * 
	 * @param val フォーマット対象
	 * @return フォーマット後の文字列
	 */
	public static String formatEmptyToNull(String val) {
		
		if (StringUtils.isEmpty(val)) {
			return null;
		}
		return val;
	}
	
}
