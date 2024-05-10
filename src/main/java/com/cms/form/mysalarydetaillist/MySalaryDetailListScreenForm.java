package com.cms.form.mysalarydetaillist;

import java.util.List;

import com.cms.entity.mysalarydetaillist.MyCmsSalaryDetailBean;
import com.cms.form.BaseForm;

import lombok.Getter;
import lombok.Setter;
/**
 * 給料一覧画面練習
 */
@Getter
@Setter
public class MySalaryDetailListScreenForm extends BaseForm{

	private String employeeId;
	
	private String salaryMonth;
	
	private List<MyCmsSalaryDetailBean> results;
}
