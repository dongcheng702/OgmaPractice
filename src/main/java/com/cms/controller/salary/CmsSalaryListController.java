package com.cms.controller.salary;

import java.util.List;

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
import com.cms.entity.salary.CmsSalaryDetailBean;
import com.cms.form.salary.CmsSalaryListForm;
import com.cms.mapper.common.CommonMapper;
import com.cms.service.salary.SalaryService;
import com.exception.BusinessException;
import com.utils.PdfUtil;
/* 
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/salary/cmssalarylist")
public class CmsSalaryListController extends ControllerBase {
	private List<CmsSalaryDetailBean> lst;

	@Autowired
	SalaryService service;

	@Autowired
	CommonMapper commonMapper;

	/**
	 * ユーザ画面初期化
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model) {

		model.addAttribute("form", new CmsSalaryListForm());

		return UrlConst.cmssalarylist;
	}

	/**
	 * 検索ボタン
	 */
	@RequestMapping(params = "select", method = RequestMethod.POST)
	public String select(@ModelAttribute("form") @Valid CmsSalaryListForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			//フォームチェックエラーがある場合
			model.addAttribute("form", form);
			return null;
		}		
		
		String errMessage = "";
		try {
			// 検索実施
			service.select(form);
		} catch (BusinessException be) {
			errMessage = be.getMessage();
		}
		//業務エラーを設定する
		form.setErrorMessage(errMessage);
		model.addAttribute("form", form);
		
		return UrlConst.cmssalarylist;
	}

	/**
	 * 給料作成ボタン
	 */
	@RequestMapping(params = "createSalary", method = RequestMethod.POST)
	public String createSalary(@ModelAttribute("form") @Valid CmsSalaryListForm form, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			//フォームチェックエラーがある場合
			model.addAttribute("form", form);
			return null;
		}
		
		String errMessage = "";
		try {
			//計算ロジックを呼び出す
			service.createSalary(form);
		} catch (BusinessException be) {
			errMessage = be.getMessage();
		}
		//業務エラーを設定する
		form.setErrorMessage(errMessage);
		model.addAttribute("form", form);
		
		return UrlConst.cmssalarylist;
	}

	/**
	 * 給料更新ボタン
	 */
	@RequestMapping(params = "deleteSalary", method = RequestMethod.POST)
	public String deleteSalary(@ModelAttribute("form") @Valid CmsSalaryListForm form, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			//フォームチェックエラーがある場合
			model.addAttribute("form", form);
			return null;
		}
		
		String errMessage = "";
		try {
			//計算ロジックを呼び出す
			service.deleteSalary(form);
		} catch (BusinessException be) {
			errMessage = be.getMessage();
		}
		
		//業務エラーを設定する
		form.setErrorMessage(errMessage);
		model.addAttribute("form", form);
		
		return UrlConst.cmssalarylist;
	}

	/**
	 * 請求書作成を行う
	 */
	@RequestMapping(params = "createPdf", method = RequestMethod.POST)
	public String createPdf(@ModelAttribute("form") CmsSalaryListForm form, Model model) {

		model.addAttribute("form", form);

		//PDF出力
		PdfUtil.printPdf("C:/work/pdf/template/PdfTemple.pdf");
		model.addAttribute("dataList", lst);
		model.addAttribute("errorMessage", "PDFファイルが作成されました。");

		return UrlConst.cmssalarylist;
	}

}