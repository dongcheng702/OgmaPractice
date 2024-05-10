package com.cms.form.marketgood;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.cms.form.BaseForm;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 商品登録フォーム
 */
@Getter
@Setter
@Data
public class MarketGoodForm extends BaseForm {

	@NotEmpty
	private String goodId;// 商品ID
	@NotEmpty
	private String goodName;// 商品名
	@NotEmpty
	private String type;// 商品種別
	@NotEmpty
	private String maker;// メーカー
	@Min(1)
	private BigDecimal purchasePrice;// 仕入単価
	@Min(1)
	private BigDecimal salesPrice;// 販売単価

}