package com.cms.mapper.cost;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cms.entity.cost.CmsCostBean;

/**
 * ユーザー情報 Mapper
 */
@Mapper
public interface CmsCostMapper {
	/**
	 * 最大ID取得
	 * 
	 * @return 最大ID
	 */
	String selectMaxId();
	
	/**
	 * ユーザー情報検索
	 * 
	 * @param user 検索用リクエストデータ
	 * @return ユーザー情報
	 */
	List<CmsCostBean> select(CmsCostBean bean);

	/**
	 * ユーザー情報登録
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 更新件数
	 */
	int insert(CmsCostBean bean);

	/**
	 * ユーザー情報更新
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 更新件数
	 */
	int update(CmsCostBean bean);

	/**
	 * ユーザー情報削除
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 更新件数
	 */
	int delete(CmsCostBean bean);
	
	/**
	 * 複数件の情報削除
	 * 
	 * @param ids 削除ID配列
	 * @return 削除件数
	 */
	int deleteAll(String[] ids);
}