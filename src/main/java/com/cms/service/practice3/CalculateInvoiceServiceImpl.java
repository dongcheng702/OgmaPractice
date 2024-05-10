package com.cms.service.practice3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.cms.entity.cmsworkhour.CmsWorkHourBean;
import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.entity.invoice.CmsInvoiceBean;
import com.cms.mapper.practice3.Practice3Mapper;
import com.exception.BusinessException;
import com.utils.CmsUtils;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * 請求書計算ServiceImpl
 */
@SuppressWarnings("rawtypes")
@Service
public class CalculateInvoiceServiceImpl implements CalculateInvoiceService {

	@Autowired
	Practice3Mapper mapper;
	private CmsEmployeeBean employeeBean;
	private List<CmsWorkHourBean> workHoursList;
	private List<CmsInvoiceBean> registList;

	/**
	 * 請求書データを登録する
	 * 
	 * @param employeeId 社員ID
	 * @param workMonth　勤務年月
	 * @return List<CmsInvoiceBean> 計算後の請求書
	 */
	@Override
	public List<CmsInvoiceBean> calculateWorkHourAndSalary(String employeeId, String workMonth) throws Exception {

		// 変数の初期化
		workHoursList = new ArrayList<CmsWorkHourBean>();
		registList = new ArrayList<CmsInvoiceBean>();

		// チェック処理
		check(employeeId, workMonth);

		// 勤務時間を統計する
		calculate();

		// 請求書の登録
		registeData();

		// 登録結果を戻す
		return registList;
	}

	/**
	 * チェック処理
	 * 
	 * @param employeeId 社員ID
	 * @param workMonth  勤務年月
	 * 
	 * @throws Exception
	 */
	private void check(String employeeId, String workMonth) throws Exception {

		// ■入力チェック
		if (StringUtils.isEmpty(employeeId)) {
			// 社員の必須チェック
			throw new BusinessException("社員IDをご設定ください");
		}

		// ■8文字以内の文字列のチェック
		if (employeeId.length() > 8) {
			// 社員の必須チェック
			throw new BusinessException("8文字以内の社員IDをご入力ください。");
		}

		// ■マスタ存在チェック（社員情報）
		CmsEmployeeBean inputBean = new CmsEmployeeBean();
		inputBean.setEmployeeId(employeeId);
		employeeBean = mapper.selectEmployee(inputBean);
		if (employeeBean == null) {
			throw new BusinessException("社員はありません。");
		}

		if (inputBean != null) {
			BigDecimal hours = new BigDecimal(employeeBean.getTopWorkHour());
			if (hours.compareTo(new BigDecimal(0)) <= 0) {
				throw new BusinessException("社員情報に1以上の整数をご登録ください。");
			}
		}

		// ■マスタ存在チェック（勤務情報）
		CmsWorkHourBean workHour = new CmsWorkHourBean();
		workHour.setEmployeeId(employeeId);
		if (!StringUtils.isEmpty(workMonth)) {
			workHour.setWorkMonth(workMonth);
		}
		workHoursList = mapper.selectWorkHourList(workHour);
		if (workHoursList == null || workHoursList.size() == 0) {
			throw new BusinessException("該当社員の勤怠情報はありません。");
		}
	}

	/**
	 * 勤務時間計算
	 * 
	 */
	private void calculate() {

		// 月毎に勤務情報の1件目を保存する（共通項目を利用するため）
		Map<String, CmsWorkHourBean> keyMap = new HashMap<String, CmsWorkHourBean>();

		// 出勤時間の合計をMapに保存する
		Map<String, Integer> workHourMap = new HashMap<String, Integer>();

		String key = "";
		Integer workHourVal = 0;

		// ■勤務時間の合計
		for (CmsWorkHourBean bean : workHoursList) {
			key = bean.getEmployeeId() + bean.getWorkMonth();

			long diff = bean.getEndTime().getTime() - bean.getStartTime().getTime();
			// 勤務時間を算出する
			String hoursVal = CmsUtils.parseMillisecone(diff);
			if (workHourMap.containsKey(key)) {
				// 勤務時間を合計する
				workHourVal = workHourMap.get(key);
				workHourVal = workHourVal + Integer.valueOf(hoursVal);
				workHourMap.put(key, workHourVal);
			} else {
				workHourVal = Integer.valueOf(hoursVal);
				workHourMap.put(key, workHourVal);
				keyMap.put(key, bean);
			}
		}

		Date today = Calendar.getInstance().getTime();
		// 勤務年月の請求書を作成する
		CmsInvoiceBean insBean = new CmsInvoiceBean();
		for (CmsWorkHourBean bean : keyMap.values()) {
			insBean = new CmsInvoiceBean();
			key = bean.getEmployeeId() + bean.getWorkMonth();

			// 社員ID
			insBean.setEmployeeId(bean.getEmployeeId());
			// 勤務年月
			insBean.setWorkMonth(bean.getWorkMonth());
			// 会社
			insBean.setCompanyName("オージーエム");
			// 請求先
			insBean.setRequestFirst("NEC");
			// 標準勤務時間（例：180時間）
			insBean.setStandardBusinessHours(employeeBean.getTopWorkHour());
			// 基本給料
			insBean.setSalary(employeeBean.getSalary());
			// 月次勤務時間の合計
			insBean.setRealWorkHous(workHourMap.get(key));
			// 残業代の計算
			Integer hoursDiff = insBean.getRealWorkHous() - insBean.getStandardBusinessHours();
			BigDecimal salary = insBean.getSalary();
			BigDecimal standardBusinessHours = new BigDecimal(insBean.getStandardBusinessHours());
			BigDecimal workOvertimeSalary = salary.divide(standardBusinessHours, 0, BigDecimal.ROUND_HALF_UP)
					.multiply(new BigDecimal(hoursDiff));
			insBean.setDisabledGeneration(workOvertimeSalary);
			insBean.setRegistDay(today);
			insBean.setUpdateDay(today);

			insBean.setEmployeeName(employeeBean.getName());
			registList.add(insBean);
		}
	}

	/**
	 * 請求書の登録
	 * 
	 */
	private void registeData() {
		try {
			mapper.insertRecords(registList);
		} catch (DuplicateKeyException e1) {
			//登録キー重複エラー
			throw new BusinessException("指定条件の請求書がすでに登録されました。", e1);
		} catch (Exception se) {
			//予想外エラー
			throw new BusinessException(se.getMessage());			
		}
	}
}