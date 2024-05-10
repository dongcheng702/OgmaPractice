package com.cms.entity.marketstore;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "market_store")
public class MarketstoreBean {
	
	@Id
	@Column(name = "storeId")
	private String storeId;
	
	@Column(name = "storeName")
	private String storeName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "startDay")
	private String startDay;
	
	@Column(name = "finishDay")
	private String finishDay;
	
	@Column(name = "registDay")
	private String registDay;
	
	@Column(name = "updateDay")
	private String updateDay;
}
