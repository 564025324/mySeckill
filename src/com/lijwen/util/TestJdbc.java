package com.lijwen.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.lijwen.dao.ISeckillDao;
import com.lijwen.entity.Seckill;

public class TestJdbc {
	public static void main(String[] args) {
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring/spring-dao.xml"));
		ISeckillDao lldao = (ISeckillDao) factory.getBean("ljwdao");

		String name = lldao.getSeckillName(10004);

		System.out.println(" name : " + name);

		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Seckill sk = new Seckill();
		// try {
		// sk.setName("测试数据-1元秒杀牧马人");
		// sk.setNumber(Integer.valueOf("1"));
		// sk.setStarttime(sdf.parse("2018-05-25 11:00:00"));
		// sk.setEndtime(sdf.parse("2018-05-25 11:05:00"));
		// System.out.println(sk);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// lldao.addSeckill(sk);

	}
}
