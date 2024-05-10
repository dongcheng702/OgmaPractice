package com.cms.controller.cost;

import javax.transaction.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cms.common.UrlConst;
import com.cms.controller.base.ControllerBase;
import com.cms.form.cost.CmsCostForm;
import com.cms.mapper.common.CommonMapper;
import com.cms.service.cost.CmsCostService;
import com.cms.service.login.LoginService;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/cost/cmscostview")
public class CmsCostViewController extends ControllerBase {

	@Autowired
	CmsCostService service;

	@Autowired
	LoginService userService;

	@Autowired
	CommonMapper commonMapper;

	/**
	 * ユーザ画面初期化
	 */
	@RequestMapping(method = RequestMethod.GET)
	private String init(Model model, @ModelAttribute("selectedId") String selectedId) {

		try {
			CmsCostForm form = new CmsCostForm();
			form.setCostId(selectedId);
	
			CmsCostForm initForm = service.readInit(form);
			model.addAttribute("form", initForm);
		} catch (SystemException se) {
			System.out.println(se.getMessage());
		}
		
		return UrlConst.cmscostview;
	}
	
	/**
	 * 「戻る」ボタンを押下する
	 */
	@RequestMapping(params = "gotoList", method = RequestMethod.POST)
	public String gotoList(@ModelAttribute("form") CmsCostForm form, BindingResult result,
			Model model) {

		return UrlConst.redirect_cmscostlist;
	}
}