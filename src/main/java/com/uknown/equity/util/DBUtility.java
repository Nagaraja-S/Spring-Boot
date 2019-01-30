package com.uknown.equity.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.uknown.equity.service.DailyInstallerService;

import javassist.compiler.ast.Stmnt;

public class DBUtility {
	
	private  static final Logger logger = LoggerFactory.getLogger(DBUtility.class);


	public static boolean checkForTable(DataSource datasource) {

		Connection con = null;
		try {
			con = datasource.getConnection();

			java.sql.DatabaseMetaData metaData = con.getMetaData();

			String date = DateUtility.getCurentDate();

			String tableName = "share_information_" + date;// need to remove this hardcoded value

			ResultSet rs = metaData.getTables(null, null, tableName, new String[] { "TABLE" });

			while (rs.next()) {
				String tName = rs.getString("TABLE_NAME");

				if (tName != null && tableName.equals(tName)) {
					return true;
				} else {
					return false;
				}
			}

		} catch (Exception e) {
			logger.error("Exception :" + e);
		}
		return false;
	}

	public static void createTable(DataSource datasource) {

		File file;
		try {
			file = ResourceUtils.getFile("classpath:sql/dailyCreate.sql");

			String query = prepareQuery(file);

			String parsedQuery = parseQuery(query);
			
			logger.debug("Creating a table :"+parsedQuery);
			
			executeQuery(parsedQuery,datasource);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	private static String prepareQuery(File file) {

		String query = null;
		try {

			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

			StringBuilder builder = new StringBuilder();

			String line;

			while ((line = bufferedReader.readLine()) != null) {

				builder.append(line);
			}

			query = builder.toString();

		} catch (Exception e) {
			logger.error("Exception :" + e);
		}
		return query;
	}

	private static String parseQuery(String query) {

		String date = DateUtility.getCurentDate();

		return query.replace("<DD_MM_YYYY>", date);
	}
	
	
	public static void executeQuery(String parsedQuery, DataSource datasource) {
		
		Connection con = null;
		Statement stmt = null;
		try {
			
			con = datasource.getConnection();
			
			stmt = con.createStatement();
			
			stmt.executeUpdate(parsedQuery);
			
		}catch (Exception e) {
			logger.error("Exception :"+e);
		}finally {
			closeStatementAndConnection(stmt,con);
		}
		
	}

	private static void closeStatementAndConnection(Statement stmt, Connection con) {
		try {
			if (stmt != null) {
				stmt.close();
			}
			
			if (con != null) {
				con.close();
			}
		}catch (SQLException e) {
			logger.error("Exception :"+e);
		}
		
	}
	
	public static void closePSAndConnection(PreparedStatement pstmt, Connection con) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			
			if (con != null) {
				con.close();
			}
		}catch (SQLException e) {
			logger.error("Exception :"+e);
		}
		
	}

	
}
