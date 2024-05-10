package com.cms.entity.mysalarydetaillist;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "cms_salary_detail")
public class MyCmsSalaryDetailBean {

	@Id
	@Column(name = "employeeId")
	private String employeeId;
	
	@Id
	@Column(name = "salaryMonth")
	private String salaryMonth;
	
	@Column(name = "salary")
	private String salary;
	
	@Column(name = "workhours")
	private BigDecimal workhours;
	
	@Column(name = "disabledGeneration")
	private BigDecimal disabledGeneration;
	
	@Column(name = "cost")
	private BigDecimal cost;
		
	@Column(name = "actualSalary")
	private BigDecimal actualSalary;

	@Column(name = "employeeNm")
	private String employeeNm;
}
