package com.cms.entity.workhour;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/**
 * 勤務情報Bean
 */

@Data
@Table(name = "cms_work_hour")
public class CmsWorkHourBean extends Object {

	@Id
	@Column(name = "employeeId")
	private String employeeId;

	@Id
	@Column(name = "workDay")
	private Timestamp workDay;
	
	@Column(name = "startTime")
	private Timestamp startTime;
	
	@Column(name = "endTime")
	private Timestamp endTime;
	
	@Column(name = "workHous")
	private int workHous;
	
	@Column(name = "registDay")
	private Timestamp registDay;
	
	@Column(name = "updateDay")
	private Timestamp updateDay;
}
