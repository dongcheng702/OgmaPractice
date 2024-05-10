package com.cms.entity.cost;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "cms_cost")
public class CmsCostBean {

	@Id
	@Column(name = "costId")
	private String costId;

	@Column(name = "costDay")
	private String costDay;

	@Column(name = "costNote")
	private String costNote;

	@Column(name = "costType")
	private String costType;

	@Column(name = "costAmount")
	private int costAmount;

	@Column(name = "hasTax")
	private String hasTax;

	@Column(name = "taxNote")
	private String taxNote;

	@Column(name = "employeeId")
	private String employeeId;

	@Column(name = "registDay")
	private Date registDay;

	@Column(name = "updateDay")
	private Date updateDay;

	/** 関連テーブル情報 */
	//社員名
	@Column(name = "employeeNm")
	private String employeeNm;

	//費用種別名
	@Column(name = "costTypeName")
	private String costTypeName;

	//税金有無
	@Column(name = "hasTaxName")
	private String hasTaxName;
	
	//費用発生年月（一覧画面検索項目）
	private String costMonth;
	
}
