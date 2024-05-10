package com.cms.mapper.csvfetch;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.cms.entity.csvfetch.CsvFetchBean;

@Mapper
public interface CsvFetchMapper {
	
	int insertBulk(@Param("inputList")List<CsvFetchBean> inputList);
}
