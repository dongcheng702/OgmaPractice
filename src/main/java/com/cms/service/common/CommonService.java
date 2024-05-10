package com.cms.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.common.MCodeMstBean;
import com.cms.mapper.common.CommonMapper;
import com.utils.ServiceUtils;

/**
 * ユーザー情報 Service
 */
@Service
public class CommonService {

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	ServiceUtils serviceUtils;
	/**
	 * ユーザー情報検索 @param userSearchRequest リクエストデータ
	 * 
	 * @return 検索結果
	 */
	public List<MCodeMstBean> select(MCodeMstBean bean) {
		return commonMapper.select(bean);
	}


}