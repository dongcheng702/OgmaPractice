package com.cms.common;

import lombok.Getter;

@Getter
public class Gender {
	private String id;
	private String value;
	private String label;

	public Gender(String id, String value, String label) {
		this.id = id;
		this.value = value;
		this.label = label;
	}

	public static Gender of(String id, String value, String label) {
		return new Gender(id, value, label);
	}
}