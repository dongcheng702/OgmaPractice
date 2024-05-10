package com.cms.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cms.entity.common.MCodeMstBean;

/**
 * ユーザー情報 Mapper
 */
@Mapper
public interface CommonMapper {
	/**
	 * コードマスタを取得する
	 * 
	 * @param bean 検索用リクエストデータ
	 * @return コードマスタ情報
	 */
	List<MCodeMstBean> select(MCodeMstBean bean);

}