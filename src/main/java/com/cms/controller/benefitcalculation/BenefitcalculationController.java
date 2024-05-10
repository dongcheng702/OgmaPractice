package com.cms.controller.benefitcalculation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cms.controller.base.ControllerBase;
import com.cms.entity.benefitcalculation.BenefitcalculationBean;
import com.cms.form.benefitcalculation.BenefitcalculationForm;
import com.cms.service.benefitcalculation.BenefitcalculationService;

//表明这是一个Spring MVC控制器类，注解告诉 Spring 这个类是一个处理 HTTP 请求的控制器，它通常配合 @RequestMapping 注解来指定处理的 URL 路径
@Controller
//映射 HTTP 请求到处理方法，可以用于类或方法上，用于定义处理哪些 URL 请求。它可以设置 HTTP 方法，URL 路径，请求参数等条件来精确匹配请求
@RequestMapping(value = "/benefitcalculation/benefitcalculation")
public class BenefitcalculationController extends ControllerBase{
	//@Autowired 注解可以省去手动创建对象的过程。Spring 框架会在需要的时候自动为你创建对象，并将其注入到相应的地方
	@Autowired
	BenefitcalculationService Service;
	//定义一个URL常量，存储控制器处理请求后返回的页面的URL
	private final static String URL = "/benefitcalculation/benefitcalculation";
	//指定了只有HTTP GET请求会被映射到这个方法上
	@RequestMapping(method = RequestMethod.GET)
	//初始化、接受两个参数，一个是Spring的模型对象Model，用于向视图传递数据。另一个是HttpServletRequest对象，用于处理HTTP请求，获取请求信息，在这里并没有用到,可以去掉
	public String init(Model model, HttpServletRequest request) {
		//向模型中添加一个名为"form"的属性，属性值是一个新创建的benefitcalculationForm对象。通常，这个对象包含了在页面中展示和处理的数据
		model.addAttribute("form", new BenefitcalculationForm());
		//返回页面的URL，Spring MVC会将请求重定向到这个URL对应的页面
		return URL;
	}
	/*
	 * params = "calculate": 指定了请求参数，只有当请求中包含名为"calculate"的参数时，该方法才会被映射到
	 * 同时指定了只有HTTP POST请求会被映射到这个方法上
	 * 当用户向服务器发送POST请求并且请求的URL与这个注解所标注的URL匹配，并且请求参数包含名为"calculate"时，Spring MVC将会调用这个方法来处理请求
	 * @ModelAttribute注解将表单数据绑定到benefitcalculationForm对象中
	 * 因为已经通过@ModelAttribute注解绑定了，Model model这个参数可以去掉
	 * HttpServletRequest对象也是在这里并没有用到,可以去掉。
	 * 当使用 @Valid 注解对请求参数进行验证时，如果验证失败，通常会返回一个错误响应。错误响应应该包含有关验证失败的详细信息，以便客户端了解出错的原因。
	 * @Valid可以通过处理方法的参数中的 BindingResult 对象来获取验证结果，并根据需要进行处理。
	*/
	@RequestMapping(params = "calculate",method = RequestMethod.POST)
	public String calculate(@ModelAttribute("form") @Valid BenefitcalculationForm form,BindingResult result,Model model, 
			HttpServletRequest request) {
		String retMessage = "";
		
		if(result.hasErrors()){
			return null;
		}
		
		try {
			List<BenefitcalculationBean> ret = Service.getBenefitData(form.getStoreId(), form.getSalesDay());
			form.setResults(ret);
			
		} catch (IllegalStateException e) {
			retMessage = e.getMessage();	
			
		} catch (Exception e) {
			retMessage = e.getMessage();
			
		}
		
	    if (StringUtils.isNotEmpty(retMessage)) {
	        form.setErrorMessage(retMessage);
	    }
			
		return URL;
	}
}
