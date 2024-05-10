package com.cms.service.employee;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cms.common.MessageConst;
import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.form.cmsemployee.CmsEmployeeForm;
import com.cms.form.cmsemployee.CmsEmployeeListForm;
import com.cms.mapper.common.CommonMapper;
import com.cms.mapper.employee.CmsEmployeeMapper;
import com.exception.BusinessException;
import com.utils.CmsUtils;
import com.utils.ServiceUtils;

/**
 * ユーザー情報 Service
 */
@Service
public class CmsEmployeeServiceImpl implements CmsEmployeeService {

	/****** Mapper ******/
	@Autowired
	CmsEmployeeMapper mapper;

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	HttpSession session;

	@Autowired
	ServiceUtils serviceUtils;
	
	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 */
	public CmsEmployeeListForm select(CmsEmployeeListForm form) {
		// ログイン情報を検索する
		CmsEmployeeBean bean = new CmsEmployeeBean();
		
		//社員名
		bean.setName(CmsUtils.formatEmptyToNull(form.getName()));

		if (StringUtils.isNotEmpty(form.getSessionEmployeeId()) && "J5".equals(form.getSessionJobType())) {
			/**
			 * 社員ID
			 * ・{社員:J5}の場合、社員自己の費用を抽出する
			 * ・{社員:J5}以外の場合、社員全体の費用を抽出する
			 */
			bean.setEmployeeId(form.getSessionEmployeeId());
		}
		
		bean.setEmployeeId(form.getEmployeeId());
		
		//社員区分
		if (!"2".equals(form.getSelectedEmployeeKbn())){
			bean.setEmployeeKbn(CmsUtils.formatEmptyToNull(form.getSelectedEmployeeKbn()));
		}
		
		//性別
		if (!"2".equals(form.getSelectedSexy())){
			bean.setSex(CmsUtils.formatEmptyToNull(form.getSelectedSexy()));
		}
		
		//生年月
		if (StringUtils.isNotEmpty(form.getMonth())) {
		    bean.setBirthday(form.getMonth()+"-01");
		}

		List<CmsEmployeeBean> retList = mapper.select(bean);
		if (retList == null || retList.size() == 0) {
			throw new BusinessException("検索結果はありません。");
		}
		//検索
		form.setResults(retList);

		return form;
	}

	/**
	 * ユーザー情報検索
	 * 
	 * @param form フォーム
	 * @return 検索結果
	 */
	public CmsEmployeeForm insert(CmsEmployeeForm form) {

		// ログイン情報を検索する
		CmsEmployeeBean bean = new CmsEmployeeBean();

		String maxId = mapper.selectMaxId();
		
		bean.setEmployeeId(String.valueOf(Integer.valueOf(maxId) + 1)); // 社員ID
		bean.setName(form.getName()); // 名前
		bean.setSex(form.getSelectedSexy()); // 性別
		bean.setBirthday(form.getBirthday()); // 生年月日
		bean.setAddress(form.getAddress()); // 住所
		bean.setPhone(form.getPhone()); // 携帯
		bean.setJoiningDate(form.getJoiningDate()); // 入社年月日
		bean.setMail(form.getMail()); // メール
		bean.setJobType(form.getSelectedJobType()); // 職種
		bean.setJobLevel("TMP"); // 職種レベル
		bean.setEmployeeKbn(form.getSelectedEmployeeKbn());// 職種レベル
		bean.setLoginId(form.getMail()); // ログインID
		bean.setPassword(form.getMail()); // パスワード
		bean.setTopWorkHour(Integer.valueOf(form.getTopWorkHour())); // 勤務時間上限
		bean.setDownWorkHour(Integer.valueOf(form.getDownWorkHour())); // 勤務時間下限
		bean.setSalary(new BigDecimal(form.getSalary())); // 給料
		bean.setEmployeeType(form.getSelectedEmployeeType()); // 社員種別
		bean.setHasTax(form.getSelectedHasTax()); // 税金有無
		mapper.insert(bean);

		return form;
	}


	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 */
	public CmsEmployeeForm editInit(CmsEmployeeForm form) {

		// ログイン情報を検索する
		CmsEmployeeBean sqlBean = new CmsEmployeeBean();
		sqlBean.setEmployeeId(form.getEmployeeId());
		
		List<CmsEmployeeBean> searchResults = mapper.select(sqlBean);
		if (!CollectionUtils.isEmpty(searchResults)) {
			CmsEmployeeBean result = searchResults.get(0);

			form.setEmployeeId(result.getEmployeeId()); // 社員ID
			form.setName(result.getName()); // 名前
			form.setSelectedSexy(result.getSex()); // 性別
			form.setBirthday(result.getBirthday().toString());    // 生年月日
			form.setAddress(result.getAddress()); // 住所
			form.setPhone(result.getPhone()); // 携帯
			form.setJoiningDate(result.getJoiningDate().toString()); // 入社年月日
			form.setMail(result.getMail()); // メール
			form.setJobType(result.getJobType()); // 職種
			form.setSelectedJobType(result.getJobType());// 職種(選択された)
			form.setSelectedEmployeeKbn(result.getEmployeeKbn());// 社員区分
			form.setSalary(result.getSalary().toString());// 携帯
			form.setTopWorkHour(String.valueOf(result.getTopWorkHour())); // 勤務時間上限
			form.setDownWorkHour(String.valueOf(result.getDownWorkHour())); // 勤務時間下限
			form.setSalary(String.valueOf(result.getSalary())); // 給料
			form.setSelectedEmployeeType(String.valueOf(result.getEmployeeType())); // 社員種別
			form.setSelectedHasTax(String.valueOf(result.getHasTax())); // 税金有無
		}

		return form;
	}
	

	/**
	 * 社員情報を更新する
	 * 
	 * @param form 社員フォーム
	 */
	public void update(CmsEmployeeForm form) {
		// 画面から社員IDを取得する
		String employeeId = form.getEmployeeId();
		// ログイン情報を検索する
		CmsEmployeeBean bean = new CmsEmployeeBean();
		bean.setEmployeeId(employeeId);

		List<CmsEmployeeBean> employeeList = mapper.select(bean);
		CmsEmployeeBean updateBean = employeeList.get(0);

		// 8桁不足の場合、前に０を埋める
		updateBean.setEmployeeId(updateBean.getEmployeeId()); // 社員ID
		updateBean.setName(form.getName()); // 名前
		updateBean.setEmployeeKbn(form.getSelectedEmployeeKbn()); // 社員区分
		updateBean.setSex(form.getSelectedSexy()); // 性別
		updateBean.setBirthday(updateBean.getBirthday()); // 生年月日
		updateBean.setAddress(form.getAddress()); // 住所
		updateBean.setPhone(form.getPhone()); // 携帯
		updateBean.setJoiningDate(updateBean.getJoiningDate());// 入社年月日
		updateBean.setMail(form.getMail()); // メール
		updateBean.setJobType(form.getSelectedJobType()); // 職種
		updateBean.setJobLevel("DUMY"); // 職種
		updateBean.setLoginId(form.getMail()); // ログインID
		updateBean.setTopWorkHour(Integer.valueOf(form.getTopWorkHour())); // 勤務時間上限
		updateBean.setDownWorkHour(Integer.valueOf(form.getDownWorkHour())); // 勤務時間下限
		updateBean.setSalary(new BigDecimal(form.getSalary())); // 給料
		updateBean.setEmployeeType(form.getSelectedEmployeeType()); // 社員種別
		updateBean.setHasTax(form.getSelectedHasTax()); // 税金有無
		
		mapper.update(updateBean);
	}
	

	/**
	 * 社員情報を初期化する
	 * 
	 * @param form 社員フォーム
	 * @return 社員フォーム
	 */
	public CmsEmployeeForm readInit(CmsEmployeeForm form) {

		// ログイン情報を検索する
		CmsEmployeeBean sqlBean = new CmsEmployeeBean();
		sqlBean.setEmployeeId(form.getEmployeeId());

		List<CmsEmployeeBean> searchResults = mapper.select(sqlBean);
		if (!CollectionUtils.isEmpty(searchResults)) {
			CmsEmployeeBean result = searchResults.get(0);

			form.setEmployeeId(result.getEmployeeId()); // 社員ID
			form.setName(result.getName()); // 名前
			form.setSelectedEmployeeKbn(result.getEmployeeKbn()); // 社員区分
			form.setSelectedSexy(result.getSex()); // 性別
			form.setBirthday(result.getBirthday());   // 生年月日
			form.setAddress(result.getAddress()); // 住所
			form.setPhone(result.getPhone()); // 携帯
			form.setJoiningDate(result.getJoiningDate()); // 入社年月日
			form.setMail(result.getMail()); // メール
			form.setJobType(result.getJobType()); // 職種
			form.setSelectedJobType(result.getJobType()); // 職種(選択された)
			form.setTopWorkHour(String.valueOf(result.getTopWorkHour())); // 勤務時間上限
			form.setDownWorkHour(String.valueOf(result.getDownWorkHour())); // 勤務時間下限
			form.setSalary(result.getSalary().toString()); // 給料
			form.setSelectedEmployeeType(String.valueOf(result.getEmployeeType())); // 社員種別
			form.setSelectedHasTax(String.valueOf(result.getHasTax())); // 税金有無
		}

		return form;
	}
	
	/**
	 * ユーザー情報を削除する
	 * 
	 * @param form フォーム
	 * @return 検索結果
	 */
	public CmsEmployeeListForm delete(CmsEmployeeListForm form) {

		//楽観チェックを行う
		optimismCheck(form);
		
		//社員ID
		String[] param = form.getSelectedItemId().split("_");
		String employeeId = param[0];
		
		CmsEmployeeBean deleteBean = new CmsEmployeeBean();
		deleteBean.setEmployeeId(employeeId);
		mapper.delete(deleteBean);
		
		return form;
	}
	
	/**
	 * ユーザー情報を削除する
	 * 
	 * @param form フォーム
	 * @return 検索結果
	 */
	public CmsEmployeeListForm deleteAll(CmsEmployeeListForm form) {
		
		String[] delIds = form.getSelectedItemIds().split(",");
		mapper.deleteAll(delIds);

		return form;
	}
	

	
	/**
	 * 楽観チェック
	 * 
	 * @param form フォーム
	 * 
	 * @return 検索結果
	 */
	public CmsEmployeeListForm optimismCheck(CmsEmployeeListForm form) {
		
		boolean btnFlg = form.isBtnFlg();
		
		String[] param = form.getSelectedItemId().split("_");

		//社員ID
		String employeeId = param[0];
		//更新日
		String updDateTime = param[1];
		
		//社員存在チェック
		CmsEmployeeBean sellectBean = new CmsEmployeeBean();
		sellectBean.setEmployeeId(employeeId);
		Integer employeeCount = mapper.selectRecordCount(sellectBean);
		if (employeeCount == 0) {
			throw new BusinessException(MessageConst.NOT_EXIST_CHECK);
		}
		//楽観チェック
		sellectBean.setUpdateDay(updDateTime);
		employeeCount = mapper.selectRecordCount(sellectBean);

		if (btnFlg && employeeCount == 0) {
			//編集ボタンOr削除ボタンを押下、レコードがほかの担当者に変更される場合
			throw new BusinessException(MessageConst.RECORD_IS_CHANGED);
		}
		return form;
	}
}