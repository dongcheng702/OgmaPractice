package com.cms.service.workhour;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cms.common.Constant;
import com.cms.entity.cmsworkhour.CmsWorkHourBean;
import com.cms.form.cmsworkhour.CmsWorkHourForm;
import com.cms.mapper.common.CommonMapper;
import com.cms.mapper.workhour.CmsWorkHourMapper;
import com.utils.CmsUtils;
import com.utils.ServiceUtils;
/*
 * ユーザー情報 Service
 */
@Service
public class CmsWorkHourServiceImpl implements CmsWorkHourService {

	/****** Mapper ******/
	@Autowired
	CmsWorkHourMapper mapper;

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
	public CmsWorkHourForm selectLoginInfo(CmsWorkHourForm form) {

		// ログイン情報を検索する
		CmsWorkHourBean paramBean = new CmsWorkHourBean();
//		paramBean.setLoginId(form.getLoginId());

		List<CmsWorkHourBean> searchResults = mapper.select(paramBean);
		if (!CollectionUtils.isEmpty(searchResults)) {
			CmsWorkHourBean result = searchResults.get(0);

//			form.setCompanyId(result.getCompanyId());
			form.setEmployeeId(result.getEmployeeId());
			form.setResults(searchResults);
		}

		return form;
	}

	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 */
	public CmsWorkHourForm select(CmsWorkHourForm form) {
		// ログイン情報を検索する
		CmsWorkHourBean bean = new CmsWorkHourBean();
		form.setResults(mapper.selectList(bean));

		return form;
	}

	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 */
	public CmsWorkHourForm insertInit(CmsWorkHourForm form) {
		try {

			// フォーム初期化
			form = initForm();
			
		} catch (SystemException se) {
			System.out.println(se.getMessage());
		}

		return form;
	}

	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 */
	public CmsWorkHourForm editInit(CmsWorkHourForm form) {
		CmsWorkHourForm editForm = null;
		try {		
			// フォーム初期化
			editForm = initForm();
	
			// ログイン情報を検索する
			CmsWorkHourBean sqlBean = new CmsWorkHourBean();
			sqlBean.setEmployeeId(form.getEmployeeId());
	
			List<CmsWorkHourBean> searchResults = mapper.select(sqlBean);
			if (!CollectionUtils.isEmpty(searchResults)) {
				CmsWorkHourBean result = searchResults.get(0);
	
				editForm.setEmployeeId(result.getEmployeeId()); // 社員ID
	
			}
		} catch (SystemException se) {
			System.out.println(se.getMessage());
		}
		
		return editForm;
	}

	/**
	 * ユーザー情報検索
	 * 
	 * @param form フォーム
	 * @return 検索結果
	 */
	public CmsWorkHourForm insert(CmsWorkHourForm form) {

		// ログイン情報を検索する
		CmsWorkHourBean bean = new CmsWorkHourBean();

		List<CmsWorkHourBean> employeeList = mapper.select(bean);
		// 8桁不足の場合、前に０を埋める
		String id = String.format(Constant.FORMAT_EMPLOYEEID, employeeList.size() + 1);
		bean.setEmployeeId(id); // 社員ID

		mapper.insert(bean);

		return form;
	}

	/**
	 * ユーザー情報を削除する
	 * 
	 * @param form フォーム
	 * @return 検索結果
	 */
	public CmsWorkHourForm delete(CmsWorkHourForm form) {

		CmsWorkHourBean bean = new CmsWorkHourBean();
		bean.setEmployeeId(form.getEmployeeId());// 社員ID

		mapper.delete(bean);

		return selectLoginInfo(form);
	}

	/**
	 * ユーザー情報を初期化する
	 * 
	 * @return 社員フォーム
	 * @throws SystemException 
	 */
	private CmsWorkHourForm initForm() throws SystemException {
		CmsWorkHourForm form = new CmsWorkHourForm();

		// 選択リスト{SEXY:性別}
		form.setSexyList(serviceUtils.getGeneralMastInfo("SEXY"));
		form.setSelectedSexy("M");

		// 選択リスト{JOB_TYPE:職種}
		form.setJobTypeList(serviceUtils.getGeneralMastInfo("JOB_TYPE"));
		form.setSelectedJobType("J5");// 項目【職種】のデフォルト値を社員に設定する

		// 社員区分（ラジオボタン）
		form.setGenders(CmsUtils.createGenders());
		// 社員区分（ラジオボタンの選択値）
		form.setSelectGender("社員");

		return form;
	}

	/**
	 * 社員情報を更新する
	 * 
	 * @param form 社員フォーム
	 */
	public void update(CmsWorkHourForm form) {
		// 画面から社員IDを取得する
		String employeeId = form.getEmployeeId();
		// ログイン情報を検索する
		CmsWorkHourBean bean = new CmsWorkHourBean();
		bean.setEmployeeId(employeeId);

		List<CmsWorkHourBean> employeeList = mapper.select(bean);
		CmsWorkHourBean updateBean = employeeList.get(0);

		// 8桁不足の場合、前に０を埋める
		updateBean.setEmployeeId(updateBean.getEmployeeId()); // 社員ID
	
		mapper.update(updateBean);
	}

	/**
	 * 社員情報を初期化する
	 * 
	 * @param form 社員フォーム
	 * @return 社員フォーム
	 */
	public CmsWorkHourForm readInit(CmsWorkHourForm form) {
		
		CmsWorkHourForm viewForm = null;
		try {
			// フォーム初期化
			viewForm = initForm();
	
			// ログイン情報を検索する
			CmsWorkHourBean sqlBean = new CmsWorkHourBean();
			sqlBean.setEmployeeId(form.getEmployeeId());
	
			List<CmsWorkHourBean> searchResults = mapper.select(sqlBean);
			if (!CollectionUtils.isEmpty(searchResults)) {
				CmsWorkHourBean result = searchResults.get(0);
	
				viewForm.setEmployeeId(result.getEmployeeId()); // 社員ID
		
			}
		} catch (SystemException se) {
			System.out.println(se.getMessage());
		}

		return viewForm;
	}
}