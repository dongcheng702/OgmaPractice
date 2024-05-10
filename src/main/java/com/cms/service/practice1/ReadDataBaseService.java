package com.cms.service.practice1;

import java.util.List;

import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.form.practice1.ReadDataBaseForm;

public interface ReadDataBaseService {

	/**
	 * 社員情報を検索する
	 * 
	 * @param form 社員検索画面フォーム
	 * @return 社員Beanリスト
	 * @throws Exception 
	 */	
	public List<CmsEmployeeBean> readDataFromMysql(ReadDataBaseForm form) throws Exception;

}
