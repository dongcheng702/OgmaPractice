package com.cms.controller.restapisample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.controller.base.ControllerBase;
import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.service.restapisample.RestApiSampleService;

/**
 * ログイン コントローラー
 */
@RestController
@RequestMapping(value = "/searchEmployee")
public class RestApiSampleController extends ControllerBase {

	@Autowired
	RestApiSampleService service;

	/**
	 * ユーザ画面初期化
	 * 
	 * @PathVariable⇒この変数はURLから取得できます。
	 */
    @GetMapping("/{id}")
	public CmsEmployeeBean getSingle(@PathVariable String id) {
    	List<CmsEmployeeBean> results = service.getEmployeeById(id);
    	return results.get(0);
	}
    
	/**
	 * ユーザ画面初期化
	 */
    @GetMapping
	public List<CmsEmployeeBean> getAll() {

		return service.getEmployeeById(null);
	}
    
	@PostMapping
    public CmsEmployeeBean create(@RequestBody CmsEmployeeBean emp) {
        return service.createEmployee(emp);
    }
}