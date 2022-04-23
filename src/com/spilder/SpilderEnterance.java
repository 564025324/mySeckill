package com.spilder;

import com.spilder.download.SpilderDownload;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目: 实现网络资源的下载
 * 功能: 爬虫启动程序
 *
 * @author Lijingwen
 */
public class SpilderEnterance {
    // 存储路径
    static String filepath = "//Users//lijingwen//Downloads//picTest//spilder//download//";
    // 爬虫下载对象：网络资源
    static String strurl = "http://www.mmonly.cc/mmtp/";
    static Map<Integer, String> map = new HashMap<>();
    // 生成网络资源下载路径
    static List<Urldb> urlList;

    static {
        try {
            urlList = JDBCHelper.selectPicurlByUrl("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 由于var++不是原子操作，所以需要synchronized同步代码块保持原子性
    public synchronized static void downloadCurrentThreadFile(int threadId) throws SQLException {
        for (int j = threadId * 1000; j < (threadId + 1) * 1000; j++) {
            System.out.println("线程" + threadId + "正在下载第" + j + "个网络资源");
            SpilderDownload.downloadByFileUrl(urlList.get(j).getUrl(), filepath);
            JDBCHelper.updatePicUrl(urlList.get(j).getUrl());
        }
    }

    public static void main(String[] args) {
        System.out.println("urlList.size() = " + urlList.size());

        for (int i = 0; i < 1000; i++) {
            // 创建爬虫下载对象
            Thread threads[] = new Thread[10];
            int threadId = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        downloadCurrentThreadFile(threadId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }
    }
}


// 多线程，一个方法只被一个线程调用一次的解决方案 https://www.cnblogs.com/zhjj/p/6665678.html

//        for (int i = 0; i <piclist.size()/5; i++) {
//        // 创建多线程对[piclist]数组用SpilderDownload.downloadByFileUrl方法执行下载
//        MultTask[] task = new MultTask[5];
//        task
//        SpilderDownload.downloadByFileUrl(url, filepath);



