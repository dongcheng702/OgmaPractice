package com.cms.entity.marketgood;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "market_goods")
public class MarketGoodBean {
	@Id
	@Column(name = "goodId")
	private String goodId;
	
	@Column(name = "goodName")
	private String goodName;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "maker")
	private String maker;
	
	@Column(name = "purchasePrice")
	private BigDecimal purchasePrice;
	
	@Column(name = "salesPrice")
	private BigDecimal salesPrice;

	@Column(name = "registDay")
	private Date registDay;
	
	@Column(name = "updateDay")
	private Date updateDay;
}
