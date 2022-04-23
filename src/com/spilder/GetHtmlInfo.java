package com.spilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取网页上的匹配图片链接和匹配网页链接并加载到数据库中
 *
 * @Author: lijingwen
 */
public class GetHtmlInfo {
    /**
     * 偶尔会面临网站的图片规则发生变更
     * 新图片链接样例：https://t1.huishahe.com/uploads/tu/202112/2/0ca1acad65.jpg
     * 新规则："\"http://t1\\.huishahe\\.com/uploads/.+\\.jpg\"";
     * 老规则： "\"http://t1\\.hxzdhn\\.com/uploads/.+\\.jpg\""
     */

    // 图片链接正则表达式
    final static String picture_regex = "\"https://t1\\.huishahe\\.com/uploads/.+\\.jpg\"";
    // 网页链接正则表达式
    final static String web_regex = "\"https://www.mmonly.cc/mmtp/xgmn/.+\\.html\"";

    private static int insert_picnum = 0;
    private static int insert_webnum = 0;

    // 数据库图片链接集合
    private static List<Urldb> dbWebUrlList = new ArrayList<>();

    // 解析网页的html
    public static void analysHtml(String strUrl) throws SQLException {
        Set<String> urlList = new HashSet<>();
        try {
            URL url = new URL(strUrl);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = in.readLine()) != null) {
                analys_line(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("首地址读取异常");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("页面转化异常");
        }
    }

    // 解析网页中某行是否存在图片/ 网页链接
    private static void analys_line(String line) throws SQLException {
        Set<String> picture_urls = new HashSet<>();
        JDBCHelper jdbcdao = new JDBCHelper();
        // 匹配图片链接
        Pattern picture_pattern = Pattern.compile(picture_regex);
        // 匹配网页链接
        Pattern web_pattern = Pattern.compile(web_regex);
        Matcher picture_matcher;
        Matcher web_matcher;

        for (String s : line.split(" ")) {
            picture_matcher = picture_pattern.matcher(s);
            web_matcher = web_pattern.matcher(s);
            // 匹配到图片链接
            if (picture_matcher.find()) {
                String pic_url = picture_matcher.group().replace("\"", "");
                if (!JDBCHelper.existUrlByUrl(pic_url, 2)) {
                    JDBCHelper.insertPicturebUrl(pic_url);
                    insert_picnum++;
                }
            }
            // 匹配到网页链接
            if (web_matcher.find()) {
                String web_url = web_matcher.group().replaceAll("\"", "");
                if (!JDBCHelper.existUrlByUrl(web_url, 1)) {
                    JDBCHelper.insertWebUrl(web_url, "1");
                    insert_webnum++;
                }
            }
        }
    }

    /**
     * 动态收集数据库中未解析的网页链接
     *
     * @throws SQLException return List<Urldb>
     */
    public static void dynamicCollectUnanasisedDBWebUrl() throws SQLException {
        try {
            while (true) {
                insert_picnum = insert_webnum = 0;
                List<Urldb> urldbList = JDBCHelper.selectUrldbByUrl("");
                System.out.println("当前时间：" + new Date() + "    本次检索到可解析的网页有【" + urldbList.size() + "】条网站地址");
                for (Urldb url : urldbList) {
                    System.out.println("当前时间：" + new Date() + "    ========= 正在解析第" + url.getId() + "个网络资源 =========");
                    analysHtml(url.getUrl());
                    JDBCHelper.updateUrlDb(url.getUrl());
                }
                Thread.sleep(60000);
                System.out.println("本次新增图片地址【" + insert_picnum + "】条，网站地址【" + insert_webnum + "】条");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws SQLException {
//        Thread threads[] = new Thread[10];
//        for (int i = 0; i < 10; i++) {
//            int threadId = i;
//            threads[i] = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
        dynamicCollectUnanasisedDBWebUrl();

//                    } catch (SQLException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            });
//            threads[i].start();
//        }
    }
}

