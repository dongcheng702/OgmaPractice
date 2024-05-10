package com.cms.controller.practice2;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cms.controller.base.ControllerBase;
import com.cms.form.practice2.RegistCsvForm;
import com.cms.service.practice2.RegistCsvService;

/**
 * 勤怠登録画面 コントローラー
 */
@Controller
@RequestMapping(value = "/practice2/registcsv")
public class RegistCsvController extends ControllerBase {

	@Autowired
	RegistCsvService service;

	private final static String URL = "/practice2/registcsv";
	/**
	 * 勤怠登録画面の初期化処理
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model) {

		model.addAttribute("form", new RegistCsvForm());

		return URL;
	}

	/**
	 * CSVファイル取込
	 * @throws Exception 
	 */
	@RequestMapping(params = "loadCsv", method = RequestMethod.POST)
	public String loadCsv(@ModelAttribute("form")@Valid RegistCsvForm form,BindingResult result, Model model) throws Exception {
		String retMessage = "";
		try {
			if (result.hasErrors()) {
				//フォームチェックエラーがある場合
				model.addAttribute("form", form);
				return null;
			}
			//D:/eclipse/dev_ogm_pg/pleiades/workspace/OgmaPractice/src/test/data/practice2/cms_work_hour.csv
			
			// CSVファイルを取込んで登録する
			int registRecordCount = service.registCsvToMySql(form.getFilePath(), Integer.valueOf(form.getItemCount()));

			retMessage = "Csvデータが登録されました。(登録件数：" + String.valueOf(registRecordCount) + "件 )";
			// 登録件数
			form.setRegistRecordCount(String.valueOf(registRecordCount));
			
		}catch (Exception e) {
			retMessage = e.getMessage();
		}

		if (StringUtils.isNotEmpty(retMessage)) {
			form.setErrorMessage(retMessage);
		}
		model.addAttribute("form", form);
	    
		return URL;
	}
}