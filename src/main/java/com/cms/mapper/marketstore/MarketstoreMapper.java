package com.cms.mapper.marketstore;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cms.entity.marketstore.MarketstoreBean;

@Mapper
public interface MarketstoreMapper {

	/**
	 * 給料明細情報検索
	 * 
	 * @param 店铺检索用
	 * @return 店铺情报
	 */
	List<MarketstoreBean> select(MarketstoreBean bean);
}
