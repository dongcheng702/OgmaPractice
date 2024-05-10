package com.cms.controller.employee;

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
@RequestMapping(value = "/employee/cmsemployeeview")
public class CmsEmployeeViewController extends ControllerBase {

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

		super.setCommonItem(form, true);
		
		CmsEmployeeForm initForm = service.readInit(form);
		
		model.addAttribute("form", initForm);

		return UrlConst.cmsemployeeview;
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