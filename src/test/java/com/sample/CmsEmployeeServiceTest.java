package com.sample;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.dbunit.database.IDatabaseConnection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.base.JunitTestBase;
import com.cms.entity.employee.CmsEmployeeBean;
import com.cms.form.cmsemployee.CmsEmployeeListForm;
import com.cms.service.employee.CmsEmployeeService;

@SuppressWarnings("rawtypes")
@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsEmployeeServiceTest {
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
    CmsEmployeeService service;

    /**
     * ケース：No1
     * 　　　　異常系
     * 条件：
     *      社員ID：DBに存在しない
     *      社員名：DBに存在しない
     * 予想値：
     *      エラー：検索結果はありません。
     */
    @Test
    public void test_readDataFromMysql_01() {
        try{
            CmsEmployeeListForm form = new CmsEmployeeListForm();
            form.setEmployeeId("111");
            form.setName("test1");
            service.select(form);
        } catch (Exception e) {
            String message = e.getMessage();
            assertEquals("検索結果はありません。", message);   
        }
    }

        /**
     * ケース：No2
     * 　　　　異常系
     * 条件：
     *      社員ID：DBに存在する
     *      社員名：DBに存在しない
     * 予想値：
     *      エラー：検索結果はありません。
     */
    @Test
    public void test_readDataFromMysql_02() {
        try{
            CmsEmployeeListForm form = new CmsEmployeeListForm();
            form.setEmployeeId("2");
            form.setName("test2");
            service.select(form);
        } catch (Exception e) {
            String message = e.getMessage();
            assertEquals("検索結果はありません。", message);   
        }
    }
    
    /**
	 * ケース：No3
	 * 　　　　異常系
	 * 条件：
	 *      社員ID：DBに存在しない
	 *      社員名：DBに存在する
	 * 予想値：
	 *      エラー：検索結果はありません。
	 */
	@Test
	public void test_readDataFromMysql_03() {
	    try{
	        CmsEmployeeListForm form = new CmsEmployeeListForm();
	        form.setEmployeeId("5");
	        form.setName("003");
	        service.select(form);
	    } catch (Exception e) {
	        String message = e.getMessage();
	        assertEquals("検索結果はありません。", message);   
	    }
	}
    /**
	 * ケース：No4
	 * 　　　　異常系
	 * 条件：
	 *      社員ID：DBに存在しない
	 *      社員名：未入力
	 * 予想値：
	 *      エラー：検索結果はありません。
	 */
	@Test
	public void test_readDataFromMysql_04() {
	    try{
	        CmsEmployeeListForm form = new CmsEmployeeListForm();
	        form.setEmployeeId("5");
	        service.select(form);
	    } catch (Exception e) {
	        String message = e.getMessage();
	        assertEquals("検索結果はありません。", message);   
	    }
	}
    /**
	 * ケース：No5
	 * 　　　　異常系
	 * 条件：
	 *      社員ID：未入力
	 *      社員名：DBに存在しない（姓のみ）
	 * 予想値：
	 *      エラー：検索結果はありません。
	 */
	@Test
	public void test_readDataFromMysql_05() {
	    try{
	        CmsEmployeeListForm form = new CmsEmployeeListForm();
	        form.setName("0");
	        service.select(form);
	    } catch (Exception e) {
	        String message = e.getMessage();
	        assertEquals("検索結果はありません。", message);   
	    }
	}
    /**
	 * ケース：No6
	 * 　　　　異常系
	 * 条件：
	 *      社員ID：未入力
	 *      社員名：DBに存在しない（名のみ）
	 * 予想値：
	 *      エラー：検索結果はありません。
	 */
	@Test
	public void test_readDataFromMysql_06() {
	    try{
	        CmsEmployeeListForm form = new CmsEmployeeListForm();
	        form.setName("03");
	        service.select(form);
	    } catch (Exception e) {
	        String message = e.getMessage();
	        assertEquals("検索結果はありません。", message);   
	    }
	}
    /**
	 * ケース：No7
	 * 　　　　正常系
	 * 条件：
	 *      社員ID：DBに存在する
	 *      社員名：未入力
	 * 予想値：
	 *      明細データが抽出されること
	 */
	@Test
	public void test_readDataFromMysql_07() {
	    try{
	        CmsEmployeeListForm form = new CmsEmployeeListForm();
	        form.setEmployeeId("2");
	        CmsEmployeeListForm ret = service.select(form);

	        assertEquals(1,ret.getResults().size());   
	        
	    } catch (Exception e) {
	        String message = e.getMessage();
	        System.out.println(message); 
	    }
	}
    /**
	 * ケース：No8
	 * 　　　　正常系
	 * 条件：
	 *      社員ID：未入力
	 *      社員名：DBに存在する（完全一致）
	 * 予想値：
	 *      明細データが抽出されること
	 */
	@Test
	public void test_readDataFromMysql_08() {
	    try{
	        CmsEmployeeListForm form = new CmsEmployeeListForm();
	        form.setName("002");
	        CmsEmployeeListForm ret = service.select(form);
	        assertEquals(1,ret.getResults().size());   
	        
	    } catch (Exception e) {
	        String message = e.getMessage();
	        System.out.println(message); 
	    }
	}
    /**
	 * ケース：No9
	 * 　　　　正常系
	 * 条件：
	 *      社員ID：未入力
	 *      社員名：DBに存在する（姓のみ）
	 * 予想値：
	 *      明細データが抽出されること
	 */
	@Test
	public void test_readDataFromMysql_09() {
	    try{
	        CmsEmployeeListForm form = new CmsEmployeeListForm();
	        form.setName("0");
	        CmsEmployeeListForm ret = service.select(form);
	        assertEquals(3,ret.getResults().size());   
	        
	    } catch (Exception e) {
	        String message = e.getMessage();
	        System.out.println(message); 
	    }
	}
    /**
	 * ケース：No10
	 * 　　　　正常系
	 * 条件：
	 *      社員ID：未入力
	 *      社員名：DBに存在する（名のみ）
	 * 予想値：
	 *      明細データが抽出されること
	 */
	@Test
	public void test_readDataFromMysql_10() {
	    try{
	        CmsEmployeeListForm form = new CmsEmployeeListForm();
	        form.setName("02");
	        CmsEmployeeListForm ret = service.select(form);
	        assertEquals(1,ret.getResults().size());   
	        
	    } catch (Exception e) {
	        String message = e.getMessage();
	        System.out.println(message); 
	    }
	}
    /**
	 * ケース：No11
	 * 　　　　正常系
	 * 条件：
	 *      社員ID：DBに存在する
	 *      社員名：DBに存在する（姓のみ）
	 * 予想値：
	 *      明細データが抽出されること
	 */
	@Test
	public void test_readDataFromMysql_11() {
	    try{
	        CmsEmployeeListForm form = new CmsEmployeeListForm();
	        form.setEmployeeId("2");
	        form.setName("0");
	        CmsEmployeeListForm ret = service.select(form);
	        assertEquals(1,ret.getResults().size());   
	        
	    } catch (Exception e) {
	        String message = e.getMessage();
	        System.out.println(message); 
	    }
	}
    /**
	 * ケース：No12
	 * 　　　　正常系
	 * 条件：
	 *      社員ID：DBに存在する
	 *      社員名：DBに存在する（名のみ）
	 * 予想値：
	 *      明細データが抽出されること
	 */
	@Test
	public void test_readDataFromMysql_12() {
	    try{
	        CmsEmployeeListForm form = new CmsEmployeeListForm();
	        form.setEmployeeId("2");
	        form.setName("02");
	        CmsEmployeeListForm ret = service.select(form);
	        assertEquals(1,ret.getResults().size());   
	        
	    } catch (Exception e) {
	        String message = e.getMessage();
	        System.out.println(message); 
	    }
	}
    /**
	 * ケース：No13
	 * 　　　　正常系
	 * 条件：
	 *      社員ID：DBに存在する
	 *      社員名：DBに存在する（完全一致）
	 * 予想値：
	 *      明細データが抽出されること
	 */
	@Test
	public void test_readDataFromMysql_13() {
	    try{
	        CmsEmployeeListForm form = new CmsEmployeeListForm();
	        form.setEmployeeId("2");
	        form.setName("002");
	        CmsEmployeeListForm ret = service.select(form);
	        assertEquals(1,ret.getResults().size());   
	        
	    } catch (Exception e) {
	        String message = e.getMessage();
	        System.out.println(message); 
	    }
	}
    /**
	 * ケース：No14
	 * 　　　　境界値
	 * 条件：
	 *      検索結果：1件のみ
	 * 予想値：
	 *      ・明細項目が設計書の通りにDBから抽出されること
	 *		・日付フォーマットが設計書通りに設定されること
	 *		・性別の名称が正常に表示されること
	 */
	@Test
	public void test_readDataFromMysql_14() {
	    try{
	        CmsEmployeeListForm form = new CmsEmployeeListForm();
	        form.setEmployeeId("2");
	        form.setName("002");
	        CmsEmployeeListForm ret = service.select(form);
	        CmsEmployeeBean bean = ret.getResults().get(0);
	        assertEquals("2",bean.getEmployeeId());   
	        assertEquals("002",bean.getName());  
	        assertEquals("02",bean.getSex());  
	        assertEquals("2024-04-05",bean.getBirthday());  
	        
	    } catch (Exception e) {
	        String message = e.getMessage();
	        System.out.println(message); 
	    }
	}
    /**
	 * ケース：No15
	 * 　　　　境界値
	 * 条件：
	 *      検索結果：複数件
	 * 予想値：
	 *      ・複数件データが抽出されること
	 *		・明細ヘッダとデータの揃えが設計書の通りに設定されること
	 *		・明細項目が設計書の通りにDBから抽出されること
	 *		・日付フォーマットが設計書通りに設定されること
	 *		・性別の名称が正常に表示されること
	 */
	@Test
	public void test_readDataFromMysql_15() {
	    try{
	        CmsEmployeeListForm form = new CmsEmployeeListForm();
	        form.setName("0");
	        CmsEmployeeListForm ret = service.select(form);
	        assertEquals(3,ret.getResults().size());   
	        List<CmsEmployeeBean> retList = new ArrayList<>();
	        for (CmsEmployeeBean bean : ret.getResults()) {
	        	CmsEmployeeBean ins = new CmsEmployeeBean();
	        	ins.setEmployeeId(bean.getEmployeeId());
	        	ins.setName(bean.getName());
	        	ins.setSex(bean.getSex());
	        	ins.setBirthday(bean.getBirthday());
	        	retList.add(ins);
	        }
	        
	        CmsEmployeeBean bean1 = new CmsEmployeeBean();
	        bean1.setEmployeeId("2");
	        bean1.setName("002");
	        bean1.setSex("02");
	        bean1.setBirthday("2024-04-05");
	        CmsEmployeeBean bean2 = new CmsEmployeeBean();
	        bean2.setEmployeeId("3");
	        bean2.setName("003");
	        bean2.setSex("03");
	        bean2.setBirthday("2024-04-05");
	        CmsEmployeeBean bean3 = new CmsEmployeeBean();
	        bean3.setEmployeeId("4");
	        bean3.setName("004");
	        bean3.setSex("04");
	        bean3.setBirthday("2024-04-05");
	        List<CmsEmployeeBean> beanList = new ArrayList<>();
	        beanList.add(bean1);
	        beanList.add(bean2);
	        beanList.add(bean3);
	        
	        assertEquals(beanList,retList);
	        
	    } catch (Exception e) {
	        String message = e.getMessage();
	        System.out.println(message); 
	    }
	}
    /**
	 * ケース：No16
	 * 　　　　境界値
	 * 条件：
	 *      Null許可の項目
	 * 予想値：
	 *      ・空白で表示されること
	 */
	@Test
	public void test_readDataFromMysql_16() {
	    try{
	        CmsEmployeeListForm form = new CmsEmployeeListForm();


	    } catch (Exception e) {
	        String message = e.getMessage();
	        System.out.println(message); 
	    }
	}
    /**
	 * ケース：No17
	 * 　　　　ソート順
	 * 条件：
	 *      複数件検索結果がある場合
	 * 予想値：
	 *      下記のソート順で画面に表示されること
	 *		入社年月日　昇順
	 *		社員ID　　　昇順
	 */
	@Test
	public void test_readDataFromMysql_17() {
	    try{
	        CmsEmployeeListForm form = new CmsEmployeeListForm();
	        form.setName("0");
	        CmsEmployeeListForm ret = service.select(form);
	        assertEquals(3,ret.getResults().size());   
	        List<CmsEmployeeBean> retList = ret.getResults();
	        
	        Integer[] ids = retList.stream().map(CmsEmployeeBean :: getEmployeeId).collect(Collectors.toList()).toArray(Integer[]::new);
	        Integer[] kuraberu = {2,3,4};
	        
	        assertArrayEquals(kuraberu, ids);
	        
	    } catch (Exception e) {
	        String message = e.getMessage();
	        System.out.println(message); 
	    }
	}
    /**
	 * ケース：No18
	 * 　　　　ソート順
	 * 条件：
	 *      複数件検索結果がある場合　且つ
	 *		入社年月日が同じの社員がある
	 * 予想値：
	 *		下記のソート順で画面に表示されること
	 *		入社年月日　昇順
	 *		社員ID　　　昇順
	 */
	@Test
	public void test_readDataFromMysql_18() {
	    try{
	        CmsEmployeeListForm form = new CmsEmployeeListForm();
	        form.setName("0");
	        //form.set入社年月日が同じ
	        CmsEmployeeListForm ret = service.select(form);
	        assertEquals(3,ret.getResults().size());   
	        List<CmsEmployeeBean> retList = ret.getResults();
	        
	        Integer[] ids = retList.stream().map(CmsEmployeeBean :: getEmployeeId).collect(Collectors.toList()).toArray(Integer[]::new);
	        Integer[] kuraberu = {2,3,4};
	        
	        assertArrayEquals(kuraberu, ids);

	    } catch (Exception e) {
	        String message = e.getMessage();
	        System.out.println(message); 
	    }
	}
}
