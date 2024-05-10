package com.cms.form.benefitcalculation;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.cms.entity.benefitcalculation.BenefitcalculationBean;
import com.cms.form.BaseForm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BenefitcalculationForm extends BaseForm{

	/* 検索結果 */
	private List<BenefitcalculationBean> results;
	
	//店ID
    @Length(max=8)
	private String storeId;

	//売上年月
	private String salesDay;
}
