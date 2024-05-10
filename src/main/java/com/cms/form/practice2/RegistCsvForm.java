package com.cms.form.practice2;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.cms.form.BaseForm;

import lombok.Getter;
import lombok.Setter;

/**
 * CSV取込フォーム
 */
@Getter
@Setter
public class RegistCsvForm extends BaseForm{

	//Csvファイルパス
	@NotEmpty
	private String filePath;
	
	//CSV項目件数
	@Pattern(regexp = "^[0-9]+$", message = "CSV項目数に数字をご入力ください。") 
	String itemCount;
	
	//登録件数
	private String registRecordCount;
}