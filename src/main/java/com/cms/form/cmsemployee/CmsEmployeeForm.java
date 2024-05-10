package com.cms.form.cmsemployee;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.cms.entity.employee.CmsEmployeeBean;
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
public class CmsEmployeeForm extends BaseForm {

	/* 検索結果 */
	private List<CmsEmployeeBean> results1;

	private String employeeId;

	@NotEmpty
	@Length(max=8)
	private String name;
	private String type;

	//職種選択リスト
	@NotEmpty
	private String selectedJobType;
	private Map<String, String> jobTypeList;

	//性別選択リスト
	@NotEmpty
	private String selectedSexy;
	private Map<String, String> sexyList;
	
	@NotEmpty
	private String birthday;

	@NotEmpty
	@Length(max=50)
	private String address;
	
	//電話番号チェック
	@Pattern(regexp = "0\\d{1,3}\\d{1,4}\\d{4}", message = "電話番号の形式で入力してください(99999999999)")
	private String phone;

	@NotEmpty
	private String joiningDate;

	@Email
	@Length(max=100)
	private String mail;
	private String jobType;
	private String jobLevel;

    @Max(value = 220,message = "勤務時間上限は220時間となります。")
	private String topWorkHour;

    @Min(value = 140,message = "勤務時間下限は140時間となります。")
	private String downWorkHour;
	
	@NotEmpty
	@Length(max=8)
	private String salary;

	private Date registrationDate;
	private Date updateDate;

	//社員区分
	private Map<String, String> employeeKbnMap;
	private String selectedEmployeeKbn;
	
	//社員種別
	@NotEmpty
	private String selectedEmployeeType;
	private Map<String, String> employeeTypeList;
	
	//税金有無
	@NotEmpty
	private String selectedHasTax;
	private Map<String, String> hasTaxList;

}