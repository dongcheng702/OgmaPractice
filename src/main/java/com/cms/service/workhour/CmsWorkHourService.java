package com.cms.service.workhour;

import com.cms.form.cmsworkhour.CmsWorkHourForm;

public interface CmsWorkHourService {

	/**
	 * 一覧画面
	 */	
	//一覧画面検索
	public CmsWorkHourForm select(CmsWorkHourForm form);
	//一覧画面【削除】
	public CmsWorkHourForm delete(CmsWorkHourForm form);
	
	/**
	 * 新規画面
	 */
	//新規画面【初期化】
	public CmsWorkHourForm insertInit(CmsWorkHourForm form);
	//新規画面【データ保存】
	public CmsWorkHourForm insert(CmsWorkHourForm form);
	
	/**
	 * 更新画面
	 */	
	//更新画面【初期化】
	public CmsWorkHourForm editInit(CmsWorkHourForm form);
	//更新画面【データ保存】	
	public void update(CmsWorkHourForm form);

	/**
	 * 参照画面
	 */	
	//参照画面【初期化】
	public CmsWorkHourForm readInit(CmsWorkHourForm form);
}
