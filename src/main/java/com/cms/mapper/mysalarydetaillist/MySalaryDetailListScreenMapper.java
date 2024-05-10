package com.cms.mapper.mysalarydetaillist;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cms.entity.mysalarydetaillist.MyCmsSalaryDetailBean;

@Mapper
public interface MySalaryDetailListScreenMapper {
	
	
	List<MyCmsSalaryDetailBean> select(MyCmsSalaryDetailBean bean);
}
