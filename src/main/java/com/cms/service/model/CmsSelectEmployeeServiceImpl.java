package com.cms.service.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.mapper.employee.CmsEmployeeMapper;

/**
 * 社員選択画面 Service
 */
@Service
public class CmsSelectEmployeeServiceImpl implements CmsSelectEmployeeService {

	/****** Mapper ******/
	@Autowired
	CmsEmployeeMapper businessMapper;

	/**
	 * 社員選択画面_検索処理
	 * 
	 * @param bean 社員Bean
	 * @return 検索結果
	 */
	public List<CmsEmployeeBean> select(CmsEmployeeBean bean) {
		
		List<CmsEmployeeBean> retList = businessMapper.select(bean);
		
		return retList;
	}

}