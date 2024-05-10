package com.cms.mapper.workhour;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.cms.entity.cmsworkhour.CmsWorkHourBean;

/**
 * ユーザー情報 Mapper
 */
@Mapper
public interface CmsWorkHourMapper {
	/**
	 * ユーザー情報検索
	 * 
	 * @param user 検索用リクエストデータ
	 * @return ユーザー情報
	 */
	List<CmsWorkHourBean> select(CmsWorkHourBean bean);

	/**
	 * ユーザー情報登録
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 更新件数
	 */
	int insert(CmsWorkHourBean bean);

	/**
	 * ユーザー情報更新
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 更新件数
	 */
	int update(CmsWorkHourBean bean);

	/**
	 * ユーザー情報削除
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 更新件数
	 */
	int delete(CmsWorkHourBean bean);
	/**
	 * ユーザー情報検索
	 * 
	 * @param user 検索用リクエストデータ
	 * @return ユーザー情報
	 */
	List<CmsWorkHourBean> selectList(CmsWorkHourBean bean);
	
	

	/**
	 * ユーザー情報登録
	 * 
	 * @param inputList 登録レコード
	 * @return 登録件数
	 */
	int insertBulk(@Param("inputList")List<CmsWorkHourBean> inputList);
}