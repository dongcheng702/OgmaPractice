package com.cms.controller.workhour;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.SystemException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cms.common.Gender;
import com.cms.common.UrlConst;
import com.cms.controller.base.ControllerBase;
import com.cms.form.cmsworkhour.CmsWorkHourForm;
import com.cms.mapper.common.CommonMapper;
import com.cms.service.workhour.CmsWorkHourService;
import com.utils.ServiceUtils;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/workhour/cmsworkhouredit")
public class CmsWorkHourEditController extends ControllerBase {

	@Autowired
	CmsWorkHourService service;

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	ServiceUtils serviceUtils;
	/**
	 * ユーザ画面初期化
	 */
	@RequestMapping(method = RequestMethod.GET)
	private String init(Model model, @ModelAttribute("selectedEmployeeId") String selectedEmployeeId) {

		CmsWorkHourForm form = new CmsWorkHourForm();
		form.setEmployeeId(selectedEmployeeId);

		CmsWorkHourForm initForm = service.editInit(form);
		model.addAttribute("form", initForm);

		return UrlConst.GOTO_USER_EIDT;
	}

	/**
	 * 保存ボタンを押下する
	 */
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("form") @Valid CmsWorkHourForm form, BindingResult result, Model model) {
		
		try {
			// 選択リスト{SEXY:性別}
			form.setSexyList(serviceUtils.getGeneralMastInfo("SEXY"));
	
			// 選択リスト{JOB_TYPE:職種}
			form.setJobTypeList(serviceUtils.getGeneralMastInfo("JOB_TYPE"));
	
			// 社員区分（ラジオボタン）
			List<Gender> genders = new ArrayList<Gender>() {
				{
					add(Gender.of("radioKbn", "社員", "社員"));
					add(Gender.of("radioKbn", "BP", "BP"));
				}
			};
			form.setGenders(genders);
	
			if (result.hasErrors()) {
	
				model.addAttribute("form", form);
				return null;
			}
	
			service.update(form);
		
		} catch (SystemException se) {
			System.out.println(se.getMessage());
		}
		
		return UrlConst.GOTO_USER_LIST_REDIRECT;
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