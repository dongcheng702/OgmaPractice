package com.cms.service.benefitcalculation;

import java.util.List;

import com.cms.entity.benefitcalculation.BenefitcalculationBean;

public interface BenefitcalculationService {
	
	public List<BenefitcalculationBean> getBenefitData(String id,String month) throws Exception;
}
