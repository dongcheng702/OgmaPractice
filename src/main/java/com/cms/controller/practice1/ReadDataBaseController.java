package com.cms.controller.practice1;

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
import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.form.practice1.ReadDataBaseForm;
import com.cms.service.practice1.ReadDataBaseService;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/practice1/readdatabase")
public class ReadDataBaseController extends ControllerBase {

	@Autowired
	ReadDataBaseService service;

	/**
	 * 練習画面の初期化処理
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model) {

		model.addAttribute("form", new ReadDataBaseForm());

		return "/practice1/readdatabase";
	}

	/**
	 * データ検索
	 */
	@RequestMapping(params = "readDataBase", method = RequestMethod.POST)
	public String readDataBase(@ModelAttribute("form") ReadDataBaseForm form,BindingResult result, Model model) {

		String retMessage = "";
		try {

		
			// CSVファイルを取込んで登録する
			List<CmsEmployeeBean> ret = service.readDataFromMysql(form);
			form.setResults(ret);

		} catch (Exception e) {
			System.out.println(e);
			//エラーメッセージを設定する
			retMessage = e.getMessage();
		}

		if (StringUtils.isNotEmpty(retMessage)) {
			form.setErrorMessage(retMessage);
		}

		model.addAttribute("form", form);
	    
		return "/practice1/readdatabase";
	}
}