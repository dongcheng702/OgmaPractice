package com.cms.service.csvfetch;

public interface CsvFetchService {
	
	//传文件数据
	public int registCsvToMySql(byte[] bytes,int count) throws Exception;
}
