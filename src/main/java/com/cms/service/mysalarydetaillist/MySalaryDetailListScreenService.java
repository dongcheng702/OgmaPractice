package com.cms.service.mysalarydetaillist;

import java.util.List;

import com.cms.entity.mysalarydetaillist.MyCmsSalaryDetailBean;
import com.cms.form.mysalarydetaillist.MySalaryDetailListScreenForm;

public interface MySalaryDetailListScreenService {

	public List<MyCmsSalaryDetailBean> readDataFromMysql(MySalaryDetailListScreenForm form) throws Exception;
}
