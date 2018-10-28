package com.crawl.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.LinkedCaseInsensitiveMap;

/**
 * 第一步 ，创建持久层类，用来存储文件名，路径以及URL
 * 
 * @author Lijingwen
 *
 */
public class JDBCHelper {

	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String DBurl = "jdbc:mysql://localhost:3306/seckill";
	private static final String user = "root";
	private static final String password = "root";
	private static PreparedStatement pstm = null;
	private static Connection spilderconn = null;

	public static void main(String[] args) {
		// try {
		// Class.forName(driver);
		// spilderconn = DriverManager.getConnection(DBurl, user, password);
		//
		// String sql = "select * from seckill ;";
		// pstm = spilderconn.prepareStatement(sql);
		//
		// System.out.println("数据库连接成功");
		//
		// // for (Urldb url : selectUrldbByUrl("")) {
		// // System.out.println(url.getUrl());
		// // }
		//
		// // insertPicturebUrl("123333333333");
		// } catch (ClassNotFoundException | SQLException e) {
		// e.printStackTrace();
		// }
	}

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName(driver);
			spilderconn = DriverManager.getConnection(DBurl, user, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return spilderconn;
	}

	public static List<Urldb> selectUrldbByUrl(String url) throws SQLException {
		List<Urldb> list = new ArrayList<Urldb>();
		try {

			String sql = "select * from urldb ";
			if (!"".equals(url)) {
				sql = sql + " where url = '" + url + "'";
			}
			spilderconn = getConnection();
			pstm = spilderconn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Urldb urlinfo = new Urldb();
				urlinfo.setUrl(rs.getString(2));
				list.add(urlinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库查询异常");
		} finally {
			spilderconn.close();
			pstm.close();
		}
		return list;
	}

	public static List<String> selectPicurlByUrl(String url) throws SQLException {
		List<String> list = new ArrayList<String>();
		try {

			String sql = "select * from url_pic ";
			if (!"".equals(url)) {
				sql = sql + " where url = '" + url + "'";
			}
			spilderconn = getConnection();
			pstm = spilderconn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				list.add(rs.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库查询异常");
		} finally {
			spilderconn.close();
			pstm.close();
		}
		return list;
	}

	public static boolean existPicUrlByUrl(String url) throws SQLException {
		try {
			String sql = "select * from url_pic ";
			if (!"".equals(url)) {
				sql = sql + " where url = '" + url + "'";
			}
			spilderconn = getConnection();
			pstm = spilderconn.prepareStatement(sql);
			if (pstm.executeQuery(sql).getRow() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库查询异常");
		} finally {
			spilderconn.close();
			pstm.close();
		}
		return false;
	}

	public static void insertPicturebUrl(String picurl) throws SQLException {
		try {
			spilderconn = getConnection();

			String sql = "insert into url_pic(url) values ('" + picurl + "');";
			pstm = spilderconn.prepareStatement(sql);
			pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			spilderconn.close();
			pstm.close();
		}
	}

	public static void insertWebUrl(String Strurl, String type) throws SQLException {
		try {
			String sql = "insert into urldb(url,url_type) values (?,?);";
			spilderconn = getConnection();
			pstm = spilderconn.prepareStatement(sql);
			pstm.setString(1, Strurl);
			pstm.setString(2, type);
			pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			spilderconn.close();
			pstm.close();
		}
	}

	public void insertFilePath(String fileName, String filePath, String url) throws SQLException {
		try {
			Class.forName(driver);
			spilderconn = DriverManager.getConnection(DBurl, user, password);
			System.out.println("数据库连接成功");

			String sql = "insert into spilder(filename,filepath,url) values (?,?,?);";
			pstm = spilderconn.prepareStatement(sql);
			pstm.setString(1, fileName);
			pstm.setString(2, filePath);
			pstm.setString(3, url);
			pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			spilderconn.close();
			pstm.close();
		}
	}

}
