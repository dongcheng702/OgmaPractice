package com.cms.controller.workhour;

import javax.transaction.SystemException;
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
import com.cms.form.cmsworkhour.CmsWorkHourForm;
import com.cms.service.workhour.CmsWorkHourService;
import com.utils.CmsUtils;
import com.utils.ServiceUtils;
/*
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/workhour/cmsworkhouradd")
public class CmsWorkHourAddController extends ControllerBase {

	@Autowired
	CmsWorkHourService service;
	
	@Autowired
	ServiceUtils serviceUtils;
	/**
	 * 社員登録画面を初期化する
	 */
	@RequestMapping(method = RequestMethod.GET)
	private String init(Model model) {

		CmsWorkHourForm initForm = service.insertInit(new CmsWorkHourForm());
		model.addAttribute("employeeForm", initForm);

		return UrlConst.GOTO_USER_ADD;
	}

	/**
	 * 保存ボタンを押下する
	 */
	@RequestMapping(params = "insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute("form") @Valid CmsWorkHourForm form, BindingResult result, Model model) {
		try {
			
		
		// 選択リスト{SEXY:性別}
		form.setSexyList(serviceUtils.getGeneralMastInfo("SEXY"));

		// 選択リスト{JOB_TYPE:職種}
		form.setJobTypeList(serviceUtils.getGeneralMastInfo("JOB_TYPE"));
		
		//社員区分（ラジオボタン）
		form.setGenders(CmsUtils.createGenders());
		
		if (result.hasErrors()) {

			model.addAttribute("form", form);
			return null;
		}

		service.insert(form);
		} catch (SystemException se) {
			System.out.println(se.getMessage());
		}
		
		return UrlConst.GOTO_USER_LIST_REDIRECT;
	}
	
	/**
	 * 「戻る」ボタンを押下する
	 */
	@RequestMapping(params = "gotoEmployeeList", method = RequestMethod.POST)
	public String gotoEmployeeList(@ModelAttribute("form") CmsWorkHourForm form, BindingResult result, Model model) {

		return UrlConst.GOTO_USER_LIST_REDIRECT;
	}
	
}