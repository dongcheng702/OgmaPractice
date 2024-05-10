package com.cms.mapper.employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cms.entity.employee.CmsEmployeeBean;

/**
 * ユーザー情報 Mapper
 */
@Mapper
public interface CmsEmployeeMapper {
	/**
	 * 社員件数の取得
	 * 
	 * @param bean 社員Bean
	 * @return レコード件数
	 */
	Integer selectRecordCount(CmsEmployeeBean bean);
	
	/**
	 * 最大社員IDの取得
	 * 
	 * @return 最大の社員ID
	 */
	String selectMaxId();
	
	/**
	 * ユーザー情報検索
	 * 
	 * @param user 検索用リクエストデータ
	 * @return ユーザー情報
	 */
	List<CmsEmployeeBean> select(CmsEmployeeBean bean);

	/**
	 * ユーザー情報登録
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 更新件数
	 */
	int insert(CmsEmployeeBean bean);

	/**
	 * ユーザー情報更新
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 更新件数
	 */
	int update(CmsEmployeeBean bean);

	/**
	 * ユーザー情報削除
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 更新件数
	 */
	int delete(CmsEmployeeBean bean);
	/**
	 * 情報削除
	 * 
	 * @param ids 削除ID配列
	 * @return 削除件数
	 */
	int deleteAll(String[] ids);

}