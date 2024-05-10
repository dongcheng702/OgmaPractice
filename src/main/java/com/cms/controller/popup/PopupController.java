package com.cms.controller.popup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.controller.base.ControllerBase;
import com.cms.entity.salary.CmsSalaryDetailBean;
import com.cms.form.salary.CmsSalaryListForm;
import com.cms.service.salary.SalaryService;

@Controller
@RequestMapping(value = "/popup/popup")
public class PopupController extends ControllerBase{
	@Autowired
	SalaryService service;
	
	private final static String URL = "/popup/popup";
	
	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model) {
		
		model.addAttribute("form", new CmsSalaryListForm());
		
		return URL;
	}
	
	@RequestMapping(params = "createpdf",method = RequestMethod.POST)
	public String createpdf(@ModelAttribute("form") CmsSalaryListForm form) {
		
		service.dataProcess(form);
			
		return URL;
	}
	
	@GetMapping("/download/pdf")
	public ResponseEntity<byte[]> downloadPdf() throws IOException {
	    File file = new File("D:\\desktop\\社員ID-2(002)給料明細.pdf");
	    byte[] pdfContent = Files.readAllBytes(file.toPath());

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_PDF);
	    headers.setContentDisposition(ContentDisposition.builder("attachment").filename("社員ID-2(002)給料明細.pdf").build());

	    return ResponseEntity.ok()
	            .headers(headers)
	            .body(pdfContent);
	}
	
	// ----------PopUp画面（社員選択画面）・検索ボタン start----------

	@PostMapping(value = "/popupmodal")
	@ResponseBody
	public List<CmsSalaryDetailBean> searchEmployees(@RequestParam Map<String, Object> params,Model model) {

		CmsSalaryListForm form = new CmsSalaryListForm();
		form.setName(params.get("name").toString());
		//bean.setEmployeeId("1");
		service.select(form);
		//result.add(bean);
		return form.getResults();
	}
	// ----------PopUp画面（社員選択画面）・検索ボタン end ----------
}
