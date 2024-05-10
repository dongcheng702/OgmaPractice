package com.cms.mapper.salary;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.cms.entity.salary.CmsSalaryDetailBean;
import com.cms.entity.salary.CmsSalaryDetailRegistBean;


/**
 * ユーザー情報 Mapper
 */
@Mapper
public interface CmsSalaryMapper {
	/**
	 * 給料明細情報検索
	 * 
	 * @param user 検索用リクエストデータ
	 * @return ユーザー情報
	 */
	List<CmsSalaryDetailBean> select(CmsSalaryDetailBean bean);

	/**
	 * 給料明細情報登録
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 更新件数
	 */
	int insert(CmsSalaryDetailBean bean);

	/**
	 * 給料明細情報削除
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 更新件数
	 */
	int delete(CmsSalaryDetailBean bean);

	int updateRecords(CmsSalaryDetailBean[] beans);
	/**
	 * 給料明細情報削除
	 * 
	 * @param user 検索用リクエストデータ
	 * @return 削除件数
	 */
	int deleteRecords(String[] ids);

	/**
	 * 給料明細情報登録（複数件）
	 * 
	 * @param inputList 登録レコード
	 * @return 登録件数
	 */
	int insertBulk(@Param("inputList")List<CmsSalaryDetailBean> inputList);
	

	/**
	 * 給料未作成の社員情報の検索
	 * 
	 * @param bean 引数
	 * @return ユーザー情報リスト
	 */
	List<CmsSalaryDetailRegistBean> selectEmployeeForSalaryIsNotCreated(CmsSalaryDetailBean bean);
}