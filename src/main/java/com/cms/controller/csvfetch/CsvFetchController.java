package com.cms.controller.csvfetch;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.cms.controller.base.ControllerBase;
import com.cms.form.csvfetch.csvfetchForm;
import com.cms.service.csvfetch.CsvFetchService;

@Controller
@RequestMapping(value = "/csvfetch/csvfetch")
public class CsvFetchController extends ControllerBase{

	@Autowired
	CsvFetchService service;
	
	private final static String URL = "/csvfetch/csvfetch";
	
	@RequestMapping(method = RequestMethod.GET)
	public String init(Model model) {
		
		model.addAttribute("form", new csvfetchForm());
		
		return URL;
	}
	
	@RequestMapping(params = "upcsvdata",method = RequestMethod.POST)
	public String upcsvdata(@ModelAttribute("form") csvfetchForm form){
		String retMessage = "";
		MultipartFile file = form.getFileInput();
	    
	    if(form.getItemCount() == 0){
	    	retMessage = "項目件数を入力してください。";
	    	form.setErrorMessage(retMessage);
	    	return URL;
	    }
	    
	    if (file.isEmpty()) {
	        retMessage = "アップロードされたファイルは空です。";
	        form.setErrorMessage(retMessage);
	        return URL;
	    }
	    
	    try {
	        // 上传的CSV文件内容
	    	int insDataCount =service.registCsvToMySql(file.getBytes(),form.getItemCount());
			retMessage = "Csvデータが登録されました。(登録件数：" + insDataCount + "件 )";

	    } catch (DataIntegrityViolationException e) {
	    	retMessage = "明細データの項目件数が正しくない（又はデータが存在している）。";
			
	    } catch (ArrayIndexOutOfBoundsException e) {
	    	retMessage = "明細データの項目件数が正しくない。";
	    	
	    } catch (Exception e) {
	        e.printStackTrace();
	        retMessage = e.getMessage();
	    }

	    if (StringUtils.isNotEmpty(retMessage)) {
	        form.setErrorMessage(retMessage);
	    }

	    return URL;
	}
}
