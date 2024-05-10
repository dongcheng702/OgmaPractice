package com.cms.entity.common;

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
@Table(name = "m_codemst")
public class MCodeMstBean {

	@Id
	@Column(name = "codeKey")
	private String codeKey;
	@Column(name = "codeName")
	private String codeName;
	@Column(name = "code")
	private String code;
	@Column(name = "value")
	private String value;

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
