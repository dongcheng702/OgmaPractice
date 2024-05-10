package com.cms.service.salary;

import com.cms.form.salary.CmsSalaryListForm;

public interface SalaryService {

	//給料検索
	public CmsSalaryListForm select(CmsSalaryListForm form);
	//給料作成
	public CmsSalaryListForm createSalary(CmsSalaryListForm form);
	//給料削除
	public CmsSalaryListForm deleteSalary(CmsSalaryListForm form);
	
	//popup练习
	public CmsSalaryListForm dataProcess(CmsSalaryListForm form);
	
}
