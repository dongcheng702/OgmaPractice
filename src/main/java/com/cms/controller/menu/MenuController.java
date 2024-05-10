package com.cms.controller.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cms.controller.base.ControllerBase;
import com.cms.form.menu.MenuForm;
import com.cms.service.login.LoginService;

/**
 * ユーザー情報 Controller
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends ControllerBase {

	@Autowired
	LoginService userService;

	/**
	 * ユーザー情報検索
	 * 
	 * @param userSearchRequest リクエストデータ
	 * @param model             Model
	 * @return ユーザー情報一覧画面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model) {
		MenuForm form = new MenuForm();
		model.addAttribute("form", form);

//		return "redirect:menu";
		return null;
	}

}