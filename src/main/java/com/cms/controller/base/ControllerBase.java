package com.cms.controller.base;

import java.util.List;
import java.util.Map;

import javax.transaction.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.form.cmsemployee.CmsEmployeeForm;
import com.cms.form.cmsemployee.CmsEmployeeListForm;
import com.cms.service.model.CmsSelectEmployeeService;
import com.utils.ServiceUtils;

/**
 * 新規コントローラ
 */
@Component
public class ControllerBase {

	@Autowired
	CmsSelectEmployeeService employeeService;
	
	@Autowired
	ServiceUtils serviceUtils;

	/**
	 * メニューをクリックする
	 */
	@RequestMapping(value = "", params = "transitionTo", method = RequestMethod.POST)
	public String transitionTo(Model model, @RequestParam String transitionTo) {
		// 画面データ初期化
		return "redirect:" + transitionTo;
	}

	/**
	 * 共通項目設定(編集画面)
	 * 
	 * @param form    社員編集画面フォーム
	 * @param initFlg 画面初期化フラグ
	 * @throws SystemException 
	 */
	public void setCommonItem(CmsEmployeeForm form, boolean initFlg) {

		try {
			// 選択リスト{EMPLOYEE_TYPE:社員種別}
			form.setEmployeeKbnMap(serviceUtils.getGeneralMastInfo("EMPLOYEE_KBN"));
	
			// 選択リスト{SEXY:性別}
			form.setSexyList(serviceUtils.getGeneralMastInfo("SEXY"));
	
			// 選択リスト{JOB_TYPE:職種}
			form.setJobTypeList(serviceUtils.getGeneralMastInfo("JOB_TYPE"));
			
			// 選択リスト{EMPLOYEE_TYPE:社員種別}
			form.setEmployeeTypeList(serviceUtils.getGeneralMastInfo("EMPLOYEE_TYPE"));
			
			// 選択リスト{TAX_FLG:税金有無}
			form.setHasTaxList(serviceUtils.getGeneralMastInfo("TAX_FLG"));
	
			if (initFlg) {
				form.setSelectedEmployeeKbn("0");
				form.setSelectedSexy("0");
				form.setSelectedJobType("J5");
				form.setSelectedEmployeeType("0");
				form.setSelectedHasTax("0");
			}
		
		} catch (SystemException se) {
			System.out.println(se.getMessage());
		}
	}

	/**
	 * 共通項目設定(一覧画面)
	 * 
	 * @param form    社員編集画面フォーム
	 * @param initFlg 初期化フラグ
	 * @throws SystemException 
	 */
	public void setCommonItem(CmsEmployeeListForm form, boolean initFlg) throws SystemException  {

		// 選択リスト{EMPLOYEE_TYPE:社員種別}
		Map<String, String> genderMap = serviceUtils.getGeneralMastInfo("EMPLOYEE_KBN");
		genderMap.put("2", "すべて");
		form.setEmployeeKbnMap(genderMap);
		if (initFlg) {
			form.setSelectedEmployeeKbn("0");
		}

		Map<String, String> sexMap = serviceUtils.getGeneralMastInfo("SEXY");
		sexMap.put("2", "すべて");
		// 選択リスト{SEXY:性別}
		form.setSexyList(sexMap);
		if (initFlg) {
			form.setSelectedSexy("0");
		}

		// 選択リスト{JOB_TYPE:職種}
		form.setJobTypeList(serviceUtils.getGeneralMastInfo("JOB_TYPE"));
		if (initFlg) {
			form.setSelectedJobType("J5");
		}
	}

	// ----------PopUp画面（社員選択画面）・検索ボタン start----------

	@PostMapping(value = "/searchEmployees")
	@ResponseBody
	public List<CmsEmployeeBean> searchEmployees(@RequestParam Map<String, Object> params) {

		CmsEmployeeBean bean = new CmsEmployeeBean();
		bean.setName(params.get("name").toString());
		List<CmsEmployeeBean> result = employeeService.select(bean);

		return result;
	}
	// ----------PopUp画面（社員選択画面）・検索ボタン end ----------
}