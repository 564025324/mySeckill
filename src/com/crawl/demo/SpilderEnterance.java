package com.crawl.demo;

import java.sql.SQLException;
import java.util.List;

import com.crawl.demo.download.SpilderDownload;

public class SpilderEnterance {
	public static void main(String[] args) {
		String filepath = "d://spilder//download//";
		String strurl = "http://www.mmonly.cc/mmtp/";
		// 生成所有数据

		JDBCHelper jdbcdao = new JDBCHelper();
		List<String> piclist = null;
		try {
			piclist = jdbcdao.selectPicurlByUrl("");
		} catch (SQLException e) {
			System.out.println("访问数据库异常");
			e.printStackTrace();
		}
		for (String url : piclist) {
			SpilderDownload.downloadByFileUrl(url, filepath);
		}

	}

}
