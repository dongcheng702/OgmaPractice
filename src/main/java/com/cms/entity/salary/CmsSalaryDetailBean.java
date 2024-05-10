package com.cms.entity.salary;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "cms_salary_detail")
public class CmsSalaryDetailBean {
	
	//社員ID
	@Id
	@Column(name = "employeeId")
	private String employeeId;
	
	//給料年月
	@Id
	@Column(name = "salaryMonth")
	private String salaryMonth;
	
	//給料
	@Column(name = "salary")
	private BigDecimal salary;

	//勤務時間
	@Column(name = "workhours")
	private BigDecimal workhours;
	
	//残業控除
	@Column(name = "disabledGeneration")
	private BigDecimal disabledGeneration;
	
	//費用
	@Column(name = "cost")
	private BigDecimal cost;
	
	//実給(税込)
	@Column(name = "actualSalary")
	private BigDecimal actualSalary;
	
	@Column(name = "registDay")
	private Date registDay;
	
	@Column(name = "updateDay")
	private Date updateDay;
	
	//結合項目
	@Column(name = "employeeNm")
	private String employeeNm;
	
}
