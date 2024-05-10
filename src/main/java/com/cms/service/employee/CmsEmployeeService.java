package com.cms.service.employee;

import com.cms.form.cmsemployee.CmsEmployeeForm;
import com.cms.form.cmsemployee.CmsEmployeeListForm;

public interface CmsEmployeeService {

	/**
	 * 一覧画面
	 */	
	//一覧画面検索
	public CmsEmployeeListForm select(CmsEmployeeListForm form);
	//一覧画面【削除】
	public CmsEmployeeListForm delete(CmsEmployeeListForm form);
	public CmsEmployeeListForm deleteAll(CmsEmployeeListForm form);
	public CmsEmployeeListForm optimismCheck(CmsEmployeeListForm form);
	
	/**
	 * 新規画面
	 */
	//新規画面【データ保存】
	public CmsEmployeeForm insert(CmsEmployeeForm form);
	
	/**
	 * 更新画面
	 */	
	//更新画面【初期化】
	public CmsEmployeeForm editInit(CmsEmployeeForm form);
	//更新画面【データ保存】	
	public void update(CmsEmployeeForm form);

	/**
	 * 参照画面
	 */	
	//参照画面【初期化】
	public CmsEmployeeForm readInit(CmsEmployeeForm form);
	
	


}
