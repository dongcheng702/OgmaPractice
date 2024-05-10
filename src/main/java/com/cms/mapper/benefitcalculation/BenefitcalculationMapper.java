package com.cms.mapper.benefitcalculation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cms.entity.benefitcalculation.BenefitcalculationBean;

@Mapper
public interface BenefitcalculationMapper {

	//搜索所有信息
	List<BenefitcalculationBean> select(BenefitcalculationBean bean);
	//搜索月次利益信息
	List<BenefitcalculationBean> selectBenefit(BenefitcalculationBean bean);
	
	//搜索店铺
	int selectID(BenefitcalculationBean bean);
	
	//插入到月次利益表
	int insertBenfit(BenefitcalculationBean bean);
	
	//更新月次利益数据
	void updateBenefit(BenefitcalculationBean bean);
	
	//查询月次利益表数据个数
	int countBenefit();
}

