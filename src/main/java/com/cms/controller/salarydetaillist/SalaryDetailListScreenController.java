package com.cms.controller.salarydetaillist;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cms.controller.base.ControllerBase;
import com.cms.entity.salarydetaillist.CmsSalaryDetailBean;
import com.cms.form.salarydetaillist.SalaryDetailListScreenForm;
import com.cms.service.salarydetaillist.SalaryDetailListScreenService;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/salarydetaillist/SalaryDetailListScreen")
public class SalaryDetailListScreenController extends ControllerBase {

	@Autowired
	SalaryDetailListScreenService service;

	/**
	 * 練習画面の初期化処理
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model) {

		model.addAttribute("form", new SalaryDetailListScreenForm());

		return "/salarydetaillist/SalaryDetailListScreen";
	}

	/**
	 * データ検索
	 */
	@RequestMapping(params = "readDataBase", method = RequestMethod.POST)
	public String readDataBase(@ModelAttribute("form") SalaryDetailListScreenForm form,BindingResult result, Model model) {

		String retMessage = "";
		try {

			// CSVファイルを取込んで登録する
			List<CmsSalaryDetailBean> ret = service.readDataFromMysql(form);
			form.setResults(ret);

		} catch (Exception e) {
			//エラーメッセージを設定する
			retMessage = e.getMessage();
		}

		if (StringUtils.isNotEmpty(retMessage)) {
			form.setErrorMessage(retMessage);
		}

		model.addAttribute("form", form);
	    
		return "/salarydetaillist/SalaryDetailListScreen";
	}
}