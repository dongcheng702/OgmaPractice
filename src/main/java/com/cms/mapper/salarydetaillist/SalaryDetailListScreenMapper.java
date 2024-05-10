package com.cms.mapper.salarydetaillist;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cms.entity.salarydetaillist.CmsSalaryDetailBean;

/**
 * 練習１ Mapper
 */
@Mapper
public interface SalaryDetailListScreenMapper {

	
	/**
	 * 給料明細情報検索
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 給料明細情報
	 */
	List<CmsSalaryDetailBean> select(CmsSalaryDetailBean bean);


}