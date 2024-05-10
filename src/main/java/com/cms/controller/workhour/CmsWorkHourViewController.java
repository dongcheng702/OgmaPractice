package com.cms.controller.workhour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cms.common.UrlConst;
import com.cms.controller.base.ControllerBase;
import com.cms.form.cmsworkhour.CmsWorkHourForm;
import com.cms.mapper.common.CommonMapper;
import com.cms.service.login.LoginService;
import com.cms.service.workhour.CmsWorkHourService;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/workhour/cmsworkhourview")
public class CmsWorkHourViewController extends ControllerBase {

	@Autowired
	CmsWorkHourService service;

	@Autowired
	LoginService userService;

	@Autowired
	CommonMapper commonMapper;

	/**
	 * ユーザ画面初期化
	 */
	@RequestMapping(method = RequestMethod.GET)
	private String init(Model model, @ModelAttribute("selectedEmployeeId") String selectedEmployeeId) {

		CmsWorkHourForm form = new CmsWorkHourForm();
		form.setEmployeeId(selectedEmployeeId);

		CmsWorkHourForm initForm = service.readInit(form);
		model.addAttribute("form", initForm);

		return UrlConst.GOTO_USER_VIEW;
	}
	
	/**
	 * 「戻る」ボタンを押下する
	 */
	@RequestMapping(params = "gotoEmployeeList", method = RequestMethod.POST)
	public String gotoEmployeeList(@ModelAttribute("form") CmsWorkHourForm form, BindingResult result,
			Model model) {

		return UrlConst.GOTO_USER_LIST_REDIRECT;
	}
}