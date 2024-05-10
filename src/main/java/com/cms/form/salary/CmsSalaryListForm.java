package com.cms.form.salary;

import java.util.List;

import com.cms.entity.salary.CmsSalaryDetailBean;
import com.cms.form.BaseForm;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * ユーザー情報 検索用リクエストデータ
 */
@Getter
@Setter
@Data
public class CmsSalaryListForm extends BaseForm{

	/* 検索条件＆登録情報 */
	private CmsSalaryDetailBean record;

	/* 検索結果 */
	private List<CmsSalaryDetailBean> results;

	//社員ID
	private String employeeId;
	//社員名
	private String name;
	//給料年月
	private String month;

	//選択済のキー
	private String itemIds;
}