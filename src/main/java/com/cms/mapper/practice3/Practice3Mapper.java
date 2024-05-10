package com.cms.mapper.practice3;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.cms.entity.cmsworkhour.CmsWorkHourBean;
import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.entity.invoice.CmsInvoiceBean;

@Mapper
public interface Practice3Mapper {
	/**
	 * 社員情報を検索する
	 * 
	 * @param bean 社員Bean
	 * @return 社員情報
	 */
	CmsEmployeeBean selectEmployee(CmsEmployeeBean bean);

	/**
	 * 勤務情報を検索する
	 * 
	 * @param bean 勤務情報Bean
	 * @return 勤務情報リスト
	 */
	List<CmsWorkHourBean> selectWorkHourList(CmsWorkHourBean bean);

	/**
	 * 請求書データを登録する
	 * 
	 * @param user 検索用リクエストデータ
	 * @return ユーザー情報
	 */
	void insertRecords(@Param("inputList") List<CmsInvoiceBean> inputList);
}
