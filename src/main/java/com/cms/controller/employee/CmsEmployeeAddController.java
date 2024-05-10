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
import com.cms.service.employee.CmsEmployeeService;
import com.utils.ServiceUtils;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/employee/cmsemployeeadd")
public class CmsEmployeeAddController extends ControllerBase {

	@Autowired
	CmsEmployeeService service;
	
	@Autowired
	ServiceUtils serviceUtils;
	/**
	 * 社員登録画面を初期化する
	 */
	@RequestMapping(method = RequestMethod.GET)
	private String init(Model model) {
		
		CmsEmployeeForm initForm = new CmsEmployeeForm();
		super.setCommonItem(initForm, true);
		
		model.addAttribute("form", initForm);

		return UrlConst.cmsemployeeadd;
	}

	/**
	 * 保存ボタンを押下する
	 */
	@RequestMapping(params = "insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute("form") @Valid CmsEmployeeForm form, BindingResult result, Model model) {
		
        super.setCommonItem(form, false);	
		if (result.hasErrors()) {
			model.addAttribute("form", form);
			return null;
		}

		try {
			service.insert(form);
		} catch (Exception e) {

			model.addAttribute("errorMessage", "システムエラーが発生しました。管理員へご連絡ください。");
//			model.addAttribute("form", form);
			return null;
		}

		return UrlConst.redirect_cmsemployeelist;
	}
	
	/**
	 * 「戻る」ボタンを押下する
	 */
	@RequestMapping(params = "gotoEmployeeList", method = RequestMethod.POST)
	public String gotoEmployeeList(@ModelAttribute("form") CmsEmployeeForm form, BindingResult result, Model model) {

		return UrlConst.redirect_cmsemployeelist;
	}
	
}