package com.cms.service.restapisample;

import java.util.List;

import com.cms.entity.employee.CmsEmployeeBean;

public interface RestApiSampleService {

	public List<CmsEmployeeBean> getEmployeeById(String id);
	
	public CmsEmployeeBean createEmployee(CmsEmployeeBean bean);
	
}
