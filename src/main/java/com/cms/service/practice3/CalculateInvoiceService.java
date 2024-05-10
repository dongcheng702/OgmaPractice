package com.cms.service.practice3;

import java.util.List;

import com.cms.entity.invoice.CmsInvoiceBean;
/**
 * 請求書計算Service
 */
public interface CalculateInvoiceService<T> {

	/**
	 * 請求書データを登録する
	 *  
	 * @param employeeId 社員ID
	 * @param workMonth 勤務年月
	 * @return 戻り値（エラー情報）
	 * @throws Exception 
	 */	
	public List<CmsInvoiceBean> calculateWorkHourAndSalary(String employeeId,String workMonth) throws Exception;

}
