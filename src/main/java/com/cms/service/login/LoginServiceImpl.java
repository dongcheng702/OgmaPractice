package com.cms.service.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.form.login.LoginForm;
import com.cms.mapper.employee.CmsEmployeeMapper;

/**
 * ユーザー情報 Service
 */
@Service
public class LoginServiceImpl implements LoginService {

	/****** Mapper ******/
	@Autowired
	CmsEmployeeMapper mapper;

	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 */
	public LoginForm selectLoginInfo(LoginForm form) {

		// ログイン情報を検索する
		CmsEmployeeBean paramBean = new CmsEmployeeBean();
		paramBean.setLoginId(form.getLoginId());

		List<CmsEmployeeBean> searchResults = mapper.select(paramBean);
		if (!CollectionUtils.isEmpty(searchResults)) {

			form.setResults(searchResults);
		}

		return form;
	}
}