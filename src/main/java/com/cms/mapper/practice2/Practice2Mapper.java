package com.cms.mapper.practice2;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.cms.entity.cmsworkhour.CmsWorkHourBean;

/**
 * 練習 Mapper
 */
@Mapper
public interface Practice2Mapper {
	
	/**
	 * 勤怠情報登録
	 * 
	 * @param inputList 登録レコード
	 * @return 登録件数
	 */
	int insertBulk(@Param("inputList")List<CmsWorkHourBean> inputList);
}