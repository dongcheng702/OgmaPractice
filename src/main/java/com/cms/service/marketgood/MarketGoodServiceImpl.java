package com.cms.service.marketgood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.marketgood.MarketGoodBean;
import com.cms.form.marketgood.MarketGoodForm;
import com.cms.mapper.common.CommonMapper;
import com.cms.mapper.marketgood.MarketGoodMapper;

/**
 * ユーザー情報 Service
 */
@Service
public class MarketGoodServiceImpl implements MarketGoodService {

	/****** Mapper ******/
	@Autowired
	MarketGoodMapper mapper;

	@Autowired
	CommonMapper commonMapper;

	@Override
	public MarketGoodForm insert(MarketGoodForm form) {

		MarketGoodBean bean = new MarketGoodBean();

		bean.setGoodId(form.getGoodId()); // 商品ID
		bean.setGoodName(form.getGoodName()); // 商品名
		bean.setType(form.getType()); // 商品種別
		bean.setMaker(form.getMaker()); // メーカー
		bean.setPurchasePrice(form.getPurchasePrice()); // 仕入単価
		bean.setSalesPrice(form.getSalesPrice()); // 販売単価
		mapper.insert(bean);

		return form;
	}
}