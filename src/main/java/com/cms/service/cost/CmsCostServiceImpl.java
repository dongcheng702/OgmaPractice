package com.cms.service.cost;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.SystemException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cms.common.Constant;
import com.cms.entity.cost.CmsCostBean;
import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.form.cost.CmsCostForm;
import com.cms.form.cost.CmsCostListForm;
import com.cms.mapper.cost.CmsCostMapper;
import com.cms.mapper.employee.CmsEmployeeMapper;
import com.utils.CmsUtils;
import com.utils.ServiceUtils;

/**
 * ユーザー情報 Service
 */
@Service
public class CmsCostServiceImpl implements CmsCostService {

	/****** Mapper ******/
	@Autowired
	CmsCostMapper mapper;

	@Autowired
	HttpSession session;

	@Autowired
	ServiceUtils serviceUtils;
	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 */
	public CmsCostListForm select(CmsCostListForm form) {
		
		// 検索条件を条件に設定する
		CmsCostBean bean = new CmsCostBean();
		
		if (StringUtils.isNotEmpty(form.getSessionEmployeeId()) && "J5".equals(form.getSessionJobType())) {
			/**
			 * 社員ID
			 * ・{社員:J5}の場合、社員自己の費用を抽出する
			 * ・{社員:J5}以外の場合、社員全体の費用を抽出する
			 */
			bean.setEmployeeId(form.getSessionEmployeeId());
		}
		
		if (StringUtils.isNotEmpty(form.getName())) {
			//社員名
			bean.setEmployeeNm(form.getName());
		}
		
		if (StringUtils.isNotEmpty(form.getMonth())) {
			//費用発生年月
			bean.setCostMonth(form.getMonth());
		}
		
		//検索結果を画面に設定する
		form.setResults(mapper.select(bean));

		return form;
	}

	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 * @throws SystemException 
	 */
	public CmsCostForm insertInit(CmsCostForm form) throws SystemException {
		// フォーム初期化
		initForm(form);

		return form;
	}

	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 * @throws SystemException 
	 */
	public CmsCostForm editInit(CmsCostForm form) throws SystemException {

		// フォーム初期化
		initForm(form);

		// ログイン情報を検索する
		CmsCostBean sqlBean = new CmsCostBean();
		sqlBean.setCostId(form.getCostId());

		List<CmsCostBean> searchResults = mapper.select(sqlBean);
		if (!CollectionUtils.isEmpty(searchResults)) {
			CmsCostBean bean = searchResults.get(0);

		  form.setCostId(bean.getCostId());               //費用ID
		  Date costDay = CmsUtils.FormatToDate(bean.getCostDay(), "yyyy-MM-dd");
		  form.setCostDay(costDay);                       //費用発生日
		  form.setCostNote(bean.getCostNote());           //費用コメント
		  form.setSelectedCostType(bean.getCostType());   //費用種別
		  form.setCostAmount(bean.getCostAmount());       //費用金額
		  form.setHasTax(bean.getHasTax());               //税金有無
		  form.setTaxNote(bean.getTaxNote());             //税金備考
		  form.setEmployeeId(bean.getEmployeeId());       //社員ID
	    }

		return form;
	}

	/**
	 * ユーザー情報検索
	 * 
	 * @param form フォーム
	 * @return 検索結果
	 */
	public CmsCostForm insert(CmsCostForm form) {

		// ログイン情報を検索する
		CmsCostBean bean = new CmsCostBean();

		String maxId = mapper.selectMaxId();
		//1件も登録されない場合、初期値を１に設定する
		maxId = (maxId == null)? "0":maxId; 
		// 8桁不足の場合、前に０を埋める
		String id = String.format(Constant.FORMAT_EMPLOYEEID, Integer.valueOf(maxId) + 1);

		bean.setCostId(id);//費用ID
		String costDay = CmsUtils.formatDateToString(form.getCostDay(), "yyyy-MM-dd");
		bean.setCostDay(costDay);//費用発生日
		bean.setCostNote(form.getCostNote());//費用コメント
		bean.setCostType(form.getSelectedCostType());//費用種別
		bean.setCostAmount(form.getCostAmount());//費用金額
		bean.setHasTax(form.getHasTax());//税金有無
		bean.setTaxNote(form.getTaxNote());//税金備考
		bean.setEmployeeId(form.getEmployeeId()); // 社員ID
		  
		mapper.insert(bean);

		return form;
	}

	/**
	 * 費用情報を削除する
	 * 
	 * @param form フォーム
	 * @return 検索結果
	 */
	public CmsCostListForm delete(CmsCostListForm form) {

		CmsCostBean bean = new CmsCostBean();
		bean.setCostId(form.getCostId());// 社員ID

		mapper.delete(bean);

		return select(form);
	}
	
	/**
	 * 複数件の費用を削除する
	 * 
	 * @param form フォーム
	 * @return 検索結果
	 */
	public CmsCostListForm deleteAll(CmsCostListForm form) {
		
		String[] delIds = form.getDelItemIds().split(",");
		mapper.deleteAll(delIds);

		return select(form);
	}
	/**
	 * 費用情報フォームを初期化する
	 * 
	 * @param 費用フォーム
	 * @throws SystemException 
	 */
	private void initForm(CmsCostForm form) throws SystemException {
		
		//費用種別
		form.setCostTypeList(serviceUtils.getGeneralMastInfo("COST_TYPE"));
		form.setSelectedCostType("C1");
		
		//税金有無
		form.setHasTaxList(serviceUtils.getGeneralMastInfo("TAX_FLG"));
		form.setHasTax("1");
	}

	/**
	 * 社員情報を更新する
	 * 
	 * @param form 社員フォーム
	 */
	public void update(CmsCostForm form) {
		
		// 費用ID
		CmsCostBean bean = new CmsCostBean();
		bean.setCostId(form.getCostId());

		List<CmsCostBean> retList = mapper.select(bean);
		CmsCostBean updateBean = retList.get(0);

		// 8桁不足の場合、前に０を埋める
		updateBean.setCostId(form.getCostId());               //費用ID
		String costDay = CmsUtils.formatDateToString(form.getCostDay(), "yyyy-MM-dd");
		updateBean.setCostDay(costDay);                       //費用発生日
		updateBean.setCostNote(form.getCostNote());           //費用コメント
		updateBean.setCostType(form.getSelectedCostType());   //費用種別
		updateBean.setCostAmount(form.getCostAmount());       //費用金額
		updateBean.setHasTax(form.getHasTax());               //税金有無
		updateBean.setTaxNote(form.getTaxNote());             //税金備考
		mapper.update(updateBean);
	}

	/**
	 * 社員情報を初期化する
	 * 
	 * @param form 社員フォーム
	 * @return 社員フォーム
	 * @throws SystemException 
	 */
	public CmsCostForm readInit(CmsCostForm form) throws SystemException {
		// フォーム初期化
		initForm(form);

		// ログイン情報を検索する
		CmsCostBean sqlBean = new CmsCostBean();
		if (StringUtils.isNotEmpty(form.getCostId())) {
			sqlBean.setCostId(form.getCostId());
		}

		List<CmsCostBean> searchResults = mapper.select(sqlBean);
		if (!CollectionUtils.isEmpty(searchResults)) {
			CmsCostBean bean = searchResults.get(0);
			  form.setCostId(bean.getCostId());                 //費用ID
			  Date costDay = CmsUtils.FormatToDate(bean.getCostDay(), "yyyy-MM-dd");
			  form.setCostDay(costDay);                         //費用発生日
			  form.setCostNote(bean.getCostNote());             //費用コメント
			  form.setSelectedCostType(bean.getCostType());     //費用種別
			  form.setCostAmount(bean.getCostAmount());         //費用金額
			  form.setHasTax(bean.getHasTax());                 //税金有無
			  form.setTaxNote(bean.getTaxNote());               //税金備考
			  form.setEmployeeId(bean.getEmployeeId());         //社員ID
		}

		return form;
	}
	

	@Autowired
	CmsEmployeeMapper employeeMapper;
	/**
	 * 社員情報の検索
	 * 
	 * @param name 社員名
	 * @return 検索結果
	 */
	public List<CmsEmployeeBean> searchEmployees(String name) {
		CmsEmployeeBean bean = new CmsEmployeeBean();
		bean.setName(name);
		List<CmsEmployeeBean> retList = employeeMapper.select(bean);
		return retList;
	}
}