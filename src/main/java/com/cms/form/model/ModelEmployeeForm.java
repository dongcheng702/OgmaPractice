package com.cms.form.model;

import java.util.List;

import com.cms.entity.employee.CmsEmployeeBean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * ユーザー情報 検索用リクエストデータ
 */
@Getter
@Setter
@Data
public class ModelEmployeeForm {

	/* 検索欄の項目 */
	private List<CmsEmployeeBean> codeList;


	private String name;

}