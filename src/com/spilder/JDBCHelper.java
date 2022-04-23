package com.spilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
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

    // 获取未被解析的所有网页List
    public static List<Urldb> selectUrldbByUrl(String url) throws SQLException {
        System.out.println("当前时间：" + new Date() + "   检索数据库网页地址开始");

        List<Urldb> list = new ArrayList<Urldb>();
        try {
            // sql语句：查询未被解析的网页
            String sql = "select * from spilder.urldb where 1=1 and analysised=0 ";
            // 如果url为空，就查询所有的网页
            if (!"".equals(url)) {
                sql = sql + " and url = '" + url + ";";
            }
            spilderconn = getConnection();
            pstm = spilderconn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(
                        new Urldb(
                                Integer.valueOf(rs.getString(1)),
                                rs.getString(2),
                                rs.getString(3),
                                Integer.valueOf(rs.getString(4))
                        ));
            }
            System.out.println("当前时间：" + new Date() + "   检索数据库网页地址结束");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库查询异常");
        } finally {
            spilderconn.close();
            pstm.close();
        }
        return list;
    }

    public static List<Urldb> selectPicurlByUrl(String url) throws SQLException {
        List<Urldb> urldbList = new ArrayList<Urldb>();
        try {

            String sql = "select * from url_pic ";
            if (!"".equals(url)) {
                sql = sql + " where url = '" + url + "' and downloadend = 0";
            }
            spilderconn = getConnection();
            pstm = spilderconn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            // 如果查询结果为空，就将“http://www.mmonly.cc/mmtp/”作为list的第一个元素
            if (!rs.next()) {
                urldbList.add(new Urldb(0, "http://www.mmonly.cc/mmtp/", "1"));
            }

            while (rs.next()) {
                urldbList.add(new Urldb(Integer.valueOf(rs.getString(1)), rs.getString(2), rs.getString(3)));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库查询异常");
        } finally {
            spilderconn.close();
            pstm.close();
        }
        return urldbList;
    }

    /**
     * 验证数据库表中是否已经存在该URL
     *
     * @param url
     * @param urlType 1:weburl ; 2:picurl
     * @return
     * @throws SQLException
     */
    public static boolean existUrlByUrl(String url, int urlType) throws SQLException {
        try {
            if ("".equals(url)) return false;

            // 根据urlType判断查询的表
            String sql = null;
            switch (urlType) {
                case 1:
                    sql = "select * from spilder.urldb where url = '" + url + "'";
                    break;
                case 2:
                    sql = "select * from spilder.url_pic where url = '" + url + "'";
                    break;
            }
            spilderconn = getConnection();
            pstm = spilderconn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库查询异常");
            return false;
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


    public static void updateUrlDb(String url) throws SQLException {
        try {
            spilderconn = getConnection();
            String sql = "update spilder.urldb set analysised=1 where url = '" + url + "'";
            pstm = spilderconn.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            spilderconn.close();
            pstm.close();
        }
    }

    public static void updatePicUrl(String url) throws SQLException {
        try {
            spilderconn = getConnection();
            String sql = "update spilder.url_pic set downloaded=1 where url = '" + url + "'";
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
