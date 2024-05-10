package com.cms.controller.marketstore;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cms.controller.base.ControllerBase;
import com.cms.entity.marketstore.MarketstoreBean;
import com.cms.form.marketstore.MarketstoreForm;
import com.cms.service.marketstore.MarketstoreService;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/marketstore/marketstore")
public class MarketstoreController extends ControllerBase {
	@Autowired
	MarketstoreService service;

	/**
	 * 店铺画面的初始化
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model, HttpServletRequest request) {

		model.addAttribute("form", new MarketstoreForm());
		
		return "/marketstore/marketstore";
	}
	
	
	@RequestMapping(params = "readStoreData",method = RequestMethod.POST)
	public String readStoreData(@ModelAttribute("form") MarketstoreForm form, Model model) {

		try {
			
			//查询数据设置到form里
			List<MarketstoreBean> ret = service.select(form);
			form.setResults(ret);
			
		} catch (Exception e) {
			//设置异常信息
			form.setErrorMessage(e.getMessage());
		}
		
		
		return "/marketstore/marketstore";
	}

}