package com.cms.form.cost;

import java.util.Date;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.cms.form.BaseForm;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 費用 検索用リクエストデータ
 */
@Getter
@Setter
@Data
public class CmsCostForm  extends BaseForm {
	
    //費用ID
	private String costId;
	
    //費用発生日
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	private Date costDay;
	
    //費用コメント
	private String costNote;
		
    //費用金額
	@Min(1)
	private int costAmount;
	
    //税金備考
	private String taxNote;
	
	//社員ID
	private String employeeId;
	//社員名
	private String employeeName;

	//新規フラグ（true:新規;false:編集）
	private boolean newFlg;
	
    //費用種別
	private String selectedCostType;
	private Map<String, String> costTypeList;
	
	//税金有無
	@NotEmpty
	private String hasTax;
	private Map<String, String> hasTaxList;
}