package com.sample.RegistCsvService;

import static org.junit.jupiter.api.Assertions.*;

import org.dbunit.database.IDatabaseConnection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.base.JunitTestBase;
import com.cms.service.practice2.RegistCsvService;

@SuppressWarnings("rawtypes")
@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistCsvServiceTest {
    private static IDatabaseConnection conn;
    private final static String path = "src\\test\\data\\employee";

    //登录测试数据
    @BeforeAll
    public static void init() throws Exception {
        //连接数据库
        conn = JunitTestBase.connect();
        //导入数据
        try {
        	JunitTestBase.initData(path, conn);
        }catch (Exception e) {
            String message = e.getMessage();
            System.out.println(message);
        }
        
    }

    //删除数据关闭连接
    @AfterAll
    public static void closeConnection() throws Exception {
        //清空数据
        JunitTestBase.clearData(path, conn);
        //关闭连接
        JunitTestBase.closeConnection(conn);
    }
    
    @Autowired
    RegistCsvService service;
    
    /**
     * ケース：No1
     * 　　　　異常
     * 条件：
     *      ファイルが設定されない
     * 予想値：
     *      メッセージ「ファイルが指定されません。」が表示されること
     */
    @Test
	public void test_RegistCsvToMySql_01() {
        try{
        	service.registCsvToMySql("D:\\desktop\\123.csv",7);
        	
        } catch (Exception e) {
            String message = e.getMessage();
            assertEquals("ファイルが指定されません。", message);   
        }
	}
    /**
     * ケース：No2
     * 　　　　異常
     * 条件：
     *      ファイルがありますが、空白です。
     * 予想値：
     *      メッセージ「空白ファイルです。」が表示されること
     */
    @Test
	public void test_RegistCsvToMySql_02() {
        try{
        	service.registCsvToMySql("D:\\desktop\\mysql_pg.post_collection.csv",7);
        	
        } catch (Exception e) {
            String message = e.getMessage();
            assertEquals("空白ファイルです。", message);   
        }
	}
    
    
    /**
     * ケース：No3
     * 　　　　異常
     * 条件：
     *      明細項目数が不足の場合
     * 予想値：
     *      メッセージ「ヘッダ項目件数は正しくありません。(正確の項目数：７)」が表示されること
     */
    
    /**
     * ケース：No4
     * 　　　　正常（最大桁数）
     * 条件：
     *      明細データ件数：1件
     * 予想値：
     *      メッセージ「ヘッダ項目件数は正しくありません。(正確の項目数：７)」が表示されること
     */
    
    /**
     * ケース：No5
     * 　　　　正常（最大桁数）
     * 条件：
     *      明細データ件数：複数件
     * 予想値：
     *      複数件データが登録されること
     */
}