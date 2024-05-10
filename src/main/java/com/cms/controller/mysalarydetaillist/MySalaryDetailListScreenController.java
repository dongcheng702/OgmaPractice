package com.cms.controller.mysalarydetaillist;

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
import com.cms.entity.mysalarydetaillist.MyCmsSalaryDetailBean;
import com.cms.form.mysalarydetaillist.MySalaryDetailListScreenForm;
import com.cms.service.mysalarydetaillist.MySalaryDetailListScreenService;

@Controller
@RequestMapping(value = "/mysalarydetaillist/mySalaryDetailListScreen")
public class MySalaryDetailListScreenController extends ControllerBase{

	@Autowired
	MySalaryDetailListScreenService service;

	/**
	 * 練習画面の初期化処理
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model) {

		model.addAttribute("form", new MySalaryDetailListScreenForm());

		return "/mysalarydetaillist/mySalaryDetailListScreen";
	}

	@RequestMapping(params = "readDataBase",method = RequestMethod.POST)
	public String readDataBase(@ModelAttribute("form") MySalaryDetailListScreenForm form,BindingResult result, Model model) {
		
		String retMessage = "";
		try {
			List<MyCmsSalaryDetailBean> ret = service.readDataFromMysql(form);
			form.setResults(ret);

		} catch (Exception e) {
			//エラーメッセージを設定する
			retMessage = e.getMessage();
		}

		if (StringUtils.isNotEmpty(retMessage)) {
			form.setErrorMessage(retMessage);
		}
		
		return "/mysalarydetaillist/mySalaryDetailListScreen";
	}
}
	
