package com.cms.controller.workhour;

import java.util.List;

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
import com.cms.entity.cmsworkhour.CmsWorkHourBean;
import com.cms.form.cmsworkhour.CmsWorkHourForm;
import com.cms.mapper.common.CommonMapper;
import com.cms.service.workhour.CmsWorkHourService;
import com.utils.CmsUtils;
import com.utils.PdfUtil;

/**
 * ログイン コントローラー
 */
@Controller
@RequestMapping(value = "/workhour/cmsworkhourlist")
public class CmsWorkHourListController extends ControllerBase {
	private List<CmsWorkHourBean> lst;

	@Autowired
	CmsWorkHourService service;

	@Autowired
	CommonMapper commonMapper;

	/**
	 * ユーザ画面初期化
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model) {

		CmsWorkHourForm form = new CmsWorkHourForm();

		// 社員区分（ラジオボタン）
		form.setGenders(CmsUtils.createGenders());
		// 社員区分の初期値を設定する
		form.setSelectGender("社員");

		model.addAttribute("form", form);

		return UrlConst.cmsworkhourlist;
	}

	/**
	 * メニュー画面初期化
	 */
	@RequestMapping(params = "select", method = RequestMethod.POST)
	public String select(@ModelAttribute("form") CmsWorkHourForm form, Model model) {
		// 画面データ初期化
		// 検索実施
		CmsWorkHourForm searchResultform = service.select(form);

		// 社員区分（ラジオボタン）
		searchResultform.setGenders(CmsUtils.createGenders());

		model.addAttribute("form", searchResultform);
		return UrlConst.cmsworkhourlist;
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
	public String read(RedirectAttributes redirectAttributes, @RequestParam String read) {

		// 更新画面へ渡す引数：社員ＩＤ
		redirectAttributes.addAttribute("selectedEmployeeId", read);
		return UrlConst.redirect_cmsemployeeview;

	}

	/**
	 * 更新ボタン
	 */
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public String update(RedirectAttributes redirectAttributes, @RequestParam String update) {

		// 更新画面へ渡す引数：社員ＩＤ
		redirectAttributes.addAttribute("selectedEmployeeId", update);
		return UrlConst.redirect_cmsemployeeedit;

	}

	/**
	 * 削除ボタン
	 */
	@RequestMapping(params = "delete", method = RequestMethod.POST)
	public String delete(RedirectAttributes redirectAttributes, @RequestParam String delete) {

		CmsWorkHourForm form = new CmsWorkHourForm();
		form.setEmployeeId(delete);
		service.delete(form);

		return UrlConst.redirect_cmsemployeelist;

	}

	/**
	 * 請求書作成を行う
	 */
	@RequestMapping(params = "createPdf", method = RequestMethod.POST)
	public String createPdf(@ModelAttribute("form") CmsWorkHourForm form, Model model) {
		// 社員区分（ラジオボタン）
		form.setGenders(CmsUtils.createGenders());
		model.addAttribute("form", form);

		
		//PDF出力
		PdfUtil.printPdf("C:/work/pdf/template/PdfTemple.pdf");
		model.addAttribute("dataList", lst);
		model.addAttribute("errorMessage", "PDFファイルが作成されました。");

		return UrlConst.cmsworkhourlist;
	}

}