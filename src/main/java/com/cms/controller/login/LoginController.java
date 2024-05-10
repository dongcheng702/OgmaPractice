package com.cms.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.form.cmsemployee.CmsEmployeeForm;
import com.cms.form.login.LoginForm;
import com.cms.service.login.LoginService;

/**
 * ログイン コントローラー
 */
@Controller
public class LoginController {

	@Autowired
	LoginService service;
	
	@Autowired
	HttpSession session;

	/**
	 * ユーザー情報検索画面を表示
	 * 
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@GetMapping(value = "/login")
	public String init(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.setAttribute("token", "authorized");
		
		CmsEmployeeForm form = new CmsEmployeeForm();
		model.addAttribute("userForm", form);

		return "login";
	}

	/**
	 * ユーザー情報検索
	 * 
	 * @param userSearchRequest リクエストデータ
	 * @param model             Model
	 * @return ユーザー情報一覧画面
	 */
	@RequestMapping(value = "/login", params = "gotoMenu", method = RequestMethod.POST)
	public String gotoMenu(@ModelAttribute LoginForm form, Model model, HttpServletRequest request) {

		CmsEmployeeBean requestBean = new CmsEmployeeBean();
		requestBean.setLoginId(form.getLoginId());
		requestBean.setPassword(form.getPassword());

		LoginForm formChk = service.selectLoginInfo(form);
		
		if (formChk.getResults() != null && formChk.getResults().size() > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("employeeId", formChk.getResults().get(0).getEmployeeId());
			session.setAttribute("employeeName", formChk.getResults().get(0).getName());
			session.setAttribute("jobType", formChk.getResults().get(0).getJobType());
			return "redirect:/menu";
		} else {
			model.addAttribute("errors", "ユーザまたはパスワードが正しくありません");
			model.addAttribute("userForm", form);

			return "redirect:/login";
		}

	}
}