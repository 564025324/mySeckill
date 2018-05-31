package com.lijwen.dao;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lijwen.entity.Seckill;

/**
 * 配置spring 和junit 整合,junit启动时加载SpringIOC容器 spring -test,junit
 * 
 * @author Lijingwen
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillMapperTest {

	// 注入Dao实现类依赖
	@Resource
	private SeckillMapper seckillMapper;
	
	@Test
	public void testQueryById() {
		long id = 10000;
		Seckill sk = seckillMapper.queryById(id);
		System.out.println(sk);
	}
	
	@Test
	public void testQueryAll() {
		List<Seckill> list = seckillMapper.queryAll(0, 5);
		System.out.println(list);
	}
	
	@Test
	public void testReduceNumber() {
		Date now = new Date();
		System.out.println(now);
		int execrecords = seckillMapper.reduceNumber(10005L, now);
		System.out.println("执行次数 ：" + execrecords);
	}


}
