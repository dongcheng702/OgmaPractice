package com.cms.controller.cost;

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
import com.cms.form.cost.CmsCostForm;
import com.cms.mapper.common.CommonMapper;
import com.cms.service.cost.CmsCostService;
import com.utils.ServiceUtils;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/cost/cmscostedit")
public class CmsCostEditController extends ControllerBase {

	@Autowired
	CmsCostService service;

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	ServiceUtils serviceUtils;
	/**
	 * ユーザ画面初期化
	 */
	@RequestMapping(method = RequestMethod.GET)
	private String init(Model model, @ModelAttribute("selectedId") String selectedId) {

		try {
			
			CmsCostForm form = new CmsCostForm();
			form.setCostId(selectedId);
	
			CmsCostForm initForm = service.editInit(form);
			model.addAttribute("form", initForm);
	
			//新規・編集フラグの設定（新規画面の場合、True）
			initForm.setNewFlg(false);
		
		} catch (SystemException se) {
			System.out.println(se.getMessage());
		}
		
		return UrlConst.cmscostedit;
	}

	/**
	 * 保存ボタンを押下する
	 */
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("form") @Valid CmsCostForm form, BindingResult result, Model model) {

		if (result.hasErrors()) {

			model.addAttribute("form", form);
			return null;
		}

		service.update(form);

		return UrlConst.redirect_cmscostlist;
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