package com.cms.entity.cmsworkhour;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "cms_work_hour")
public class CmsWorkHourBean {
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
	
	private String workMonth;
}
