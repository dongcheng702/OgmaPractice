package com.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.common.GeneralMstBean;
import com.cms.mapper.common.GeneralMstMapper;

@Service
public class ServiceUtils {

	@Autowired
	GeneralMstMapper generalMstMapper;

	/**
	 * 選択リストの設定
	 * 
	 * @param key 汎用マスタのキー
	 * @throws SystemException 
	 */
	public Map<String, String> getGeneralMastInfo(String key) throws SystemException {

		Map<String, String> mp = new HashMap<String, String>();

		for (GeneralMstBean bean : selectGeneralMastInfo(key)) {
			mp.put(bean.getGeneralId(), bean.getGeneralName());
		}

		return mp;
	}

	/**
	 * 選択リストの設定
	 * 
	 * @param generalList 汎用マスタのリスト
	 * @param defaultVal  デフォルト値
	 * @throws SystemException 
	 */
	public Map<String, String> getGeneralMastInfo(String key, String defaultVal) throws SystemException {

		Map<String, String> mp = new HashMap<String, String>();

		// デフォルト値
		mp.put("", defaultVal);
		for (GeneralMstBean bean : selectGeneralMastInfo(key)) {
			mp.put(bean.getGeneralId(), bean.getGeneralName());
		}

		return mp;
	}

	/**
	 * 汎用マスタ情報の取得
	 * 
	 * @param key 汎用マスタのキー
	 * @throws SystemException 
	 */
	private List<GeneralMstBean> selectGeneralMastInfo(String key) throws SystemException {

		GeneralMstBean inBean = new GeneralMstBean();
		inBean.setGeneralKey(key);
		List<GeneralMstBean> ret = generalMstMapper.select(inBean);
		if (ret == null || ret.size() == 0) {
			throw new SystemException("汎用マスタは存在しません。（汎用ID:）"+key);
		}
		return ret;
	}
}
