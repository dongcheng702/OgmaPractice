package com.cms.service.salarydetaillist;

import java.util.List;

import com.cms.entity.salarydetaillist.CmsSalaryDetailBean;
import com.cms.form.salarydetaillist.SalaryDetailListScreenForm;

public interface SalaryDetailListScreenService {

	/**
	 * 給料明細情報を検索する
	 * 
	 * @param form 給料一覧画面フォーム
	 * @return 給料明細Beanリスト
	 * @throws Exception 
	 */	
	public List<CmsSalaryDetailBean> readDataFromMysql(SalaryDetailListScreenForm form) throws Exception;

}
