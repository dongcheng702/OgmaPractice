package com.cms.service.practice2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.cms.entity.cmsworkhour.CmsWorkHourBean;
import com.cms.mapper.practice2.Practice2Mapper;
import com.exception.BusinessException;
import com.utils.CmsUtils;

/**
 * 勤怠登録画面のサービス
 */
@Service
public class RegistCsvServiceImpl implements RegistCsvService {

	@Autowired
	Practice2Mapper mapper;

	private List<CmsWorkHourBean> csvList;
	private int itemCount;
	private int insCount;

	private BufferedReader textFile;

	
	/**
	 * ファイル取込
	 * 
	 * @param filePath  ファイルパス
	 * @param itemCount 項目数
	 * @throws Exception 
	 */
	public int registCsvToMySql(String filePath, int itemCount) throws Exception {
		
		try {
			File csvFile = new File(filePath);
			this.itemCount = itemCount;
			
			// ■CSVファイルチェック
			checkInput(csvFile);
	
			// ■CSVファイル解析
			readFile(csvFile);

			// 正常終了
			return insCount;
			
		} catch (IOException e) {
			throw new BusinessException("ファイル取込が失敗しました。");
		} catch (DuplicateKeyException se) {
			throw new BusinessException("CSVファイルには重複キー項目が存在します。データをご確認ください。");
		}
	}

	/**
	 * CSVファイルの存在チェック
	 * 
	 * @param csvFile 対象CSVファイル
	 * @return エラーメッセージ
	 * @throws Exception 
	 */
	private void checkInput(File csvFile) throws Exception {

		// ■チェック
		if (!csvFile.exists()) {
			throw new BusinessException("ファイルが指定されません。");
		}

		long fileSize = csvFile.length();

		if (fileSize == 0) {
			throw new BusinessException("空白ファイルです。");
		}
	}

	/**
	 * CSV行解析
	 * 
	 * @param path  ファイルパス
	 * @param count 項目数
	 * @return 登録件数
	 * @throws Exception 
	 */
	private void readFile(File csvFile) throws Exception {
		//登録件数
		insCount = 0;
		
		csvList = new ArrayList<CmsWorkHourBean>();
		textFile = new BufferedReader(new FileReader(csvFile));

		String lineValue = textFile.readLine();

		String[] headerItems = lineValue.split(",");
		if (headerItems.length != this.itemCount) {
			
			// ヘッダ項目数のチェック
			throw new BusinessException("ヘッダ項目件数は正しくありません。(正確の項目数：７)");
		}

		// ■CSV解析
		while (lineValue != null) {
			lineValue = textFile.readLine();
			if (lineValue == null) {
				// 最終行の場合、ループ処理を終了する
				break;
			}
			CmsWorkHourBean bean = saveToList(lineValue);
			if (bean != null) {
				csvList.add(bean);
			}
		}
		
		// ■CSV登録
		if (csvList.size() > 0) {
			// 勤怠時間を登録する
			insCount = mapper.insertBulk(csvList);
		}
	}

	/**
	 * CSV行解析
	 * 
	 * @param line 行内容
	 * @return 解析済のBean
	 * @throws Exception 
	 */
	private CmsWorkHourBean saveToList(String line) throws Exception {
		String[] itemValues = line.split(",");

		if (itemValues.length != this.itemCount) {
			throw new BusinessException("明細項目件数は正しくありません。(正確の項目数：７)");
		}
		CmsWorkHourBean bean = new CmsWorkHourBean();
		// 社員ID
		bean.setEmployeeId(itemValues[0]);

		// 勤務年月日
		bean.setWorkDay(CmsUtils.FormatToTimestame(itemValues[1]));
		// 開始時間
		bean.setStartTime(CmsUtils.FormatToTimestame(itemValues[2]));
		// 終了時間
		bean.setEndTime(CmsUtils.FormatToTimestame(itemValues[3]));
		// 勤務時間
		bean.setWorkHous(Integer.parseInt(itemValues[4].trim()));
		// ステータス
		// 登録日時
		bean.setRegistDay(CmsUtils.FormatToTimestame(itemValues[5]));
		// 更新日時
		bean.setUpdateDay(CmsUtils.FormatToTimestame(itemValues[6]));

		return bean;
	}
	//--------------------CSV登録 End--------------------


}