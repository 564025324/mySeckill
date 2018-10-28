package com.crawl.demo.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.Iterator;

public class SpilderDownload {

	public static void downloadByFileUrl(String url_str, String filepath) {
		File dir2 = new File(filepath);
		if (!dir2.exists())
			dir2.mkdirs();

		System.out.println(dir2 + ":创建成功");
		try {
			File file = new File(dir2, (getMaxFileName(filepath) + 1) + ".jpg");
			if (file.exists()) {
				System.out.println(file + "已存在");
			}
			URL url = new URL(url_str);
			BufferedInputStream biStream = new BufferedInputStream(url.openStream());
			BufferedOutputStream boStream = new BufferedOutputStream(new FileOutputStream(file));

			System.out.println("開始下载...");

			byte[] buf = new byte[1024];
			int len;
			while ((len = biStream.read(buf)) != -1) {
				boStream.write(buf, 0, len);
			}
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

		String url_str = "http://t1.hxzdhn.com/uploads/tu/201611/tt/kinjt4vwupk.jpg";
		String filepath = "d://spilder//download//";

		// downloadByFileUrl(url_str,filepath);
		// System.out.println(getMaxFileName(filepath));

	}
}
