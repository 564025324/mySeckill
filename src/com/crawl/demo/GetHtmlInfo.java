package com.crawl.demo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetHtmlInfo {
	final static String picture_regex = "\"http://t1\\.hxzdhn\\.com/uploads/.+\\.jpg\"";
	final static String web_regex = "\"http://www.mmonly.cc/mmtp/.+\\.html\"";

	private static int insert_picnum = 0;
	private static int insert_webnum = 0;

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

	private static void analys_line(String line) throws SQLException {
		Set<String> picture_urls = new HashSet<>();
		JDBCHelper jdbcdao = new JDBCHelper();

		Pattern picture_pattern = Pattern.compile(picture_regex);
		Pattern web_pattern = Pattern.compile(web_regex);
		Matcher picture_matcher;
		Matcher web_matcher;

		for (String s : line.split(" ")) {
			picture_matcher = picture_pattern.matcher(s);
			web_matcher = web_pattern.matcher(s);

			if (picture_matcher.find()) {
				String pic_url = picture_matcher.group().replace("\"", "");
				if (!jdbcdao.existPicUrlByUrl(pic_url)) {
					jdbcdao.insertPicturebUrl(pic_url);
					System.out.println("插入一条新的图片地址");
					insert_picnum++;
				}
			}
			if (web_matcher.find()) {
				String web_url = web_matcher.group().replaceAll("\"", "");
				if (jdbcdao.selectUrldbByUrl(web_url).size() == 0) {
					jdbcdao.insertWebUrl(web_url, "1");

					System.out.println("插入一条新的html地址");
					insert_webnum++;
				}
			}
		}
	}

	public static Set<String> collectWebUrl() {
		Set<String> web_url_list = new HashSet<>();

		return web_url_list;
	}

	public static void main(String[] args) throws SQLException {
		JDBCHelper jdbcdao = new JDBCHelper();
		List<Urldb> weburllist = jdbcdao.selectUrldbByUrl("");
		System.out.println("本次检索到数据库中有【" + weburllist.size() + "】条网站地址");
		for (Urldb url : weburllist) {
			analysHtml(url.getUrl());
		}

		System.out.println("本次新增图片地址【" + insert_picnum + "】条");
		System.out.println("本次新增网站地址【" + insert_webnum + "】条");

	}

}
