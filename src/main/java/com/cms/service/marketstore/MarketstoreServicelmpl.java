package com.cms.service.marketstore;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.marketstore.MarketstoreBean;
import com.cms.form.marketstore.MarketstoreForm;
import com.cms.mapper.marketstore.MarketstoreMapper;
import com.exception.BusinessException;

@Service
public class MarketstoreServicelmpl implements MarketstoreService{

	@Autowired
	MarketstoreMapper storeMapper;

	@Override
	public List<MarketstoreBean> select(MarketstoreForm form) throws Exception{
		MarketstoreBean param = new MarketstoreBean();
		if (!StringUtils.isEmpty(form.getStoreId()))	{
			param.setStoreId(form.getStoreId());
		}
		if (!StringUtils.isEmpty(form.getStoreName()))	{
			param.setStoreName(form.getStoreName());
		}
		
		List<MarketstoreBean> results = storeMapper.select(param);
		
		if (results.size() == 0) {
			throw new BusinessException("検索結果がありません。");
		}
		
		return results;
	}
	

}
