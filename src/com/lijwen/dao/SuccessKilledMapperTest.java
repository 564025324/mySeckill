package com.lijwen.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lijwen.entity.SuccessKilled;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledMapperTest {

	@Resource
	private SuccessKilledMapper successkilledMapper;
	
	@Test
	public void testInsertSuccessKilled() {
		// successkilledMapper.insertSuccessKilled(10005L, 15663406118L);
	}
	@Test
	public void testQueryByIdWithSeckill() {
		SuccessKilled skd = successkilledMapper.queryByIdWithSeckill(10005L, 15663406118L);
		
		System.out.println(skd);
		System.out.println(skd.getSeckill());
		
	}
	


}
