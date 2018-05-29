package com.lijwen.dao;

import java.util.List;

import com.lijwen.entity.Seckill;

public interface ISeckillDao {
	
	public String getSeckillName(Integer seckillId);
	
	public void addSeckill(Seckill sk);

	//	public List<Seckill> getSkList();

}
