package com.cms.controller.marketgood;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cms.common.UrlConst;
import com.cms.controller.base.ControllerBase;
import com.cms.form.cmsemployee.CmsEmployeeForm;
import com.cms.form.marketgood.MarketGoodForm;
import com.cms.service.marketgood.MarketGoodService;
import com.utils.ServiceUtils;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/marketgood/marketgoodadd")
public class MarketGoodAddController extends ControllerBase {

	@Autowired
	MarketGoodService service;
	
	@Autowired
	ServiceUtils serviceUtils;
	/**
	 * 商品登録画面を初期化する
	 */
	@RequestMapping(method = RequestMethod.GET)
	private String init(Model model) {
		
		MarketGoodForm initForm = new MarketGoodForm();
		
		model.addAttribute("form", initForm);

		return "/marketgood/marketgoodadd";
	}

	/**
	 * 保存ボタンを押下する
	 */
	@RequestMapping(params = "insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute("form") @Valid MarketGoodForm form, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("form", form);
			return null;
		}

		try {
			service.insert(form);
		} catch (Exception e) {

			model.addAttribute("errorMessage", "システムエラーが発生しました。管理員へご連絡ください。");
			return null;
		}

		model.addAttribute("errorMessage", "データが正常に登録されました。");
		return null;
	}
	
	/**
	 * 「戻る」ボタンを押下する
	 */
	@RequestMapping(params = "gotoEmployeeList", method = RequestMethod.POST)
	public String gotoEmployeeList(@ModelAttribute("form") CmsEmployeeForm form, BindingResult result, Model model) {

		return UrlConst.redirect_cmsemployeelist;
	}
	
}