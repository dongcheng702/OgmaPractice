package com.cms.service.practice1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.form.practice1.ReadDataBaseForm;
import com.cms.mapper.practice1.Practice1Mapper;
import com.exception.BusinessException;

/**
 * 社員情報取得サービス
 */
@Service
public class ReadDataBaseServiceImpl implements ReadDataBaseService {

	@Autowired
	Practice1Mapper mapper;

	/**
	 * 社員情報を検索する
	 * 
	 * @param form 社員検索画面フォーム
	 * @return 社員Beanリスト
	 * @throws Exception 
	 */	
	@Override
	public List<CmsEmployeeBean> readDataFromMysql(ReadDataBaseForm form) throws Exception {
		
		CmsEmployeeBean param = new CmsEmployeeBean();
		if (!StringUtils.isEmpty(form.getEmployeeId()))	{
			param.setEmployeeId(form.getEmployeeId());
		}
		
		if (!StringUtils.isEmpty(form.getEmployeeName()))	{
			param.setName(form.getEmployeeName());
		}
		List<CmsEmployeeBean> results = mapper.select(param);


		if (results.size() == 0) {
			throw new BusinessException("検索結果がありません。");
		}
		
		return results;
	}

}