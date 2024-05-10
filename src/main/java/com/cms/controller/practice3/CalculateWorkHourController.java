package com.cms.controller.practice3;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cms.controller.base.ControllerBase;
import com.cms.entity.invoice.CmsInvoiceBean;
import com.cms.form.practice3.CalculateWorkHourForm;
import com.cms.service.practice3.CalculateInvoiceService;
import com.exception.BusinessException;

/**
 * 請求書計算Controller
 */
@Controller
@RequestMapping(value = "/practice3/calculateinvoice")
public class CalculateWorkHourController extends ControllerBase {

	@Autowired
	CalculateInvoiceService service;

	/**
	 * 請求書計算画面の初期化
	 * 
	 * @param model モデル（データ保存）
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model) {

		model.addAttribute("form", new CalculateWorkHourForm());

		return "/practice3/calculateinvoice";
	}

	/**
	 * 請求書計算
	 * 
	 * @param form 請求書計算画面のフォーム
	 * @param result Validatorのエラー登録
	 * @param model モデル（データ保存）
	 */
	@RequestMapping(params = "calculate", method = RequestMethod.POST)
	public String calculate(@ModelAttribute("form")@Valid CalculateWorkHourForm form,BindingResult result,  Model model) {
		
		try {
			if (result.hasErrors()) {
				//フォームチェックエラーがある場合
				model.addAttribute("form", form);
				return null;
			}
			
			// 請求書計算サービスを呼び出す
			List<CmsInvoiceBean> ret = service.calculateWorkHourAndSalary(form.getEmployeeId() ,form.getWorkMonth());

			//計算結果をフォームに保存する
			form.setResults(ret);

		} catch (BusinessException e) {
			
			//エラーメッセージを設定する
			form.setErrorMessage(e.getMessage());
			System.out.println(e.getErrDetail());
		    
		} catch (Exception e) {
			
			//エラーメッセージを設定する
			form.setErrorMessage(e.getMessage());
			System.out.println(e);
			
		}
		//フォームをモデルに保存し、画面に返却する
		model.addAttribute("form", form);
		
		return "/practice3/calculateinvoice";
	}
}