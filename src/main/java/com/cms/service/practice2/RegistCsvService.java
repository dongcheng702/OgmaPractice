package com.cms.service.practice2;

public interface RegistCsvService {

	/**
	 * CSV取込処理
	 * 
	 * @param filePath ファイルパス
	 * @param itemCount 項目数
	 * @return 戻り値（エラー情報）
	 * @throws Exception 
	 */	
	public int registCsvToMySql(String filePath, int itemCount) throws Exception;

}
