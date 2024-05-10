package com.cms.controller.vuesample;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cms.controller.base.ControllerBase;
import com.cms.entity.benefitcalculation.BenefitcalculationBean;
import com.cms.form.benefitcalculation.BenefitcalculationForm;
import com.cms.service.benefitcalculation.BenefitcalculationService;

// vueSampleController的实现
@Controller
@RequestMapping(value = "/vuesample/vueSample") // 修正路径格式
public class VueSampleController extends ControllerBase {
	
	@Autowired
	BenefitcalculationService Service;
	
	
    @RequestMapping(method = RequestMethod.GET)
    public String init(Model model, HttpServletRequest request) {
        // 在这里你可以添加任何你需要的逻辑
        // 比如从数据库中加载数据，设置模型属性等
        
        return "vuesample/vueSample"; // 返回视图名称，这会告诉Spring Boot去找名为"vuesample/vueSample"的Thymeleaf模板
    }
    
    @PostMapping("/calculate")
    public ResponseEntity<List<BenefitcalculationBean>> calculate(@RequestBody BenefitcalculationForm form) {
        // 执行计算逻辑，处理接收到的表单数据，执行相应的计算操作
    	List<BenefitcalculationBean> ret = new ArrayList<>();
		try {
			ret = Service.getBenefitData(form.getStoreId(), form.getSalesDay());
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        // 将计算结果返回
        return ResponseEntity.ok(ret);
    }
}
