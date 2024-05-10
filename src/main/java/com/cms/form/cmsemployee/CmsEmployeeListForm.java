package com.cms.form.cmsemployee;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.form.BaseForm;

import lombok.Getter;
import lombok.Setter;

/**
 * ユーザー情報 検索用リクエストデータ
 */
@Getter
@Setter
public class CmsEmployeeListForm extends BaseForm {

	/* 検索結果 */
	private List<CmsEmployeeBean> results;

	private String employeeId;

	@NotEmpty
	private String name;
	
	private String type;

	
	//生年月
	private String month;

	//職種選択リスト
	@NotEmpty
	private String selectedJobType;
	private Map<String, String> jobTypeList;

	//性別選択リスト
	@NotEmpty
	private String selectedSexy;
	private Map<String, String> sexyList;

	//社員区分
	private Map<String, String> employeeKbnMap;
	private String selectedEmployeeKbn;
	
	//全選択の削除対象
	private String selectedItemIds;
	
	//選択対象(ID+最終更新日)
	private String selectedItemId;
	
	//ボタンフラグ（削除、編集ボタン：True；参照：False）
	private boolean btnFlg;

}