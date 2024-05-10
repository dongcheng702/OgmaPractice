package com.cms.form.practice3;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.cms.entity.invoice.CmsInvoiceBean;
import com.cms.form.BaseForm;

import lombok.Getter;
import lombok.Setter;

/**
 * CSV取込フォーム
 */
@Getter
@Setter
public class CalculateWorkHourForm extends BaseForm{

	//社員ID
	@NotEmpty
    @Length(max = 8 ,message="8文字以内の社員IDをご入力ください。")
	private String employeeId;
	//勤務年月
	private String workMonth;
	
	//請求書
	private List<CmsInvoiceBean> results;
	
}