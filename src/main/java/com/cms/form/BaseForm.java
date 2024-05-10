package com.cms.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseForm {

	public String errorMessage;
	
	/** ----------Session---------- */
	// 社員ID(session引数保存)
	private String sessionEmployeeId;
	// 職種(session引数保存)
	private String sessionJobType;
	
	/** ----------制御---------- */
	// 社員種別：disabledFlg（画面制御用）
	private String disabledFlg;
}
