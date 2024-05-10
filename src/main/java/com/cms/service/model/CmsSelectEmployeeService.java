package com.cms.service.model;

import java.util.List;

import com.cms.entity.employee.CmsEmployeeBean;
/**
 * 社員選択画面 Service
 */
public interface CmsSelectEmployeeService {

	/**
	 * 社員選択画面_検索処理
	 * 
	 * @param bean 社員Bean
	 * @return List<CmsEmployeeBean> 検索結果
	 */	
	public List<CmsEmployeeBean> select(CmsEmployeeBean bean);
	
}
