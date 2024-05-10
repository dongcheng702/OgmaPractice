package com.cms.controller.cost;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.cms.service.cost.CmsCostService;
import com.utils.ServiceUtils;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/cost/cmscostadd")
public class CmsCostAddController extends ControllerBase {

	@Autowired
	CmsCostService service;
	
	@Autowired
	ServiceUtils serviceUtils;
	
	/**
	 * 社員登録画面を初期化する
	 */
	@RequestMapping(method = RequestMethod.GET)
	private String init(Model model,HttpServletRequest request) {

		try {
			CmsCostForm initForm = service.insertInit(new CmsCostForm());
			
			//ログイン社員IDを社員IDに設定する
			HttpSession session = request.getSession();
			//社員ID
			initForm.setEmployeeId((String) session.getAttribute("employeeId"));
			//社員名
			initForm.setEmployeeName((String) session.getAttribute("employeeName"));
			//新規・編集フラグの設定（新規画面の場合、True）
			initForm.setNewFlg(true);
			
			model.addAttribute("form", initForm);
		
		} catch (SystemException se) {
			System.out.println(se.getMessage());
		}
		return UrlConst.cmscostadd;
	}

	/**
	 * 保存ボタンを押下する
	 */
	@RequestMapping(params = "insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute("form") @Valid CmsCostForm form, BindingResult result, Model model) {

		try {
			service.insertInit(form);
			
			if (result.hasErrors()) {
	
				model.addAttribute("form", form);
				return null;
			}
	
			service.insert(form);
		} catch (SystemException se) {
			System.out.println(se.getMessage());
		}
		
		return UrlConst.redirect_cmscostlist;
	}
	
	/**
	 * 「戻る」ボタンを押下する
	 */
	@RequestMapping(params = "gotoList", method = RequestMethod.POST)
	public String gotoList(@ModelAttribute("form") CmsCostForm form, BindingResult result, Model model) {

		return UrlConst.redirect_cmscostlist;
	}
	
//	private 
}