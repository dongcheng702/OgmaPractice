package com.cms.controller.pdf;

import java.util.List;

import com.cms.entity.employee.CmsEmployeeBean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * ログインフォーム
 */
@Getter
@Setter
@Data
public class PdfOutputForm {

	/* 検索結果 */
	private List<CmsEmployeeBean> results;
	
	private String paramItem;

}