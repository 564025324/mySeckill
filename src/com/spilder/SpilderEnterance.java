package com.spilder;

import com.spilder.download.SpilderDownload;
import java.sql.SQLException;
import java.util.List;

/**
 * 项目: 实现网络资源的下载
 * 功能: 爬虫启动程序
 *
 * @author Lijingwen
 */
public class SpilderEnterance {
    public static void main(String[] args) {
        // 存储路径
        String filepath = "//Users//lijingwen//Downloads//picTest//spilder//download//";
        // 爬虫下载对象：网络资源
        String strurl = "http://www.mmonly.cc/mmtp/";
        // 生成所有数据库的连接
        JDBCHelper jdbcdao = new JDBCHelper();
        // 生成网络资源下载路径
        List<String> piclist = null;

        try {
            // 获取所有的网络资源
            piclist = JDBCHelper.selectPicurlByUrl("");
        } catch (SQLException e) {
            System.out.println("访问数据库异常");
            e.printStackTrace();
        }

        // 执行下载任务
        for (String url : piclist) {
            SpilderDownload.downloadByFileUrl(url, filepath);
        }

    }

}
