package com.cms.service.restapisample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.mapper.employee.RestApiSampleMapper;

/**
 * ユーザー情報 Service
 */
@Service
public class RestApiSampleServiceImpl implements RestApiSampleService {

	/****** Mapper ******/
	@Autowired
	RestApiSampleMapper mapper;
	
	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 */
	public List<CmsEmployeeBean> getEmployeeById(String id) {
		// ログイン情報を検索する
		CmsEmployeeBean bean = new CmsEmployeeBean();
		
		bean.setEmployeeId(id);
		
		return mapper.select(bean);
	}

	@Override
	public CmsEmployeeBean createEmployee(CmsEmployeeBean bean) {
		
		return mapper.save(bean);
	}

}