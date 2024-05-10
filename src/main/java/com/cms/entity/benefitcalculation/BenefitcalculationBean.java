package com.cms.entity.benefitcalculation;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "market_store")
public class BenefitcalculationBean {
	
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
	private Date startDay;

	@Column(name = "finishDay")
	private Date finishDay;

	@Column(name = "registDay")
	private Timestamp registDay;
	
	@Column(name = "updateDay")
	private Timestamp updateDay;
	
	//market_sales and market_goods
    @Column(name = "salesId")
    private String salesId;
    
    @Column(name = "goodId")
    private String goodId;
    
    @Column(name = "salesCount")
    private int salesCount;

    @Column(name = "salesDay")
    private String salesDay;

    @Column(name = "salesPrice")
    private int salesPrice;
    
    @Column(name = "purchasePrice")
    private int purchasePrice;
    
    @Column(name = "benefitId")
    private int benefitId;
    
    @Column(name = "salesMonth")
    private String salesMonth;
  
    //利益
    @Column(name = "benefit")
    private int benefit;
    
    //購入金額合計
    @Column(name = "salesAmount")
    private int salesAmount;
    
    //仕入金額合計
    @Column(name = "purchaseAmount")
    private int purchaseAmount;
         
}
