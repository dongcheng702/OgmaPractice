package com.cms.entity.csvfetch;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "market_sales")
public class CsvFetchBean {

	@Id
	@Column(name = "salesId")
	private String salesId;
	
	@Column(name = "salesDay")
	private String salesDay;

	@Column(name = "storeId")
	private String storeId;
	
	@Column(name = "goodId")
	private String goodId;

	@Column(name = "salesCount")
	private int salesCount;
	
	@Column(name = "registDay")
	private Timestamp registDay;
	
	@Column(name = "updateDay")
	private Timestamp updateDay;
}
