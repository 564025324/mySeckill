package com.spilder;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 抓取图片
 *
 * @author Lijingwen
 */
public class GetPicture {

    public void getHtmlPicture(String httpUrl, String filePath) {
        URL url;
        BufferedInputStream in;
        FileOutputStream file;
        try {
            System.out.println("爬取网络图片");
            // 获取网络图片的名字
            String fileName = httpUrl.substring(httpUrl.lastIndexOf("/")).replace("/", "");
            // 初始化URL
            url = new URL(httpUrl);
            // 初始化in对象，也就是获得url字节流
            in = new BufferedInputStream(url.openStream());
            file = new FileOutputStream(new File(filePath + "\\" + fileName));
            int t;
            while ((t = in.read()) != -1) { // TODO: 这个用法是什么
                file.write(t);
            }
            file.close();
            in.close();
            System.out.println("图片爬取成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getHtmlCode(String httpUrl) throws Exception {
        String content = "";
        URL url = new URL(httpUrl);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String input;
        // 如果有数据
        while ((input = reader.readLine()) != null) {
            // 读取数据赋值给content
            content += input;
        }
        // 关闭缓冲区
        reader.close();

        return content;
    }

    /**
     * 图片获取方法
     *
     * @param url
     * @param filePath
     * @throws Exception
     */
    public void get(String url, String filePath) throws Exception {
        // 定义两个获取网页图片的正则表达式
        String searchImgReg = "(?x)(src|SRC|background|BACKGROUND)=('|\")/?(([\\w-]+\\.(jpg|JPG|png|PNG|gif|GIF)))('|\")";
        String searchImgReg2 = "(?x)(src|SRC|background|BACKGROUND)=('|\")(http://([\\w-]+\\.)+[\\w-]+(:[0-9]+)*(/[\\w-]+)*(/[\\w-]+\\.(jpg|JPG|png|PNG|gif|GIF)))('|\")";

        String content = this.getHtmlCode(url);
        Pattern pattern = Pattern.compile(searchImgReg);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.group(3)); // TODO: 这里3 是什么鬼
            this.getHtmlPicture(matcher.group(3), filePath);
        }
    }

}
