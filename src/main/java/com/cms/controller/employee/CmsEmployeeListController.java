package com.cms.controller.employee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.SystemException;

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
import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.form.cmsemployee.CmsEmployeeListForm;
import com.cms.mapper.common.CommonMapper;
import com.cms.service.employee.CmsEmployeeService;
import com.exception.BusinessException;
import com.utils.PdfUtil;
import com.utils.ServiceUtils;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/employee/cmsemployeelist")
public class CmsEmployeeListController extends ControllerBase {
	private List<CmsEmployeeBean> lst;

	@Autowired
	CmsEmployeeService service;

	@Autowired
	ServiceUtils serviceUtils;
	
	@Autowired
	CommonMapper commonMapper;

	/**
	 * ユーザ画面初期化
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model, HttpServletRequest request) {

		try {
			
			CmsEmployeeListForm form = new CmsEmployeeListForm();
			
			//session情報の設定
			this.setScreenHidden(request, form);
			
			super.setCommonItem(form, true);
			model.addAttribute("form", form);
			
		} catch (SystemException se) {
			System.out.println(se.getMessage());
		}
		
		return UrlConst.cmsemployeelist;
	}

	/**
	 * メニュー画面初期化
	 */
	@RequestMapping(params = "select", method = RequestMethod.POST)
	public String select(@ModelAttribute("form") CmsEmployeeListForm form, Model model, HttpServletRequest request) {
		try {
			//session情報の設定
			this.setScreenHidden(request, form);
			
		    // 画面データ初期化
			searchScreenList(form, model);
		} catch (BusinessException be) {
			
			//業務エラーを画面に表示する
			form.setErrorMessage(be.getMessage());
			model.addAttribute("form", form);
		}
		return UrlConst.cmsemployeelist;
	}

	/**
	 * 画面再検索を実施する
	 * 
	 * @param form 一覧画面フォーム
	 * @param model モデル
	 */
	private void searchScreenList(CmsEmployeeListForm form, Model model) {

		try {
			//項目初期化
			super.setCommonItem(form, false);
			
			// 検索実施
			CmsEmployeeListForm responseForm = service.select(form);
			model.addAttribute("form", responseForm);
			
		} catch (SystemException se) {
			System.out.println(se.getMessage());
		}
	}
	/**
	 * 新規ボタン
	 */
	@RequestMapping(params = "add", method = RequestMethod.POST)
	public String add(Model model) {

		return UrlConst.redirect_cmsemployeeadd;
	}

	/**
	 * 参照ボタン
	 */
	@RequestMapping(params = "read", method = RequestMethod.POST)
	public String read(@ModelAttribute("form") CmsEmployeeListForm form, 
			Model model, RedirectAttributes redirectAttributes, @RequestParam String read) {

		String[] param = read.split("_");
		try {

			//削除対象をサービスに渡す
			form.setSelectedItemId(read);
			//参照ボタンの場合、trueを設定する
			form.setBtnFlg(false);
			service.optimismCheck(form);
			
		} catch (BusinessException be) {
			
			form.setErrorMessage(be.getMessage());
			// 画面データ初期化
			searchScreenList(form, model);
			
			return UrlConst.cmsemployeelist;
		}

		
		// 更新画面へ渡す引数：社員ＩＤ
		redirectAttributes.addAttribute("selectedEmployeeId", param[0]);
		return UrlConst.redirect_cmsemployeeview;
	}

	/**
	 * 更新ボタン
	 */
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("form") CmsEmployeeListForm form, 
			Model model, RedirectAttributes redirectAttributes, @RequestParam String update) {
        
		String[] param = update.split("_");
		try {

			//削除対象をサービスに渡す
			form.setSelectedItemId(update);
			//参照ボタンの場合、trueを設定する
			form.setBtnFlg(true);
			service.optimismCheck(form);
			
		} catch (BusinessException be) {
			
			form.setErrorMessage(be.getMessage());
			// 画面データ初期化
			searchScreenList(form, model);
			return UrlConst.cmsemployeelist;
		}

		// 更新画面へ渡す引数：社員ＩＤ
		redirectAttributes.addAttribute("selectedEmployeeId", param[0]);
		return UrlConst.redirect_cmsemployeeedit;

	}

	/**
	 * 削除ボタン
	 */
	@RequestMapping(params = "delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute("form") CmsEmployeeListForm form, 
			Model model, @RequestParam String delete) {

		try {
			
			//削除対象をサービスに渡す
			form.setSelectedItemId(delete);
			//削除を行う
			CmsEmployeeListForm responseForm = service.delete(form);

			// 画面データ初期化
			searchScreenList(responseForm, model);
		} catch (BusinessException be) {
			
			form.setErrorMessage(be.getMessage());
			//削除が失敗したら、再検索をしなく、そのままエラー情報を画面に設定する
			// 画面データ初期化
			searchScreenList(form, model);
		}

		return UrlConst.cmsemployeelist;

	}
	
	/**
	 * 全削除
	 */
	@RequestMapping(params = "deleteAll", method = RequestMethod.POST)
	public String deleteAll(@ModelAttribute("form") CmsEmployeeListForm form, Model model) {

		//全削除サービスを呼び出す
		service.deleteAll(form);

		// 画面データ初期化
		searchScreenList(form, model);
		
		return UrlConst.cmsemployeelist;

	}
	/**
	 * 請求書作成を行う
	 */
	@RequestMapping(params = "createPdf", method = RequestMethod.POST)
	public String createPdf(@ModelAttribute("form") CmsEmployeeListForm form, Model model) {
		try {
			// 社員区分（ラジオボタン）
			super.setCommonItem(form, true);
			
			// PDF出力
			PdfUtil.printPdf("C:/work/pdf/template/PdfTemple.pdf");
			model.addAttribute("dataList", lst);
			form.setErrorMessage("PDFファイルが作成されました。");
			model.addAttribute("form", form);
			
		} catch (SystemException se) {
			System.out.println(se.getMessage());
		}
		
		return UrlConst.cmsemployeelist;
	}

	private void setScreenHidden(HttpServletRequest request, CmsEmployeeListForm form) {
		/**-----sessionからログインの社員IDを取得する-----*/
		HttpSession session = request.getSession();
		//社員IDを設定する
		String employeeId = String.valueOf(session.getAttribute("employeeId"));
		form.setSessionEmployeeId(employeeId);
		//職種を設定する
		String jobType = String.valueOf(session.getAttribute("jobType"));
		form.setSessionJobType(jobType);
		
		//社員の場合、新規ボタンを禁止する
		form.setDisabledFlg(("J5".equals(jobType)) ? "display:none;":"");
	}
}