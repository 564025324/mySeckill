package com.demo.dao;

import com.demo.entity.Seckill;

public interface ISeckillDao {
	
	public String getSeckillName(Integer seckillId);
	
	public void addSeckill(Seckill sk);

	//	public List<Seckill> getSkList();

}
