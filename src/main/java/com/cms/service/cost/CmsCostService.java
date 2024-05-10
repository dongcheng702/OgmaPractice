package com.cms.service.cost;

import java.util.List;

import javax.transaction.SystemException;

import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.form.cost.CmsCostForm;
import com.cms.form.cost.CmsCostListForm;

public interface CmsCostService {

	/**
	 * 一覧画面
	 */	
	//一覧画面検索
	public CmsCostListForm select(CmsCostListForm form);
	//一覧画面【削除】
	public CmsCostListForm delete(CmsCostListForm form);
	public CmsCostListForm deleteAll(CmsCostListForm form);
	
	/**
	 * 新規画面
	 * @throws SystemException 
	 */
	//新規画面【初期化】
	public CmsCostForm insertInit(CmsCostForm form) throws SystemException;
	//新規画面【データ保存】
	public CmsCostForm insert(CmsCostForm form);
	
	/**
	 * 更新画面
	 * @throws SystemException 
	 */	
	//更新画面【初期化】
	public CmsCostForm editInit(CmsCostForm form) throws SystemException;
	//更新画面【データ保存】	
	public void update(CmsCostForm form);

	/**
	 * 参照画面
	 * @throws SystemException 
	 */	
	//参照画面【初期化】
	public CmsCostForm readInit(CmsCostForm form) throws SystemException;
	

	public List<CmsEmployeeBean> searchEmployees(String name);
}
