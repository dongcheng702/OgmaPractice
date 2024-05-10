package com.cms.entity.invoice;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 請求書Bean
 */

@Data
@Table(name = "cms_invoice")
public class CmsInvoiceBean{
	
	@Id
	@Column(name = "employeeId")
	private String employeeId;

	@Id
	@Column(name = "workMonth")
	private String workMonth;
	
	@Column(name = "companyName")
	private String companyName;
	
	@Column(name = "requestFirst")
	private String requestFirst;
	
	@Column(name = "standardBusinessHours")
	private Integer standardBusinessHours;
	
	@Column(name = "realWorkHous")
	private Integer realWorkHous;
	
	@Column(name = "salary")
	private BigDecimal salary;
	
	@Column(name = "disabledGeneration")
	private BigDecimal disabledGeneration;
	
	@Column(name = "registDay")
	private Date registDay;
	
	@Column(name = "updateDay")
	private Date updateDay;

	private String employeeName;
}
