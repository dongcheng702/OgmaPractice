package com.cms.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cms.entity.common.GeneralMstBean;

/**
 * 汎用マスタ Mapper
 */
@Mapper
public interface GeneralMstMapper {
	/**
	 * 汎用マスタを取得する
	 * 
	 * @param bean 検索用リクエストデータ
	 * @return 汎用マスタ情報
	 */
	List<GeneralMstBean> select(GeneralMstBean bean);

}