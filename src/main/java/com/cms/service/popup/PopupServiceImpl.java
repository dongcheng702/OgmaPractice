package com.cms.service.popup;

import org.springframework.beans.factory.annotation.Autowired;

import com.cms.form.popup.PopupForm;
import com.cms.mapper.salary.CmsSalaryMapper;

public class PopupServiceImpl  implements PopupService{

	@Autowired
	CmsSalaryMapper salaryMapper;
	
	@Override
	public PopupForm select(PopupForm form) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	

}
