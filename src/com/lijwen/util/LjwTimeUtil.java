package com.lijwen.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LjwTimeUtil {
	public static void setStringToDate(String str) {
		Date day = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			sdf.parse(str);
			System.out.println(sdf.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		setStringToDate("2018-05-25 11:00:00");
	}

}
