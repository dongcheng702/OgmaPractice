package com.cms.form.popup;

import java.util.List;

import com.cms.entity.salary.CmsSalaryDetailBean;
import com.cms.form.BaseForm;

import lombok.Data;

@Data
public class PopupForm extends BaseForm{

	/* 検索結果 */
	private List<CmsSalaryDetailBean> results;

	//社員ID
	private String employeeId;
	//社員名
	private String name;
	//給料年月
	private String month;
}
