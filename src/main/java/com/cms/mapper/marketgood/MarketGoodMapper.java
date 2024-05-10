package com.cms.mapper.marketgood;

import org.apache.ibatis.annotations.Mapper;

import com.cms.entity.marketgood.MarketGoodBean;

/**
 * 商品情報 Mapper
 */
@Mapper
public interface MarketGoodMapper {
	
	/**
	 * 商品情報登録
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 登録件数
	 */
	int insert(MarketGoodBean bean);

}