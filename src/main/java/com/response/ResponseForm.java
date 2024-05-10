package com.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseForm<T> {

	//処理ステータス
	private String status;
	
	//エラー内容
	private String errContent;
	
	//検索結果
	private List<T> results;
	
	//検索結果
	private T registData;
	
	//登録／更新件数
	private int upsertCount;
}
