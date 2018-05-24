package com.lijwen.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestQuartzService {

	public void printHelloMethod() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(now));
	}
}
