package com.spilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目: 实现网络资源的下载
 * 功能: 数据库操作类(创建持久层类，用来存储文件名，路径以及URL)
 *
 * @author Lijingwen
 */
public class JDBCHelper {

    //
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String DBurl = "jdbc:mysql://localhost:3306/spilder";
    private static final String user = "root";
    private static final String password = "12345678";
    private static PreparedStatement pstm = null;
    private static Connection spilderconn = null;

    public static void main(String[] args) {
        try {
            Class.forName(driver);
            spilderconn = DriverManager.getConnection(DBurl, user, password);
            String sql = "select * from urldb ;";
            pstm = spilderconn.prepareStatement(sql);
            System.out.println("数据库连接成功");

            // insertPicturebUrl("123333333333");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // 建立和「本地数据库」的连接
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

    // 从DB中获取所有的所需资源的URL
    public static List<Urldb> selectUrldbByUrl(String url) throws SQLException {
        List<Urldb> list = new ArrayList<Urldb>();
        try {
            String sql = "select * from spilder.urldb";
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

            // 如果查询结果为空，就将“http://www.mmonly.cc/mmtp/”作为list的第一个元素
            if (!rs.next()) {
                list.add("http://www.mmonly.cc/mmtp/");
            }

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

            String sql = "insert into url_pic(filename,filepath,url) values (?,?,?);";
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
