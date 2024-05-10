package com.cms.controller.employee;

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
import com.cms.mapper.common.CommonMapper;
import com.cms.service.employee.CmsEmployeeService;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/employee/cmsemployeeedit")
public class CmsEmployeeEditController extends ControllerBase {

	@Autowired
	CmsEmployeeService service;

	@Autowired
	CommonMapper commonMapper;

	/**
	 * ユーザ画面初期化
	 */
	@RequestMapping(method = RequestMethod.GET)
	private String init(Model model, @ModelAttribute("selectedEmployeeId") String selectedEmployeeId) {

		CmsEmployeeForm form = new CmsEmployeeForm();
		form.setEmployeeId(selectedEmployeeId);

		//共通項目設定
		super.setCommonItem(form, true);
		
		CmsEmployeeForm initForm = service.editInit(form);
		
		model.addAttribute("form", initForm);
		
		return UrlConst.cmsemployeeedit;
	}

	/**
	 * 保存ボタンを押下する
	 */
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("form") @Valid CmsEmployeeForm form, BindingResult result, Model model) {

		//共通項目設定
		super.setCommonItem(form, false);
		
		if (result.hasErrors()) {
			model.addAttribute("form", form);
			return null;
		}

		service.update(form);

		return UrlConst.redirect_cmsemployeelist;
	}

	/**
	 * 「戻る」ボタンを押下する
	 */
	@RequestMapping(params = "gotoEmployeeList", method = RequestMethod.POST)
	public String gotoEmployeeList(@ModelAttribute("form") CmsEmployeeForm form, BindingResult result,
			Model model) {

		return UrlConst.redirect_cmsemployeelist;
	}

}