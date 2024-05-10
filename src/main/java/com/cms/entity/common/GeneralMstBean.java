package com.cms.entity.common;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * ユーザー情報 Entity
 */
@Getter
@Setter
@Entity
@Table(name = "cms_general_mst")
public class GeneralMstBean {
	@Id
	@Column(name = "generalKey")
	private String generalKey;
	@Column(name = "generalId")
	private String generalId;
	@Column(name = "generalName")
	private String generalName;
	@Column(name = "generalExplanation")
	private String generalExplanation;
	@Column(name = "registrationDate")
	private Date registrationDate;
	@Column(name = "updateDate")
	private Date updateDate;
}
