package com.cms.form.salary;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.cms.common.Gender;
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
public class CmsSalaryForm extends BaseForm {

	private String employeeId;
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
	private String address;
	
	//電話番号チェック
	@Pattern(regexp = "^0\\d{10,10}$", message="{error.pattern.unmatch}")
	private String phone;

	@NotEmpty
	private String joiningDate;

	@Email
	private String mail;
	private String jobType;
	private String jobLevel;
	private String loginId;
	private String password;
	private Date registrationDate;
	private Date updateDate;
	private String companyId;

	//社員区分
	private List<Gender> genders;
	private String selectGender;

}