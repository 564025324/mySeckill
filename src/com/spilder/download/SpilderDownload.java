package com.spilder.download;

import java.io.*;
import java.net.URL;

/**
 * 类SpilderDownload ，检验当前服务是否可以下载链接中的资源。
 * 作者：Administrator
 */
public class SpilderDownload {

    // 将下面的方法改成每次上次调用结束后才能开始下一个下载
    // 如果每次调用都要开始下一个下载，那么下载速度会很慢
    public SpilderDownload(String url, String filepath) {
        downloadByFileUrl(url, filepath);
    }

    // 将下面的方法改成每次上次调用结束后才能开始下一个下载
    public static void downloadByFileUrl(String url_str, String filepath) {
        File dir2 = new File(filepath);
        if (!dir2.exists()) {
            dir2.mkdirs();
            System.out.println(dir2 + ":创建成功");
        }

        try {
            File file = new File(dir2, (getMaxFileName(filepath) + 1) + ".jpg");
            if (file.exists()) {
                System.out.println("文件" + file + "已存在");
            }
            URL url = new URL(url_str);
            BufferedInputStream biStream = new BufferedInputStream(url.openStream());
            BufferedOutputStream boStream = new BufferedOutputStream(new FileOutputStream(file));

            System.out.println("开始下载...");

            byte[] buf = new byte[1024];
            int len;
            while ((len = biStream.read(buf)) != -1) {
                boStream.write(buf, 0, len);
            }
            // 关闭流
            boStream.close();
            biStream.close();
            System.out.println("完成下载" + file.getName());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接失败，跳过当前文件");
        }
    }

    /**
     * 获取当前目录下文件名为数字且最大的文件名对应数字
     *
     * @param filepath
     * @return
     */
    public static int getMaxFileName(String filepath) {
        int min = 0, max = 0;
        try {
            File file = new File(filepath);
            if (file.listFiles().length == 0) {
                return 0;
            } else {
                for (File f : file.listFiles()) {
                    if (f.isFile()) {
                        int fname = Integer.valueOf(f.getName().replaceAll(".jpg", ""));
                        if (fname >= max) {
                            max = fname;
                        } else if (fname <= min) {
                            min = fname;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return max;
    }


    public static void main(String[] args) throws IOException {
        // 测试下载效果
        downloadByFileUrl("https://t1.huishahe.com/uploads/tu/202204/40/006S6bqkly1h0zzw3e2fpj30qo0g7dhd.jpg", "//Users//lijingwen//Downloads//picTest//");

    }
}
