package com.cms.service.marketstore;

import java.util.List;

import com.cms.entity.marketstore.MarketstoreBean;
import com.cms.form.marketstore.MarketstoreForm;

public interface MarketstoreService {
	
	//店铺检索
	public List<MarketstoreBean> select(MarketstoreForm form) throws Exception;
}
