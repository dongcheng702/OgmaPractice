package com.cms.form.login;

import java.util.List;

import com.cms.entity.employee.CmsEmployeeBean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * ログインフォーム
 */
@Getter
@Setter
@Data
public class LoginForm {

	/* 検索結果 */
	private List<CmsEmployeeBean> results;
	
	private String loginId;
	private String password;

}