package com.cms.entity.employee;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "cms_employee")
public class CmsEmployeeBean {
	@Id
	@Column(name = "employeeId")
	private String employeeId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "sex")
	private String sex;
	private String sexForList;
	
	@Column(name = "birthday")
	private String birthday;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "joiningDate")
	private String joiningDate;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "jobType")
	private String jobType;
	
	@Column(name = "jobLevel")
	private String jobLevel;
	
	@Column(name = "employeeKbn")
	private String employeeKbn;
	private String employeeKbnForList;

	@Column(name = "salary")
	private BigDecimal salary;
	
	@Column(name = "employeeType")
	private String employeeType;
	
	@Column(name = "hasTax")
	private String hasTax;
	
	@Column(name = "topWorkHour")
	private int topWorkHour;
	
	@Column(name = "downWorkHour")
	private int downWorkHour;
	
	@Column(name = "loginId")
	private String loginId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "registDay")
	private String registDay;
	
	@Column(name = "updateDay")
	private String updateDay;
}
