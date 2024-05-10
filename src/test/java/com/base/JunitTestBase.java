package com.base;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.operation.DatabaseOperation;

public class JunitTestBase {
	
	public static IDatabaseConnection connect() throws Exception {
        // 数据库 URL
        String url = "jdbc:mysql://localhost/mysql_pg";
        // 数据库用户名
        String username = "root";
        // 数据库密码
        String password = "Cai.a117";
        Connection conn = DriverManager.getConnection(url,username,password);
        IDatabaseConnection dbConn = new DatabaseConnection(conn);
        return dbConn;
    }

    public static void initData(String path,IDatabaseConnection dbConn) throws Exception {
        //创建数据集
        IDataSet dataset = new CsvDataSet(new File(path));
        
        DatabaseOperation.DELETE_ALL.execute(dbConn, dataset);
        //插入数据
        DatabaseOperation.INSERT.execute(dbConn, dataset);
    }

    public static void clearData(String path,IDatabaseConnection dbConn) throws Exception {
        //创建数据集
        IDataSet dataset = new CsvDataSet(new File(path));
        DatabaseOperation.DELETE_ALL.execute(dbConn, dataset);
    }

    public static void closeConnection(IDatabaseConnection dbConn) throws Exception {
        //关闭连接
        dbConn.close();
    }
   
    //登録データを抽出する
    public static ITable executeQuery(IDatabaseConnection dbconn, String tableNm ,String sql){

        PreparedStatement ps;
        ITable iTable = null;
		  try {
		   ps = dbconn.getConnection().prepareStatement(sql);
		   iTable = dbconn.createTable(tableNm, ps);
		
		  } catch (SQLException e) {
		   e.printStackTrace();
		  } catch (DataSetException e) {
		   e.printStackTrace();
		  }
		  return iTable;
    }
}
