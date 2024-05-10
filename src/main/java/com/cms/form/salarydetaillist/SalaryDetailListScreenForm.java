package com.cms.form.salarydetaillist;

import java.util.List;

import com.cms.entity.salarydetaillist.CmsSalaryDetailBean;
import com.cms.form.BaseForm;

import lombok.Getter;
import lombok.Setter;

/**
 * 給料一覧画面フォーム
 */
@Getter
@Setter
public class SalaryDetailListScreenForm extends BaseForm{

	//社員ID
	private String employeeId;
	//給料年月
	private String salaryMonth;
	
	//給料一覧画面の検索結果
	private List<CmsSalaryDetailBean> results;
}