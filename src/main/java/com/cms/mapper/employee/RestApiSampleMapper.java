package com.cms.mapper.employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cms.entity.employee.CmsEmployeeBean;

/**
 * 社員情報 Mapper（RestApi）
 */
@Mapper
public interface RestApiSampleMapper {
	
	/**
	 * 社員情報検索
	 * 
	 * @param bean 検索条件
	 */
	List<CmsEmployeeBean> select(CmsEmployeeBean bean);
	
	/**
	 * 社員情報保存
	 * 
	 * @param bean 登録項目
	 * @return CmsEmployeeBean 登録後の社員情報
	 */
	CmsEmployeeBean save(CmsEmployeeBean bean);
}