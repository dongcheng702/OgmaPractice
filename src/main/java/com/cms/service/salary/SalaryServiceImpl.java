package com.cms.service.salary;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.entity.salary.CmsSalaryDetailBean;
import com.cms.entity.salary.CmsSalaryDetailRegistBean;
import com.cms.form.salary.CmsSalaryListForm;
import com.cms.mapper.employee.CmsEmployeeMapper;
import com.cms.mapper.salary.CmsSalaryMapper;
import com.exception.BusinessException;
/*
 * ユーザー情報 Service
 */
@Service
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	CmsSalaryMapper salaryMapper;

	@Autowired
	CmsEmployeeMapper employeeMapper;

	/** 給料明細リスト */
	private List<CmsSalaryDetailBean> results;

	/** 給料明細登録リスト */
	private List<CmsSalaryDetailRegistBean> employeeHaveNotSalary;

	private String nm;
	
	private String month;
	/**
	 * ユーザー情報検索 
	 * 
	 * @param userSearchRequest リクエストデータ
	 * @return 検索結果
	 */
	public CmsSalaryListForm select(CmsSalaryListForm form) {

		//画面変数初期化
		init(form);
		
		//給料明細の検索
		checkEmployeeExist(nm);
		
		//給料明細の検索
		getSalaryInfo(nm, month);

		form.setResults(results);

		return form;
	}
	
	//popup练习
	public CmsSalaryListForm dataProcess(CmsSalaryListForm form) {

		//画面変数初期化
		init(form);
		
		//給料明細の検索
		checkEmployeeExist(nm);
		
		//給料明細の検索
		getSalaryInfo(nm, month);

		form.setResults(results);
		
		try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(doc, page);

            // 设置字体和字号
            PDType0Font font = PDType0Font.load(doc, new File("C:\\Windows\\Fonts\\simhei.ttf"));
            contentStream.setFont(font, 12);

            // 添加数据到 PDF
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("社員ID: " + results.get(0).getEmployeeId());
            contentStream.newLine();
            contentStream.newLineAtOffset(0, -20); // 减少 Y 坐标偏移量，将文本放置在下一行
            contentStream.showText("社員名: " + results.get(0).getEmployeeNm());
            contentStream.newLine();
            contentStream.newLineAtOffset(0, -20); // 减少 Y 坐标偏移量，将文本放置在下一行
            contentStream.showText("給料年月: " + results.get(0).getSalaryMonth());
            contentStream.newLine();
            contentStream.newLineAtOffset(0, -20); // 减少 Y 坐标偏移量，将文本放置在下一行
            contentStream.showText("基本給料: " + results.get(0).getSalary());
            contentStream.newLine();
            contentStream.newLineAtOffset(0, -20); // 减少 Y 坐标偏移量，将文本放置在下一行
            contentStream.showText("勤務時間: " + results.get(0).getWorkhours());
            contentStream.newLine();
            contentStream.newLineAtOffset(0, -20); // 减少 Y 坐标偏移量，将文本放置在下一行
            contentStream.showText("残業控除: " + results.get(0).getDisabledGeneration());
            contentStream.newLine();
            contentStream.newLineAtOffset(0, -20); // 减少 Y 坐标偏移量，将文本放置在下一行
            contentStream.showText("費用: " + results.get(0).getCost());
            contentStream.newLine();
            contentStream.newLineAtOffset(0, -20); // 减少 Y 坐标偏移量，将文本放置在下一行
            contentStream.showText("実給(税込): " + results.get(0).getActualSalary());
            // 添加更多数据
            contentStream.endText();

            contentStream.close();
            
            String filepath = "D:\\desktop\\社員ID-" + 
					            results.get(0).getEmployeeId() + 
					            "(" + results.get(0).getEmployeeNm()+")給料明細.pdf";
            
            doc.save(new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }

		return form;
	}
	
	
	/**
	 * 給料明細情報を計算する
	 * 
	 * @param form フォーム
	 * @return 検索結果
	 */
	@Override
	public CmsSalaryListForm createSalary(CmsSalaryListForm form) {

		//画面変数初期化
		init(form);
		
		//■必須チェック
		if (StringUtils.isEmpty(month)) {
			throw new BusinessException("給料計算ボタンを押すため、給料年月をご指定ください。");
		}

		//社員存在チェック
		checkEmployeeExist(nm);
		
		//給料明細の検索
		createEmployeeWhoHaveNotSalary(nm, month);
		
		//再検索実施
		List<CmsSalaryDetailBean> results = researchSalaryDetail(nm, month);
		form.setResults(results);
		
		return form;
	}

	/**
	 * 給料明細情報を削除する
	 * 
	 * @param form フォーム
	 * @return 検索結果
	 */
	@Override
	public CmsSalaryListForm deleteSalary(CmsSalaryListForm form) {
		//画面変数初期化
		init(form);
		
		//削除給料情報を取得する
		String[] ids = form.getItemIds().split(",");

		//一括削除実施
		salaryMapper.deleteRecords(ids);
		
        //再検索
		List<CmsSalaryDetailBean> results = researchSalaryDetail(nm, month);
		form.setResults(results);
		return form;
	}

	/**
	 * 社員情報を検索する
	 * 
	 * @param nm 社員名
	 */
	private void checkEmployeeExist(String nm) {
		//■社員情報存在チェック
		CmsEmployeeBean employeeBean = new CmsEmployeeBean();
		employeeBean.setName(nm);
		
		Integer recordCount = employeeMapper.selectRecordCount(employeeBean);
		if (recordCount == 0) {
			throw new BusinessException("社員は存在しません。");
		}
	}

	/**
	 * 給料明細情報を検索する
	 * 
	 * @param nm 社員名
	 * @param month 給料年月
	 */
	private void getSalaryInfo(String nm , String month) {
		CmsSalaryDetailBean salaryBean = new CmsSalaryDetailBean();

		salaryBean.setEmployeeNm(nm);
		salaryBean.setSalaryMonth(month);
		
		results = salaryMapper.select(salaryBean);
		//検索ボタンを押下後
		if (results != null && results.size() == 0) {
			throw new BusinessException("給料明細データはまだ作成されません。ボタン【給料計算】でご作成ください。");
		}
	}
	
	/**
	 * 社員情報取得（※給料未作成の社員）
	 * 
	 * @param nm 社員名
	 * @param month 給料年月
	 */
	private void createEmployeeWhoHaveNotSalary(String nm, String month) {
		
		CmsSalaryDetailBean bean = new CmsSalaryDetailBean();
		bean.setSalaryMonth(month);
		List<CmsSalaryDetailBean> insRecords = new ArrayList<CmsSalaryDetailBean>();
		CmsSalaryDetailBean record = null;
		
		employeeHaveNotSalary = salaryMapper.selectEmployeeForSalaryIsNotCreated(bean);

		Date today = Calendar.getInstance().getTime();
	    System.out.println("---------start-----------");
		for (CmsSalaryDetailRegistBean emp : employeeHaveNotSalary) {
			record = new CmsSalaryDetailBean();
			//社員ID
			record.setEmployeeId(emp.getEmployeeId());
			
			//給料年月
			record.setSalaryMonth(emp.getMonth());
			
			//給料
			record.setSalary(emp.getSalary());

			//勤務時間
			//勤務時間
			BigDecimal hours = (emp.getHours() == null)? new BigDecimal(0):emp.getHours();
			record.setWorkhours(hours);
			
			//■残業控除を計算する
			//勤務時間上限
			BigDecimal topWorkHour = emp.getTopWorkHour();
			//基本給料（税込）
			BigDecimal salary = emp.getSalary();
			BigDecimal disabledGeneration = new BigDecimal(0);
			if (hours != null) {
				//残業と控除を計算する
				disabledGeneration = salary.divide(topWorkHour, 0, BigDecimal.ROUND_HALF_UP)
						                   .multiply(hours.subtract(topWorkHour));
				record.setDisabledGeneration(disabledGeneration);			
			}
			record.setDisabledGeneration(disabledGeneration);

			//■費用
			//費用合計
			BigDecimal cost =(emp.getCostAmount() == null)? new BigDecimal(0):emp.getCostAmount();
			record.setCost(cost);
			
			//実給(税込)
			BigDecimal actualSalary = salary.add(cost).add(disabledGeneration);
			record.setActualSalary(actualSalary);
			
			//登録日付
			record.setRegistDay(today);

			//更新日付
			record.setUpdateDay(today);
			
			insRecords.add(record);
		}
		
		//一括登録
		salaryMapper.insertBulk(insRecords);
	    System.out.println("---------end-----------");
	}
	
	/**
	 * 再検索を実施する
	 * 
	 * @param nm 社員名
	 * @param month 給料年月
	 * @return 検索結果
	 */
	private List<CmsSalaryDetailBean> researchSalaryDetail(String nm, String month) {
		//再検索条件設定
		CmsSalaryDetailBean bean = new CmsSalaryDetailBean();
		bean.setEmployeeNm(nm);
		bean.setSalaryMonth(month);
		
		return salaryMapper.select(bean);
	}
	
	/**
	 * 変数の初期化
	 * 
	 * @param form フォーム
	 */
	private void init(CmsSalaryListForm form) {
		
		//社員名
		nm = (StringUtils.isEmpty(form.getName())?null:form.getName());
		//給料年月
		month = (StringUtils.isEmpty(form.getMonth())?null:form.getMonth().replace("-", ""));
		
	}
}