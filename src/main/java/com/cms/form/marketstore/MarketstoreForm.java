package com.cms.form.marketstore;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.cms.entity.marketstore.MarketstoreBean;
import com.cms.form.BaseForm;

import lombok.Getter;
import lombok.Setter;

/**
 * ユーザー情報 検索用リクエストデータ
 */
@Getter
@Setter
public class MarketstoreForm extends BaseForm {

	/* 検索結果 */
	private List<MarketstoreBean> results;
	
	private String storeId;

	//店名
	@NotEmpty
	@Length(max=50)
	private String storeName;

	//住所
	@NotEmpty
	@Length(max=50)
	private String address;
	
	//电话
	@Pattern(regexp = "0\\d{1,3}\\d{1,4}\\d{4}", message = "電話番号の形式で入力してください(99999999999)")
	private String phone;

	//開始日
	@NotEmpty
	private Date startDate;
	
	//完成日
	@NotEmpty
	private Date finishDate;
}