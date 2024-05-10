package com.cms.service.csvfetch;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.csvfetch.CsvFetchBean;
import com.cms.mapper.csvfetch.CsvFetchMapper;
import com.exception.BusinessException;

@Service
public class CsvFetchServicelmpl implements CsvFetchService{

	@Autowired
	CsvFetchMapper mapper;
	
	private List<CsvFetchBean> csvList;
	
	private int insCount;
	
	@Override
	public int registCsvToMySql(byte[] bytes,int count) throws Exception {
		insCount = 0;
		
        String data = new String(bytes);
        
        splitData(data,count);
        
		return insCount;
	}
	
	private void splitData(String data,int count) throws Exception {
		csvList = new ArrayList<CsvFetchBean>();
		String[] itemValues = data.split("\\r\\n");
		
		if(count != itemValues[0].split(",").length){
			throw new BusinessException("項目件数は正しくありません。" + "正しい項目件数は：" + itemValues[0].split(",").length);
		}
		
		if(itemValues.length <= 1){
			throw new BusinessException("空白ファイルです。");
		}
		
				
		for(int i = 1;i < itemValues.length;i++) {
			CsvFetchBean bean = saveToList(itemValues[i]);
			if (bean != null) {
				csvList.add(bean);
			}
		}
		
		if (csvList.size() > 0) {
			insCount = mapper.insertBulk(csvList);
		}
		
	}

	private CsvFetchBean saveToList(String data) throws Exception {
		String[] itemValues = data.split(",");
		
		CsvFetchBean bean = new CsvFetchBean();
		// 売上ID
	    bean.setSalesId(itemValues[0].replaceAll("\"", ""));
	    // 売上年月日
	    bean.setSalesDay(itemValues[1].replaceAll("\"", ""));
	    // 販売店ID
	    bean.setStoreId(itemValues[2].replaceAll("\"", "").trim());
	    // 商品ID
	    bean.setGoodId(itemValues[3].replaceAll("\"", "").trim());
	    // 販売件数
	    bean.setSalesCount(Integer.parseInt(itemValues[4].trim()));
	    // ステータス
	    // 登録日時
	    bean.setRegistDay(FormatToTimestamp(itemValues[5].replaceAll("\"", "")));
	    // 更新日時
	    bean.setUpdateDay(FormatToTimestamp(itemValues[6].replaceAll("\"", "")));

		return bean;
	}
	
	//时间格式处理
	private Timestamp FormatToTimestamp(String dt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/M/d H:m:s");
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(dt);
        } catch (ParseException e) {
            e.printStackTrace();  // 打印异常信息，以便调试
            return null;
        }
        return new java.sql.Timestamp(parsedDate.getTime());
    }
}
