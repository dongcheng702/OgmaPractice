package com.cms.service.mysalarydetaillist;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.mysalarydetaillist.MyCmsSalaryDetailBean;
import com.cms.form.mysalarydetaillist.MySalaryDetailListScreenForm;
import com.cms.mapper.mysalarydetaillist.MySalaryDetailListScreenMapper;

@Service
public class MySalaryDetailListScreenServicelmpl implements MySalaryDetailListScreenService{

	@Autowired
	MySalaryDetailListScreenMapper mapper;
	
	@Override
	public List<MyCmsSalaryDetailBean> readDataFromMysql(MySalaryDetailListScreenForm form) throws Exception {
		MyCmsSalaryDetailBean param = new MyCmsSalaryDetailBean();
		if(!StringUtils.isEmpty(form.getEmployeeId())) {
			param.setEmployeeId(form.getEmployeeId());
		}
		if (!StringUtils.isEmpty(form.getSalaryMonth()))	{
			param.setSalaryMonth(form.getSalaryMonth().replace("-", ""));
		}
		List<MyCmsSalaryDetailBean> results = mapper.select(param);
		
		return results;
	}

}
