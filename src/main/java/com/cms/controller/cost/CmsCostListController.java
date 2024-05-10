package com.cms.controller.cost;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cms.common.UrlConst;
import com.cms.controller.base.ControllerBase;
import com.cms.entity.cost.CmsCostBean;
import com.cms.form.cost.CmsCostForm;
import com.cms.form.cost.CmsCostListForm;
import com.cms.mapper.common.CommonMapper;
import com.cms.service.cost.CmsCostService;
import com.cms.service.model.CmsSelectEmployeeService;
import com.utils.PdfUtil;
/*
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/cost/cmscostlist")
//@Slf4j
public class CmsCostListController extends ControllerBase {
	private List<CmsCostBean> lst;

	@Autowired
	CmsCostService service;
	
	@Autowired
	CmsSelectEmployeeService employeeService;
	

	@Autowired
	CommonMapper commonMapper;
	/**
	 * ユーザ画面初期化
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model) {

		CmsCostListForm form = new CmsCostListForm();

		model.addAttribute("form", form);

		return UrlConst.cmscostlist;
	}

	/**
	 * メニュー画面初期化
	 */
	@RequestMapping(params = "select", method = RequestMethod.POST)
	public String select(@ModelAttribute("form") CmsCostListForm form, Model model, HttpServletRequest request) {
		

		/**-----sessionからログインの社員IDを取得する-----*/
		HttpSession session = request.getSession();
		//社員IDを設定する
		String employeeId = String.valueOf(session.getAttribute("employeeId"));
		form.setSessionEmployeeId(employeeId);
		//職種を設定する
		String jobType = String.valueOf(session.getAttribute("jobType"));
		form.setSessionJobType(jobType);
		
		// 検索実施
		CmsCostListForm searchResultform = service.select(form);

		model.addAttribute("form", searchResultform);
		return UrlConst.cmscostlist;
	}

	/**
	 * 新規ボタン
	 */
	@RequestMapping(params = "add", method = RequestMethod.POST)
	public String add(Model model) {

		return UrlConst.redirect_cmscostadd;
	}

	/**
	 * 参照ボタン
	 */
	@RequestMapping(params = "read", method = RequestMethod.POST)
	public String read(RedirectAttributes redirectAttributes, @RequestParam String read) {

		// 更新画面へ渡す引数：社員ＩＤ
		redirectAttributes.addAttribute("selectedId", read);
		return UrlConst.redirect_cmscostview;

	}

	/**
	 * 更新ボタン
	 */
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String update(RedirectAttributes redirectAttributes, @RequestParam String update) {

		// 更新画面へ渡す引数：社員ＩＤ
		redirectAttributes.addAttribute("selectedId", update);
		return UrlConst.redirect_cmscostedit;

	}

	/**
	 * 削除ボタン
	 */
	@RequestMapping(params = "delete", method = RequestMethod.POST)
	public String delete(RedirectAttributes redirectAttributes, @RequestParam String delete, Model model) {

		CmsCostListForm form = new CmsCostListForm();
		form.setCostId(delete);
		service.delete(form);

		// 画面データ初期化
		model.addAttribute("form", form);
		return UrlConst.cmscostlist;

	}

	/**
	 * 全削除
	 */
	@RequestMapping(params = "deleteAll", method = RequestMethod.POST)
	public String deleteAll(@ModelAttribute("form") CmsCostListForm form, Model model) {

		//全削除サービスを呼び出す
		service.deleteAll(form);

		// 画面データ初期化
		model.addAttribute("form", form);
		
		return UrlConst.cmscostlist;

	}

	/**
	 * 請求書作成を行う
	 */
	@RequestMapping(params = "createPdf", method = RequestMethod.POST)
	public String createPdf(@ModelAttribute("form") CmsCostForm form, Model model) {

		model.addAttribute("form", form);

		
		//PDF出力
		PdfUtil.printPdf("C:/work/pdf/template/PdfTemple.pdf");
		model.addAttribute("dataList", lst);
		model.addAttribute("errorMessage", "PDFファイルが作成されました。");

		return UrlConst.cmscostlist;
	}
	

//	// ----------PopUp画面（社員選択画面）・検索ボタン start----------
//
//	@PostMapping(value = "/searchEmployees")
//	@ResponseBody
//	public List<CmsEmployeeBean> searchEmployees(@RequestParam Map<String, Object> params) {
//
//		CmsEmployeeBean bean = new CmsEmployeeBean();
//		bean.setName(params.get("name").toString());
//		List<CmsEmployeeBean> result = employeeService.select(bean);
//
//		return result;
//	}
//	// ----------PopUp画面（社員選択画面）・検索ボタン end ----------
}