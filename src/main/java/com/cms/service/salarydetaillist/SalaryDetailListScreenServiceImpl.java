package com.cms.service.salarydetaillist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.cms.entity.salarydetaillist.CmsSalaryDetailBean;
import com.cms.form.salarydetaillist.SalaryDetailListScreenForm;
import com.cms.mapper.salarydetaillist.SalaryDetailListScreenMapper;
import com.exception.BusinessException;

/**
 * 社員情報取得サービス
 */
@Service
public class SalaryDetailListScreenServiceImpl implements SalaryDetailListScreenService {

	@Autowired
	SalaryDetailListScreenMapper mapper;

	/**
	 * 社員情報を検索する
	 * 
	 * @param form 社員検索画面フォーム
	 * @return 社員Beanリスト
	 * @throws Exception 
	 */	
	@Override
	public List<CmsSalaryDetailBean> readDataFromMysql(SalaryDetailListScreenForm form) throws Exception {
		
		CmsSalaryDetailBean param = new CmsSalaryDetailBean();
		if (!StringUtils.isEmpty(form.getEmployeeId()))	{
			param.setEmployeeId(form.getEmployeeId());
		}
		
		if (!StringUtils.isEmpty(form.getSalaryMonth()))	{
			param.setSalaryMonth(form.getSalaryMonth().replace("-", ""));
		}
		List<CmsSalaryDetailBean> results = mapper.select(param);

		if (results.size() == 0) {
			throw new BusinessException("検索結果がありません。");
		}
		
		return results;
	}

}