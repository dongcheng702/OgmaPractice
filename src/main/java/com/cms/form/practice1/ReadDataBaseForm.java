package com.cms.form.practice1;

import java.util.List;

import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.form.BaseForm;

import lombok.Getter;
import lombok.Setter;

/**
 * CSV取込フォーム
 */
@Getter
@Setter
public class ReadDataBaseForm extends BaseForm{
	//社員ID
	private String employeeId;
	//社員名
	private String employeeName;
	
	private List<CmsEmployeeBean> results;
}