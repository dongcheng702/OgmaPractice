package com.cms.mapper.practice1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cms.entity.employee.CmsEmployeeBean;

/**
 * 練習１ Mapper
 */
@Mapper
public interface Practice1Mapper {

	
	/**
	 * ユーザー情報検索
	 * 
	 * @param user 検索用リクエストデータ
	 * @return ユーザー情報
	 */
	List<CmsEmployeeBean> select(CmsEmployeeBean bean);


}